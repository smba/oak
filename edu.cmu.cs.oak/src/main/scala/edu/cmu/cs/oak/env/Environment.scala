package edu.cmu.cs.oak.env

import scala.collection.immutable.Stack
import scala.collection.mutable.HashSet
import scala.collection.mutable.ListBuffer

import com.caucho.quercus.expr.Expr
import com.caucho.quercus.program.Arg
import com.caucho.quercus.program.Function
import com.caucho.quercus.statement.Statement

import edu.cmu.cs.oak.core.Interpreter
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.ClassDef
import edu.cmu.cs.oak.value.FunctionDef
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakValueSequence
import edu.cmu.cs.oak.value.OakVariable
import edu.cmu.cs.oak.value.ObjectValue
import com.caucho.quercus.function.AbstractFunction
import edu.cmu.cs.oak.env.heap.OakHeap
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.exceptions.VariableNotFoundException
import org.slf4j.LoggerFactory
import edu.cmu.cs.oak.value.ArrayValue

/**
 * Programs state and program state operations.
 *
 * @author Stefan Muehlbauer <smuhlbau@andrew.cmu.edu>
 */
abstract class Environment(parent: EnvListener, calls: Stack[String], constraint: String) extends EnvListener {

  val logger = LoggerFactory.getLogger(classOf[Environment])

  /**
   * Map of variable identifiers and variable references.
   * In various contexts, variables can refer to the same value.
   * For variable reference handling {@see {@link #edu.cmu.cs.oak.env.OakHeap}}.
   */
  var variables = Map[String, OakVariable]()

  /**
   * Mutable output trace. All output genereated in this environment will
   * be cached and sent to the parent environment unless this is the global
   * environment (parent == null).
   */
  private var output = new ListBuffer[OakValue]()

  /**
   * Updates a variable in the environment, i.e. it stores a variable
   * assignment, such as '$i = 8'.
   * @paran name Name of the variable
   * @param value OakValue to assign to the variable
   */
  def update(name: String, value: OakValue) {

    if (Environment.globals.keySet contains name) {
      Environment.globals += (name -> value)
      return
    }
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
    if (Environment.globals.keySet contains name) {
      return Environment.globals.get(name).get
    }
    val variable = {
      val opt = variables.get(name)
      if (!opt.isEmpty) {
        opt.get
      } else {
        println("Unassigned variable " + name + ".")
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
   * Creates a D Model tree of the (symbolic) output that
   * can easily be traversed.
   * @return Root node of the D Model tree
   */
  def getOutputModel(): DNode = {
    DNode.createDNode(output.toList)
  }

  /**
   * Add output to environment.
   * @param value Value to add to output
   */
  def addOutput(value: OakValue) {
    output += value

  }

  /**
   * Get the output sequence from the environment.
   * @return output as sequence of values
   */
  def getOutput(): List[OakValue] = output.toList

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
  def receiveOutput(value: Seq[OakValue]) = {
    value.foreach {
      v => output += v
    }
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
  def prependOutput(pre: List[OakValue]) {
    output = pre.to[ListBuffer] ++ getOutput
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

  /**
   * Join this branch environment with another branch environment.
   * @param that BranchEnv instance to join with
   * @return merged Environment instance
   */
  final def join(that: Environment): Environment = {

    /* Assert that both this and that environment link to the 
     * same parent environment. */
    //assert(getParent eq that.getParent)

    /* Create a new result environment of the same tyoe
     * as the parent environment. 
     * 
     * */
    val env = parent.asInstanceOf[Environment] 
    env match {
      case simpleEnv: SimpleEnv => {
        new SimpleEnv(simpleEnv.getParent(), simpleEnv.getCalls(), simpleEnv.getConstraint())
      }
      case funcEnv: FunctionEnv => {
        new FunctionEnv(funcEnv.getParent(), funcEnv.getCalls(), funcEnv.getConstraint())
      }
      case branchEnv: BranchEnv => {
        new BranchEnv(branchEnv.getParent(), branchEnv.getCalls(), branchEnv.getConstraint())
      }
      case obi: ObjectEnv => {
        new ObjectEnv(obi.getParent(), obi.getCalls(), obi.getConstraint(), obi.obj)
      }
      case null => new SimpleEnv(null, new Stack[String], "true")

    }

    /* Compute symbolic output of merged branches and add
     	* it to the output of the result environment. */
    val symbolicOutput = joinOutput(this, that)
    symbolicOutput.foreach {
      o =>
        {
          env.addOutput(o)
        }
    }

    /* Compute the merged map of variables and add
     *  the map to the result environment. 
     *  */
    val joinedVar = joinVariableMaps(this, that)
    joinedVar.keySet.foreach {
      key =>
        {
          /* Assert that the variables are defined. */
          assert(joinedVar.get(key).nonEmpty)
          env.update(key, joinedVar.get(key).get)
        }
    }
    return env
  }

  /**
   * Merges two program states variable store. Variables
   * will refer to symbolic values if their definition is ambiguous or
   * they're only defined once.
   */
  private def joinVariableMaps(env1: Environment, env2: Environment): Map[String, OakValue] = {
    val m1 = env1.getVariables
    val m2 = env2.getVariables
    /**
     * Map for joined program states
     *  - get all keys that are in m1 but not in m2
     *  - get all keys that are in m2 but not in m1
     *  - get all keys that are in both m1 and m2
     */
    var smap = Map[String, OakValue]()

    (m1.keySet -- m2.keySet).foreach {
      key => smap += key -> Choice(env1.getConstraint, env1.lookup(key), null)
    }

    (m2.keySet -- m1.keySet).foreach {
      key => smap += key -> Choice(env2.getConstraint, env2.lookup(key), null)
    }

    (m1.keySet intersect m2.keySet).foreach { key =>
      if (!env1.lookup(key).toString.equals(env2.lookup(key).toString)) {
        smap += key -> Choice(env1.getConstraint, env1.lookup(key), env2.lookup(key))
      } else {
        smap += key -> env1.lookup(key)
      }
    }
    return smap
  }

  /**
   * Finds the longest common prefix/sequence of
   * elements on two given stacks.
   */
  private def commonPrefix(s: List[OakValue], t: List[OakValue], out: List[OakValue] = List[OakValue]()): List[OakValue] = {
    if (s.isEmpty || t.isEmpty || !s(0).equals(t(0))) {
      return out
    } else {
      commonPrefix(s.slice(1, s.size), t.slice(1, t.size), out.::(s(0)))
    }
  }

  /**
   * Merges output stacks of two program states.
   */
  private def joinOutput(env1: Environment, env2: Environment): List[OakValue] = {

    val s1 = env1.getOutput
    val s2 = env2.getOutput

    val prefix = commonPrefix(s1.reverse, s2.reverse)
    val output1 = s1.slice(prefix.size, s1.size)
    val output2 = s2.slice(prefix.size, s2.size)

    if (compareStacks(output1, output2)) {
      return prefix ++ output1
    } else {
      var stv1 = if (output1.size > 1) new OakValueSequence(output1.toList) else if (output1.size == 1) output1.head else NullValue("AbstractEnv::joinOutput")
      var stv2 = if (output2.size > 1) new OakValueSequence(output2.toList) else if (output2.size == 1) output2.head else NullValue("AbstractEnv::joinOutput")

      return prefix.::(Choice(env1.getConstraint, stv1, stv2))
    }
  }

  /**
   * Recursively compares two stacks of Value objects. Two stacks are
   * equal if their size is equal, their top element is equal and the
   * stack.pop stack are equal (-> recursive definition).
   */
  private def compareStacks(s1: List[OakValue], s2: List[OakValue]): Boolean = {
    var a = s1
    var b = s2
    if (a.isEmpty != b.isEmpty) {
      return false
    }
    if (a.isEmpty && b.isEmpty) {
      return true
    }
    val element_a = a.head
    a = a.tail
    val element_b = b.head
    b = b.tail
    try {
      if (((element_a == null) && (element_b != null)) || (!element_a.equals(element_b)))
        return false
      return compareStacks(a, b)
    } finally {
      a = a.::(element_a)
      b = b.::(element_b)
    }
  }

  def unset(name: String) {
    OakHeap.unsetVariable(getRef(name))
    variables -= name
  }

  def ifdefy(): List[String] = {
    var res = List[String]()
    output.foreach { n => res = res ++ ifdefy(n) }
    return res
  }
  
  def getParents(): List[Environment] = {
    val parents = new ListBuffer[Environment]
    var cParent = this.parent
    while (cParent != null) {
      parents += cParent.asInstanceOf[Environment]
      cParent = cParent.asInstanceOf[Environment].getParent()
    }
    parents.toList
  }
}

/**
 * Includes ~static~ methods used by any environment.
 */
object Environment {

  var globals = Map[String, OakValue]()

  /**
   * Map of class definitions. All classes defined during the program execution
   * are stored here.
   */
  var classDefs = Map[String, ClassDef]()

  /**
   * Creates a new function environment that is used to
   * execute a function call. The function call is documented
   * via the environments call stack.
   *
   * @param dis Parent environment to bounce output to
   * @param f Name of the function
   * @return FunctionEnv
   */
  def createFunctionEnvironment(dis: Environment, f: String): FunctionEnv = {
    return new FunctionEnv(dis, dis.getCalls push f, dis.getConstraint)
  }

  /**
   * Creates a new object environment that is used to
   * execute a function call.
   *
   * @param dis Parent environment to bounce output to
   * @param f Name of the function
   * @return ObjectEnv
   */
  def createObjectEnvironment(dis: Environment, obj: ObjectValue): ObjectEnv = {
    return ObjectEnv(dis, dis.getCalls(), dis.getConstraint, obj)
  }

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

  def joinN(parent: Environment, envs: List[BranchEnv], default: BranchEnv): Environment = {

    def choice(varmap: Map[String, OakValue], defaultValue: OakValue): Choice = {
      val keyIterator = varmap.keySet.toIterator
      var currentkey = keyIterator.next
      var choice = Choice(currentkey.toString, varmap.get(currentkey).get, defaultValue)
      while (keyIterator.hasNext) {
        currentkey = keyIterator.next
        choice = Choice(currentkey.toString, varmap.get(currentkey).get, choice)
      }
      choice
    }

    /* Identify all variable names */
    val variableNames = {
      var buffer = new HashSet[String]
      envs.foreach {
        case env => buffer = buffer union env.getVariables.keySet
      }
      buffer.toSet
    }

    /* find out, which variables changed by merging all update sets */
    val changed = envs.map {
      env => env.getUpdates
    }.foldLeft(Set[String]())(_ union _)

    /* Generate a choice for any changed variable */
    var choices = Map[String, Choice]()
    changed.foreach {
      c =>
        {
          var cMap = Map[String, OakValue]() // Constraint -> Value
          envs.foreach {
            env =>
              {
                val value = try {
                  env.lookup(c)
                } catch {
                  case _ => NullValue("Environment::joinN")
                }
                cMap += (env.getConstraint -> value)
              }
          }
          val defaultValue = if (default != null) {
            try {
              default.lookup(c)
            } catch {
              case _ => NullValue("Environment::joinN")
            }
          } else {
            NullValue("Environment::joinN")
          }
          choices += (c -> choice(cMap, defaultValue))
        }
    }

    /* Merge output */
    val outputChoice = {
      var outputMap = Map[String, OakValueSequence]()
      envs.foreach {
        env => outputMap += (env.getConstraint -> new OakValueSequence(env.getOutput))
      }
      val defaultOutput = if (default != null) {
        try {
          new OakValueSequence(default.getOutput)
        } catch {
          case _ => NullValue("Environment::joinN")
        }
      } else {
        NullValue("Environment::joinN")
      }
      choice(outputMap, defaultOutput)
    }

    parent.addOutput(outputChoice)
    choices.foreach {
      case (name, choice) => {
        parent.update(name, choice)
      }
    }

    parent
  }

  def addToGlobal(name: String, value: OakValue = NullValue("AbstractEnv::addToGlobal")) {
    globals += (name -> value)
  }

  /**
   * Adds a class definition to the environment.
   * @param value ClassDef to add
   */
  def addClass(value: ClassDef): Unit = {
    classDefs += (value.getName -> value)
  }

  /**
   * Looks up a class definition in the environment.
   * @param name Name of the class
   * @return corresponding class definition
   */
  def getClass(name: String): ClassDef = {
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