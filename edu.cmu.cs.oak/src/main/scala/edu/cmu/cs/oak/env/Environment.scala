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

import edu.cmu.cs.oak.exceptions.VariableNotFoundException
import edu.cmu.cs.oak.nodes.ConcatNode
import edu.cmu.cs.oak.nodes.ConcatNode
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakValueSequence
import edu.cmu.cs.oak.value.OakVariable
import edu.cmu.cs.oak.value.ObjectValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.nodes.ConcatNode
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.nodes.ConcatNode
import edu.cmu.cs.oak.value.NullValue
import scala.collection.mutable.HashSet
import edu.cmu.cs.oak.nodes.ConcatNode
import scala.collection.mutable.AnyRefMap

/**
 * Programs state and program state operations.
 *
 * @author Stefan Muehlbauer <smuhlbau@andrew.cmu.edu>
 */
class Environment(parent: Environment, calls: Stack[Call], constraint: Constraint) extends EnvListener {
  
  var age = 0 
  
  var changed = false
  
  /**
   * Map of variable identifiers and variable references.
   * In various contexts, variables can refer to the same value.
   * For variable reference handling {@see {@link #edu.cmu.cs.oak.env.Oa kHeap}}.
   */
  val variables = AnyRefMap[String, OakValue]()

  /**
   * Changes in the static class fields
   */
  val staticClassFields = collection.mutable.Map[String, collection.mutable.Map[String, OakValue]]()
  
  
  /**
   * Map of references
   */
  val references = AnyRefMap[OakVariable, OakValue]()

  /**
   * Map of constants that are defined during the execution
   *  of a PHP script.
   */
  val constants = AnyRefMap[String, OakValue]()
  
  /**
   * Map of global variable identifiers and variable references.
   */
  val globalVariables = HashSet[String]()

  //var loopModeEnabled = false;

  /**
   * Output (D Model) of the environment.
   */
  val output = ConcatNode(List())

  /**
   * Map of fuńction names and function definitions
   */
  var funcs = AnyRefMap[String, FunctionDef]()
  /**
   * Map of class definitions. All classes defined during the program execution
   * are stored here.
   */
  var classDefs = AnyRefMap[String, ClassDef]()
  
  /**
   * Environment flag to be set to TRUE, once a return statement has 
   * been executed. This flag will be checked when a statement/block 
   * is executed.
   */
  private var terminated = false
  
  val logger = LoggerFactory.getLogger(classOf[Environment])

  /**
   * Updates a variable in the environment, i.e. it stores a variable
   * assignment, such as '$i = 8'.
   * @paran name Name of the variable
   * @param value OakValue to assign to the variable
   */
  def update(name: String, value: OakValue) {
    
    changed = true
    if (variables.contains(name)) {
      val ref = variables.get(name).get.asInstanceOf[OakVariable]
      references.put(ref, value)
    } else {
      val variable = OakVariable(name + OakHeap.getIndex(), name)
      references.put(variable, value)
      variables.put(name, variable)
    }
  }

  def isFunctionEnv(): Boolean = (this.parent != null) && (this.parent.getCalls().size < getCalls().size)
  
  def hasChanged = changed
  
  def isGlobalVariable(varname: String): Boolean = {
    if (globalVariables contains varname) {
      return true
    } else if (parent != null  && (!(parent eq this))) {
      return parent.isGlobalVariable(varname)
    } else {
      return false
    }
  }
  /**
   * Looks up an variable and returns its context-dependent value.
   * @param name Name of the variable
   * @return value Value of the variable
   */
  def lookup(name: String): OakValue = {
    var reference = if (isGlobalVariable(name)) {
      getRef(name, false)
    } else {
      getRef(name)
    }
    
    def recursiveLookup(reference: OakVariable): OakValue = {
      this.extract(reference) match {
        case ref2: OakVariable => recursiveLookup(ref2)
        case null => null
        case ov: OakValue => ov
      }
    }
    
    val value = try {
      recursiveLookup(reference)
    } catch {
      case vnfe: VariableNotFoundException => throw new RuntimeException(vnfe)
    }
    value
  }

  /**
   * Add output to environment.
   * @param DNode Value to add to output
   */
  def addOutput(o: DNode) {
    changed = true
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

    var out = (new PrettyPrinter(160, 2)).format(wrapXML)
    out = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" + out
    out
  }

  /**
   * Manipulation of references via the environment
   *  @param name of the variable
   *  @param reference to point to
   */
  def setRef(name: String, ref: OakVariable): Unit = {
    changed = true
    variables.put(name, ref)
  }

  def extract(reference: OakVariable): OakValue = {
    if (references.contains(reference)) {
      references.get(reference).get
    } else if ((parent != null)) {
      parent.extract(reference)
    } else {
      throw new VariableNotFoundException("Reference not found " + reference)
    }
  }

  def insert(reference: OakVariable, value: OakValue) {
    
    changed = true
    references.put(reference, value)
  }

  /**
   * Get the current reference of a variable at runtime.
   * @param name Name of the variable
   * @return reference that variable 'name points to
   */
  def getRef(name: String, limitScope: Boolean = true): OakVariable = {
    if (variables.contains(name)) {
      val ref = variables.get(name).get.asInstanceOf[OakVariable]
      ref
    } else if (parent != null) {
      parent.getRef(name)
    } else {
      throw new VariableNotFoundException("Unassigned variable " + name + ".")
    }

  }

  /**
   * Returns the current call stack at runtime.
   * @return Stack of strings where each string denotes a function call
   */
  def getCalls(): Stack[Call] = calls

  /**
   * Returns the environment's parent environment (null if top-level env)
   * @param parent environment
   */
  def getParent(): Environment = parent

  /**
   * Returns the environments path condition
   * @return path condition
   */
  def getConstraint(): Constraint = constraint

  /**
   * Returns the environments map of variable names to references
   * @return map of variable names to references
   */

  def unsetArrayElement(name: String, index: OakValue) {
    val arrayValueO = lookup(name)
    assert(arrayValueO.isInstanceOf[ArrayValue])
    val arrayValue = arrayValueO.asInstanceOf[ArrayValue]
    //heap.unsetVariable(arrayValue.getRef(index))
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
    variables.remove(name)
  }

  def ifdefy(): List[String] = output.ifdefy()

  def getParents(): List[Environment] = {
    val parents = new ListBuffer[Environment]
    var cParent = this.parent
    while (cParent != null) {
      parents += cParent
      cParent = cParent.getParent()
    }
    parents.toList
  }

  def addToGlobal(name: String) {
    changed = true
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
    val s = f._name

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
    //    joinResult.joinedHeap.foreach {
    //      case (reference, value) => {
    //        this.heap.insert(reference, value)
    //      }
    //    }
    //    val sizes = (this.heap.varval.size, joinResult.joinedHeap.size)
    joinResult.joinedHeap.foreach {
      case (reference, value) => this.insert(reference, value)
    }

    // 3) Update the variables that have changed during the execution of the branches
    joinResult.joinedVariables.foreach {
      case (name, value) => this.update(name, value)
    }

    // 4 Globals
    joinResult.joinedGlobals.foreach {
      g => this.addToGlobal(g)
    }
    
    // 5 Static fields
    joinResult.joinedStaticClassVariables.foreach {
      case (c, m) => {
        m.foreach {
          case (f, v) => {
            this.setStaticClassField(c, f, v)
          }
        }
      }
    }
    
    // 6) Constants
    joinResult.joinedConstants.foreach {
      case (n, c) => this.defineConstant(n, c)
    }
    
    // 7) Functions
    joinResult.joinedFunctionDefs.foreach {
      case (name, f) => this.defineFunction(f)
    }
    
    // 8) Classes
    joinResult.joinedClassDefs.foreach {
      case (name, c) => this.addClass(c)
    }
  }

  def getDelta(): Delta = {
    var returnMap = AnyRefMap[String, OakValue]()
    if (variables.contains("$return")) returnMap += ("$return" -> getRef("$return"))
    if (variables.contains("$returnref")) returnMap += ("$returnref" -> getRef("$returnref"))
    
    globalVariables.foreach { 
      gv => {
//        if (!(variables.keySet contains gv)) {
//          update(gv, NullValue(gv))
//        }
        if ( ! this.extract(this.getRef(gv, false)).isInstanceOf[ArrayValue]) {
          returnMap += (gv -> this.getRef(gv, false)) 
        }
        
      }
    }
    
    var t = AnyRefMap[String, Map[String, OakValue]]()
    this.staticClassFields.foreach {
      case (m1, m2) => t.put(m1, m2.toMap)
    }
    new Delta(this.getOutput(), if (!this.isFunctionEnv()) variables else returnMap, references, t, this.globalVariables.toSet, constants.toMap, funcs, classDefs)
  }

  //  def weaveReferences(that: Environment) {
  //    val references = that.getHeap.varval.filter { case (ref, value) => !(this.heap.varval.keySet contains ref) }
  //    this.heap.varval = (this.heap.varval.toSeq ++ references.toSeq).toMap
  //    
  //  }

  /**
   * Creates a new Heap linked to the parents heap
   */
  //  def copyHeap(): OakHeap = {
  //    val copy = new OakHeap(heap.varval)
  //    copy.varval = heap.varval
  //    copy
  //  }

  def getStaticClassField(className: String, fieldName: String): OakValue = {
    if (!this.staticClassFields.get(className).isEmpty && !this.staticClassFields.get(className).get.get(fieldName).isEmpty) {
        this.staticClassFields.get(className).get.get(fieldName).get
    } else if (this.parent != null) {
      this.parent.getStaticClassField(className, fieldName)
    } else {
      getClassDef(className).getStaticFields().get(fieldName).get
    }
  }
  
  def setStaticClassField(className: String, fieldName: String, value: OakValue) = {
    changed = true
    if (this.staticClassFields.get(className).isEmpty) {
      this.staticClassFields += (className -> collection.mutable.Map[String, OakValue]())
    }
    this.staticClassFields.get(className).get.put(fieldName, value)
  }
  
    /**
   * Defines a constant used during the program execution.
   *
   * @param name Name of the constant
   * @param value Value of the Constant
   */
  def defineConstant(name: String, value: OakValue) {
    constants.put(name, value)
  }
  
   /**
   * Defines a constant used during the program execution.
   *
   * @param name Name of the constant
   * @param value Value of the Constant
   */
  def getConstant(name: String): OakValue = {
    if (!constants.get(name).isEmpty) {
      constants.get(name).get
    } else if (parent != null) {
      parent.getConstant(name)
    } else {
      StringValue("", "", 0)//NullValue("not found")//
    }
  }
  
  def defineFunction(f: FunctionDef): Unit = {
    funcs.put(f.getName, f)
  }

  def getFunction(name: String): FunctionDef = {
    if (!funcs.get(name).isEmpty) {
      funcs.get(name).get
    } else if (parent != null) {
      parent.getFunction(name)
    } else {
      throw new RuntimeException("Function " + name + " not defined!")
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
    if (!classDefs.get(name).isEmpty) {
      classDefs.get(name).get
    } else if (parent != null) {
      parent.getClassDef(name)
    } else {
      throw new RuntimeException("ClassDef " + name + " not defined!")
    }
  }
  
  def containsFunction(name: String) = !funcs.get(name).isEmpty
  
  def hasTerminated(): Boolean = this.terminated
  
  def terminate() {
    this.terminate(calls.size)
  }
  
  private def terminate(call_stack_size: Int) {
    this.terminated = true
    if (parent != null && parent.getCalls().size == call_stack_size) {
      parent.terminate(call_stack_size)
    }
  }
}

/**
 * Static factory methods for environments using different
 * configurations.
 */
object Environment {

  var forks = 0

  /**
   * Splits an environment into two branch environments that
   * can be joined afterwards.
   *
   * @param newConstraint Path constrained to add to the branches
   * @param Tuple of two branch environments
   */
  private def simpleFork(parent: Environment, newConstraint: Constraint): (BranchEnv, BranchEnv) = {
    Environment.forks += 1
    val b1 = new BranchEnv(parent, parent.getCalls(), newConstraint)
    val b2 = new BranchEnv(parent, parent.getCalls(), newConstraint.NOT())

    /* Add variables of parent environment to the branch environments. */
    //    parent.variables.foreach {
    //      case (name, reference) =>
    //        {
    //          b1.setRef(name, reference)
    //          b2.setRef(name, reference)
    //        }
    //    }
    return (b1, b2)
  }

  def fork(environment: Environment, conditions: List[Constraint]): List[BranchEnv] = {
    val forked = Environment.simpleFork(environment, conditions(0))
    forked._1.age = environment.age+1
    forked._2.age = environment.age+1
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
  def createFunctionEnvironment(dis: Environment, fc: Call): Environment = {
    val env = new Environment(dis, dis.getCalls push fc, dis.getConstraint)
    env.age = dis.age+1
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
    env.age = dis.age+1
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
  def createMethodEnvironment(dis: Environment, obj: ObjectValue, mc: Call): Environment = {
    val env = createFunctionEnvironment(dis, mc)
    env.update("$this", obj)
    env.age = dis.age+1
    env
  }

  def createLoopEnvironment(dis: Environment): LoopEnv = {
    val env = new LoopEnv(dis, dis.getCalls, dis.getConstraint)
    env.age = dis.age+1
    env
  }

}