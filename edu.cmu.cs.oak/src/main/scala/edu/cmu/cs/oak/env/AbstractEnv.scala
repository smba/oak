package edu.cmu.cs.oak.env

import scala.collection.immutable.Stack
import scala.collection.mutable.ListBuffer

import org.slf4j.LoggerFactory

import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.ClassDef
import edu.cmu.cs.oak.value.FunctionDef
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakValueSequence
import edu.cmu.cs.oak.value.OakVariable
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.env.heap.OakHeap
import edu.cmu.cs.oak.exceptions.VariableNotFoundException
import edu.cmu.cs.oak.value.NullValue

/**
 * Programs state and program state operations.
 *
 * @author Stefan Muehlbauer <smuhlbau@andrew.cmu.edu>
 */
abstract class AbstractEnv(parent: EnvListener, calls: Stack[String], constraint: String) extends Environment {

  /**
   * Map of variable identifiers and variable references.
   * In various contexts, variables can refer to the same value.
   * For variable reference handling {@see {@link #edu.cmu.cs.oak.env.OakHeap}}.
   */
  var variables = Map[String, OakVariable]()

  /**
   * Mutable output trace. All output genereated in this environment will
   *  be cached and sent to the parent environment unless this is the global
   *  environment (parent == null).
   */
  var output = new ListBuffer[OakValue]()

  /**
   * Logger instance for environments.
   */
  val logger = LoggerFactory.getLogger(classOf[AbstractEnv])

  override def update(name: String, value: OakValue) {

    // global?
    if (AbstractEnv.globals.keySet contains name) {
      AbstractEnv.globals += (name -> value)
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

  override final def lookup(name: String): OakValue = {

    // global?
    if (AbstractEnv.globals.keySet contains name) {
      return AbstractEnv.globals.get(name).get
    }

    val variable = {
      val opt = variables.get(name)
      if (!opt.isEmpty) {
        opt.get
      } else {
        throw new VariableNotFoundException("Unassigned variable " + name + ".")
      }
    }
    val ret = try {
      OakHeap.extract(variable)
    } catch {
      case e: Exception => throw new RuntimeException(e)
    }
    if (ret == null) {
      System.err.println(this.variables.keySet contains "$locales")
      throw new RuntimeException(name)
    }
    ret
  }

  override def unset(name: String) {
    OakHeap.unsetVariable(getRef(name))
    variables -= name
  }

  /**
   * Creates a D Model tree of the (symbolic) output that
   * can easily be traversed.
   *
   * @return Root node of the D Model tree
   */
  final override def getOutputModel(): DNode = DNode.createDNode(output.toList)

  override def receiveOutput(value: Seq[OakValue]) = {
    value.foreach {
      v => output += v
    }
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

  override def setRef(name: String, ref: OakVariable): Unit = {
    variables += (name -> ref)
  }

  override def getRef(name: String): OakVariable = {
    variables.get(name).get
  }

  /**
   * Join this branch environment with another branch environment.
   *
   * @param that BranchEnv instance to join with
   *
   * @return merged Environment instance
   */
  override final def join(that: Environment): Environment = {

    /* Assert that both this and that environment link to the 
     * same parent environment. */
    //assert(getParent eq that.getParent)

    /* Create a new result environment of the same tyoe
     * as the parent environment. 
     * 
     * */
    val env = parent.asInstanceOf[Environment] match {
      case simpleEnv: SimpleEnv => {
        new SimpleEnv(simpleEnv.getParent(), simpleEnv.getCalls(), simpleEnv.getConstraint())
      }
      case funcEnv: FunctionEnv => {
        new FunctionEnv(funcEnv.getParent(), funcEnv.getCalls(), funcEnv.getConstraint())
      }
      case branchEnv: BranchEnv => {
        new BranchEnv(branchEnv.getParent(), branchEnv.getCalls(), branchEnv.getConstraint())
      }
      case null => new SimpleEnv(null, new Stack[String], "true")

    }

    /* Compute symbolic output of merged branches and add
     * it to the output of the result environment. */
    val symbolicOutput = joinOutput(this, that)
    symbolicOutput.foreach {
      o => env.addOutput(o)
    }

    /* Compute the merged map of variables and add
     *  he map to the result environment. 
     *  
     *  TODO Tag 'dirty' variables and only compare neccessary variables
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
   * Finds the longest common prefix/sequence of elements on two given stacks.
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
      var stv1 = if (output1.size > 1) OakValueSequence(output1.toList) else if (output1.size == 1) output1.head else NullValue("")
      var stv2 = if (output2.size > 1) OakValueSequence(output2.toList) else if (output2.size == 1) output2.head else NullValue("")

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

  final override def prependOutput(pre: List[OakValue]) { output = pre.to[ListBuffer] ++ getOutput }
  final override def addFunction(value: FunctionDef) { OakHeap.defineFunction(value) }
  final override def getFunction(name: String): FunctionDef = { OakHeap.getFunction(name) }
  final override def addOutput(value: OakValue) { output += value }

  def ifdefy(): List[String] = {
    var res = List[String]()
    output.foreach { n => res = res ++ ifdefy(n) }
    return res
  }

  def toXml = {
    <state>
      {
        for (key <- variables.keySet) yield <variable>
                                              <key>{ key }</key>
                                              <value>{ OakHeap.extract(variables.get(key).get).toXml }</value>
                                            </variable>
      }
    </state>
  }

  /* ----- Getter methods ----- */
  final override def getCalls(): Stack[String] = calls
  final override def getParent(): EnvListener = parent
  final override def getOutput(): List[OakValue] = output.toList
  final override def getVariables(): Map[String, OakVariable] = variables
  final override def getConstraint(): String = constraint
}

object AbstractEnv {

  var globals = Map[String, OakValue]()
  
  
  /**
   * Map of class definitions. All classes defined during the program execution
   * are stored here.
   */
  var classDefs = Map[String, ClassDef]()

  def addToGlobal(name: String) {
    globals += (name -> NullValue(""))
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

}