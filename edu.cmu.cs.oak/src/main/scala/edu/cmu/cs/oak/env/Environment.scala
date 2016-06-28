package edu.cmu.cs.oak.env

import scala.collection.immutable.Stack
import scala.collection.mutable.ListBuffer
import scala.xml.PrettyPrinter

import org.slf4j.LoggerFactory

import com.caucho.quercus.expr.Expr
import com.caucho.quercus.function.AbstractFunction
import com.caucho.quercus.program.Arg
import com.caucho.quercus.program.Function
import com.caucho.quercus.statement.Statement

import edu.cmu.cs.oak.env.heap.OakHeap
import edu.cmu.cs.oak.exceptions.VariableNotFoundException
import edu.cmu.cs.oak.nodes.ConcatNode
import edu.cmu.cs.oak.nodes.ConcatNode
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.ClassDef
import edu.cmu.cs.oak.value.FunctionDef
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakValueSequence
import edu.cmu.cs.oak.value.OakVariable
import edu.cmu.cs.oak.value.ObjectValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.SymbolValue

/**
 * Programs state and program state operations.
 *
 * @author Stefan Muehlbauer <smuhlbau@andrew.cmu.edu>
 */
class Environment(parent: EnvListener, calls: Stack[String], constraint: String) extends EnvListener {

  /**
   * HEAP of the environment. This heap manages the mapping from references to 
   * actual values.
   */
  val heap = new OakHeap()

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

  var loopModeEnabled = false;

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

    if (name equals "$wp_local_package") throw new RuntimeException()
    
    value match {
      case a: OakVariable => {
        variables += (name -> a)
      }
      case _ => {
        if (variables.contains(name)) {
          heap.insert(variables.get(name).get, value)
        } else {
          val variable = OakVariable(name + OakHeap.getIndex(), name)
          heap.insert(variable, value)
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
        throw new VariableNotFoundException("Unassigned variable " + name + ".")
      }
    }
    val ret = try {
      heap.extract(variable)
    } catch {
      case e: Exception => throw new RuntimeException(e)
    }
    if (ret == null) {
      NullValue("")
    }
    ret
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
   * pretty-printed XML
   */
  def getOutputAsPrettyXML(): String = {
    
    val wrapXML = {
      <DataModel>
			  { this.output.toXml() }
			</DataModel> 
    }
    
    var out = (new PrettyPrinter(200, 2)).format( wrapXML )  
    out = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" + out 
    out
  }
  

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
    val opt = variables.get(name)
    if (!opt.isEmpty) {
      opt.get
    } else {
      null
    }
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
   * Returns the environments map of variable names to references
   * @return map of variable names to references
   */
  def getVariables(): Map[String, OakVariable] = variables

  def unsetArrayElement(name: String, index: OakValue) {
    val arrayValueO = lookup(name)
    assert(arrayValueO.isInstanceOf[ArrayValue])
    val arrayValue = arrayValueO.asInstanceOf[ArrayValue]

    heap.unsetVariable(arrayValue.getRef(index))
    arrayValue.set(index, NullValue("AbstractEnv::unsetArrayElement"), this)
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
    heap.unsetVariable(getRef(name))
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

  def addToGlobal(name: String) {
    this.globalVariables += name
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

  def weaveDelta(joinResult: Delta) {

    // 1) Add the variational output to the environments output
    if (!joinResult.joinedOutput.isEmpty()) {
      this.addOutput(joinResult.joinedOutput)
    }

    // 2) Update the references on the environment's heap
    joinResult.joinedHeap.foreach {
      case (reference, value) => {
        this.heap.insert(reference, value)
      }
    }
    
    // 3) Update the variables that have changed during the execution of the branches
    joinResult.joinedVariables.foreach {
      case (name, value) => this.update(name, value)
    }
    
    // 5 Globals
    joinResult.joinedGlobals.foreach {
      g => this.addToGlobal(g)
    }
  }

  def getDelta(): Delta = {
    new Delta(this.getOutput(), this.variables, this.heap.varval, Set())
  }
  
  def weaveReferences(that: Environment) {
    that.heap.varval.filter{ case (ref, value) => !(this.heap.varval.keySet contains ref)}.foreach {
      case (ref, value) => this.heap.insert(ref, value)
    }
  }
  
}

/**
 * Static factory methods for environments using different
 * configurations.
 */
object Environment {
  
  /**
   * Map of fuńction names and function definitions
   */
  var funcs = Map[String, FunctionDef]()
  /**
   * Map of class definitions. All classes defined during the program execution
   * are stored here.
   */
  var classDefs = Map[String, ClassDef]()

  /**
   * Splits an environment into two branch environments that
   * can be joined afterwards.
   *
   * @param newConstraint Path constrained to add to the branches
   * @param Tuple of two branch environments
   */
  private def simpleFork(parent: Environment, newConstraint: String): (BranchEnv, BranchEnv) = {
    val b1 = new BranchEnv(parent, parent.getCalls(), parent.getConstraint() + " && " + newConstraint)
    val b2 = new BranchEnv(parent, parent.getCalls(), parent.getConstraint() + " && NOT(" + newConstraint + ")")

    /* Add variables of parent environment to the branch environments. */
    parent.variables.foreach {
      case (name, reference) =>
        {
          b1.setRef(name, reference)
          b2.setRef(name, reference)
        }
    }
    parent.heap.varval.foreach {
      case (reference, value) => {
        b1.heap.insert(reference, value)
        b2.heap.insert(reference, value)
      }
    }
    return (b1, b2)
  }

  def fork(environment: Environment, conditions: List[String]): List[BranchEnv] = {
    val forked = Environment.simpleFork(environment, conditions(0))
    if (conditions.size == 1) {
      List(forked._1, forked._2)
    } else {
      forked._1 :: fork(forked._2, conditions.tail)
    }
  }
  
  /**
   * Creates a new function environment that is used to
   * execute a 
   * 
   *  - function call or 
   *  - method call
   *  
   * The function call is documented via the 
   * environment's call stack.
   *
   * @param dis Parent environment to bounce output to
   * @param f Name of the function
   * @return FunctionEnv
   */
  def createFunctionEnvironment(dis: Environment, f: String): Environment = {
    val env = new Environment(dis, dis.getCalls push f, dis.getConstraint)
    dis.heap.varval.foreach {
      case (reference, value) => {
        env.heap.insert(reference, value)
      }
    }
    env
  }

  /**
   * Creates a new object environment that is used to
   * execute a function call.
   *
   * @param dis Parent environment to bounce output to
   * @param f Name of the function
   * @return ObjectEnv
   */
  def createObjectEnvironment(dis: Environment, obj: ObjectValue): Environment = {
    val env = new Environment(dis, dis.getCalls(), dis.getConstraint)
    env.update("$this", obj)
    env
  }
  
  /**
   * Creates a new function environment that is used to
   * execute a 
   * 
   *  - function call or 
   *  - method call
   *  
   * The function call is documented via the 
   * environment's call stack.
   *
   * @param dis Parent environment to bounce output to
   * @param f Name of the function
   * @return FunctionEnv
   */
  def createMethodEnvironment(dis: Environment, obj: ObjectValue, m: String): Environment = {
    val env = createFunctionEnvironment(dis, m)
    env.update("$this", obj)
    env
  }
  
  def createLoopEnvironment(dis: Environment): LoopEnv = {
    val env = new LoopEnv(dis, dis.getCalls, dis.getConstraint)
    dis.variables.foreach {
      case (name, reference) => env.setRef(name, reference)
    }
    dis.heap.varval.foreach {
      case (reference, value) => env.heap.insert(reference, value)
    }
    env
  }
  
  def defineFunction(f: FunctionDef): Unit = {
    funcs += (f.getName -> f)
  }

  def getFunction(name: String): FunctionDef = {
    val opt = funcs.get(name)
    if (!opt.isEmpty) {
      return opt.get
    } else {
      throw new RuntimeException("Function " + name + " is undefined.")
    }
  }
  
    /**
   * Adds a class definition to the environment.
   * @param value ClassDef to add
   */
  def addClass(value: ClassDef) {
    this.classDefs += (value.getName -> value)
  }

  /**
   * Looks up a class definition in the environment.
   * @param name Name of the class
   * @return corresponding class definition
   */
  def getClassDef(name: String): ClassDef = {

    if (name equals "Exception") {
      // TODO implement built-in class(es)?
    }
    try {
      classDefs.get(name).get
    } catch {
      case nsee: NoSuchElementException => throw new RuntimeException("Class " + name + " is not defined.")
    }
  }
  
}