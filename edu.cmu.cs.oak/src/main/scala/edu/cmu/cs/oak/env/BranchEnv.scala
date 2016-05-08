package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.{ Choice, OakValue, StringValue }

import scala.collection.immutable.{ Map, Stack }
import edu.cmu.cs.oak.value.OakVariable
import scala.collection.mutable.ListBuffer
import edu.cmu.cs.oak.value.OakValueSequence

class BranchEnv(parent: EnvListener, calls: Stack[String], constraint: String) extends AbstractEnv(parent: EnvListener, calls: Stack[String], constraint: String) {

  def join(that: BranchEnv): Environment = {

    val symbolicOutput = joinOutput(this, that)
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
    }
    
    symbolicOutput.foreach {
      o => env.addOutput(o)
    }

    val joinedVar = joinVariableMaps(this, that)
    joinedVar.keySet.foreach {

      key => assert(!joinedVar.get(key).get.isInstanceOf[OakVariable]); env.update(key, joinedVar.get(key).get)
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
      if (!env1.lookup(key).toString().equals(env2.lookup(key).toString())) {
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
  private def joinOutput(env1:Environment, env2:Environment): List[OakValue] = {
    
    val s1 = env1.getOutput
    val s2 = env2.getOutput
    
    val prefix = commonPrefix(s1.reverse, s2.reverse)
    val output1 = s1/*.reverse*/.slice(prefix.size, s1.size)
    val output2 = s2/*.reverse*/.slice(prefix.size, s2.size)

    if (compareStacks(output1, output2)) {
      return prefix ++ output1
    } else {
      var stv1 = if (output1.size > 1) OakValueSequence(output1.toList)/*StringValue(output1.foldLeft("")((a, b) => a + b))*/ else if (output1.size == 1) output1.head else null
      var stv2 = if (output2.size > 1) OakValueSequence(output2.toList)/*StringValue(output2.foldLeft("")((a, b) => a + b))*/ else if (output2.size == 1) output2.head else null

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
  
}