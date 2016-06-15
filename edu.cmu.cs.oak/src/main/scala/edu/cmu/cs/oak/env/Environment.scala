package edu.cmu.cs.oak.env

import scala.collection.immutable.Stack
import scala.collection.mutable.HashSet
import scala.collection.mutable.ListBuffer

import org.slf4j.LoggerFactory

import com.caucho.quercus.expr.Expr
import com.caucho.quercus.function.AbstractFunction
import com.caucho.quercus.program.Arg
import com.caucho.quercus.program.Function
import com.caucho.quercus.statement.Statement

import edu.cmu.cs.oak.env.heap.OakHeap
import edu.cmu.cs.oak.exceptions.VariableNotFoundException
import edu.cmu.cs.oak.nodes.ConcatNode
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.nodes.SelectNode
import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.ClassDef
import edu.cmu.cs.oak.value.FunctionDef
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakValueSequence
import edu.cmu.cs.oak.value.OakVariable
import edu.cmu.cs.oak.value.ObjectValue
import edu.cmu.cs.oak.value.SymbolValue

/**
 * Programs state and program state operations.
 *
 * @author Stefan Muehlbauer <smuhlbau@andrew.cmu.edu>
 */
class Environment(parent: EnvListener, calls: Stack[String], constraint: String) extends EnvListener {

  /** 
   * Map of variable identifiers and variable references.
   * In various contexts, variables can refer to the same value.
   * For variable reference handling {@see {@link #edu.cmu.cs.oak.env.OakHeap}}.
   */
  var variables = Map[String, OakVariable]()
  
  /**
   * Map of global variable identifiers and variable references.
   */
  var globalVariables = Set[String]()

  /**
   * Map of class definitions. All classes defined during the program execution
   * are stored here.
   */
  var classDefs = Map[String, ClassDef]()

  /**
   * Output (D Model) of the environment.
   */
  val output = ConcatNode(List())

  val logger = LoggerFactory.getLogger(classOf[Environment])
  
  /**
   * Updates a variable in the environment, i.e. it stores a variable
   * assignment, such as '$i = 8'.
   * @paran name Name of the variable
   * @param value OakValue to assign to the variable
   */
  def update(name: String, value: OakValue) {

    value match {
      case a: OakVariable => {
        variables += (name -> a)
      }
      case _ => {
        if (variables.contains(name)) {
          OakHeap.insert(variables.get(name).get, value)
        } else {
          val variable = new OakVariable(name + OakHeap.getIndex())
          OakHeap.insert(variable, value)
          variables += (name -> variable)
        }
      }
    }
  }

  /**
   * Looks up an variable and returns its context-dependent value.
   * @param name Name of the variable
   * @return value Value of the variable
   */
  def lookup(name: String): OakValue = {
    val variable = {
      val opt = variables.get(name)
      if (!opt.isEmpty) {
        opt.get
      } else {
        //println("Unassigned variable " + name + ".")
        throw new VariableNotFoundException("Unassigned variable " + name + ".")
      }
    }
    val ret = try {
      OakHeap.extract(variable)
    } catch {
      case e: Exception => throw new RuntimeException(e)
    }
    if (ret == null) {
      throw new RuntimeException(name)
    }
    ret
  }

  /**
   * Add output to environment.
   * @param value Value to add to output
   */
  def addOutput(value: OakValue) {
    output.addOutput(DNode.createDNode(value))
  }
  
  /**
   * Add output to environment.
   * @param DNode Value to add to output
   */
  def addOutput(o: DNode) {
    output.addOutput(o)
  }

  /**
   * Get the output sequence from the environment.
   * @return output as sequence of values
   */
  def getOutput(): DNode = output

  /**
   * Manipulation of references via the environment
   *  @param name of the variabke
   *  @param reference to point to
   */
  def setRef(name: String, ref: OakVariable): Unit = {
    variables += (name -> ref)
  }

  /**
   * Get the current reference of a variable at runtime.
   * @param name Name of the variable
   * @return reference that variable 'name points to
   */
  def getRef(name: String): OakVariable = {
    variables.get(name).get
  }

  /**
   * Adds a function definition to the environment.
   * @param value Function definition to add
   */
  def addFunction(value: FunctionDef) {
    OakHeap.defineFunction(value)
  }

  /**
   * Looks up a function definition in the environment.
   * @param name Name of the function
   * @return corresponding function definition
   */
  def getFunction(name: String): FunctionDef = {
    OakHeap.getFunction(name)
  }

  /**
   * Returns the current call stack at runtime.
   * @return Stack of strings where each string denotes a function call
   */
  def getCalls(): Stack[String] = calls

  /**
   * Returns the environment's parent environment (null if top-level env)
   * @param parent environment
   */
  def getParent(): EnvListener = parent

  /**
   * Receive and store output from a child environment
   * @param value Sequence of output values to add
   */
  def receiveOutput(node: DNode) = {
     addOutput(node)
  }

  /**
   * Returns the environments path condition
   * @return path condition
   */
  def getConstraint(): String = constraint

  /**
   * Prepends the passed output to the environments output.
   * @param pre List of values to add
   */
  def prependOutput(pre: DNode) {
    output.prepend(pre)
  }

  /**
   * Returns the environments map of variable names to references
   * @return map of variable names to references
   */
  def getVariables(): Map[String, OakVariable] = variables

  def unsetArrayElement(name: String, index: OakValue) {
    val arrayValueO = lookup(name)
    assert(arrayValueO.isInstanceOf[ArrayValue])
    val arrayValue = arrayValueO.asInstanceOf[ArrayValue]

    OakHeap.unsetVariable(arrayValue.getRef(index))
    arrayValue.set(index, NullValue("AbstractEnv::unsetArrayElement"))
  }

  def ifdefy(node: OakValue): List[String] = {
    var res = List[String]()
    node match {
      case seq: OakValueSequence => {
        seq.getSequence.foreach { v => res = res ++ ifdefy(v) }
      }
      case ite: Choice => {
        res = res ++ List("#if " + ite.getConstraint())
        res = res ++ ifdefy(ite.getV1()).map { x => x.trim }
        res = res ++ List("#else")
        res = res ++ ifdefy(ite.getV2()).map { x => x.trim }
        res = res ++ List("#endif")
      }
      case s: SymbolValue => res = res.::(s.toString())
      case null => res
      case _ => res = res.::(node.toString())
    }
    res
  }

  

  def unset(name: String) {
    OakHeap.unsetVariable(getRef(name))
    variables -= name
  }

  def ifdefy(): List[String] = output.ifdefy()
  
  def getParents(): List[Environment] = {
    val parents = new ListBuffer[Environment]
    var cParent = this.parent
    while (cParent != null) {
      parents += cParent.asInstanceOf[Environment]
      cParent = cParent.asInstanceOf[Environment].getParent()
    }
    parents.toList
  }
  
    
  def addToGlobal(name: String, value: OakValue = NullValue("AbstractEnv::addToGlobal")) {
    this.globalVariables += name
  }

  /**
   * Adds a class definition to the environment.
   * @param value ClassDef to add
   */
  def addClass(value: ClassDef): Unit = {
    this.classDefs += (value.getName -> value)
  }

  /**
   * Looks up a class definition in the environment.
   * @param name Name of the class
   * @return corresponding class definition
   */
  def getClass(name: String): ClassDef = {
    
    if (name equals "Exception") {
      // TODO implement built-in class(es)?
    }
    try {
      classDefs.get(name).get
    } catch {
      case nsee: NoSuchElementException => throw new RuntimeException("Class " + name + " is not defined.")
    }
  }
  
  /**
   * Defines a function. The defined function will be accessible during the
   * program execution.
   *
   * @param fu Function instance retrieved from the QuercusProgram to execute
   *
   * @return FunctionDef instance to be stored by the Intepreter
   */
  def defineFunction(fu: AbstractFunction): FunctionDef = {
    val f = fu.asInstanceOf[Function]
    val hasReturn = f._hasReturn //Interpreter.accessField(f, "_hasReturn").asInstanceOf[Boolean]
    val returnsRef = f._isReturnsReference //Interpreter.accessField(f, "_isReturnsReference").asInstanceOf[Boolean]
    val args = ListBuffer[String]()
    var defaults = Map[String, Expr]()
    f._args.foreach {
      a =>
        {
          val default = a._default.asInstanceOf[Expr]
          if (default != null) defaults += (a.getName.toString() -> default)
          args.append((if (a.isReference()) "&" else "") + a.getName.toString())
        }
    }
    val statement = f._statement.asInstanceOf[Statement]
    // Add function to the global environment
    return new FunctionDef(f.getName, args.toArray, defaults, statement, hasReturn, returnsRef)
  }
}

/**
 * Static factory methods for environments using different 
 * configurations.
 */
object Environment {

  /**
   * Splits an environment into two branch environments that
   * can be joined afterwards.
   *
   * @param newConstraint Path constrained to add to the branches
   * @param Tuple of two branch environments
   */
  def fork(parent: Environment, newConstraint: String): (BranchEnv, BranchEnv) = {
    val b1 = new BranchEnv(parent, parent.getCalls(), parent.getConstraint() + " && " + newConstraint)
    val b2 = new BranchEnv(parent, parent.getCalls(), parent.getConstraint() + " && NOT(" + newConstraint + ")")

    /* Add variables of parent environment to the branch environments. */
    parent.getVariables.keySet.foreach {
      k =>
        {
          b1.update(k, parent.lookup(k))
          b2.update(k, parent.lookup(k))
        }
    }
    return (b1, b2)
  }

  /**
   *
   */
  def splitN(parent: Environment, expr: Expr, cases: List[Expr]): List[BranchEnv] = {

    var envs = List[BranchEnv]()
    cases.foreach {
      c => envs ++= List(new BranchEnv(parent, parent.getCalls, parent.getConstraint + " && (" + expr + " == " + c + ")"))
    }
    envs.toList.foreach {
      e =>
        parent.getVariables.keySet.foreach {
          k =>
            {
              e.update(k, parent.lookup(k))
            }
        }
    }
    return envs
  }
}