package edu.cmu.cs.oak.env

import scala.collection.immutable.{Map, Stack}

/**
 * Implementation of a program state.
 */
class SimpleEnvironment(parent: Environment,
                        variables: Map[String, OakValue],
                        output:    Stack[OakValue],
                        calls:     Stack[String],
                        constraint: String,
                        functions: Map[String, FunctionDef]) extends Environment {

  override def getParent = parent

  override def getVariables = variables

  override def getOutput = output

  override def getCalls = calls

  override def getConstraint = constraint

  override def getFunctions = functions
  
  override def update(name:String, value:OakValue): Environment = {
    return new SimpleEnvironment(parent, variables + (name -> value), output, calls, constraint, functions)
  }
  
  override def lookup(name:String): OakValue = {
    val opt = variables.get(name)
    if (!opt.isEmpty) {
      return opt.get
    } else {
      println(getVariables)
      throw new RuntimeException("Variable " + name + " is undefined.")
    }
  }
  
  override def addOutput(value:OakValue): Environment = {
    return new SimpleEnvironment(parent, variables, output.push(value), calls, constraint, functions)
  }
  
  override def join(env:Environment): Environment = {
    val vars = joinVariableMaps(this, env)
    val outp = joinStacks(this, env)
    return new SimpleEnvironment(parent, vars, outp, calls, constraint, functions)
  }
  
  /**
   * Finds the longest common prefix/sequence of elements on two given stacks.
   */
  private def commonPrefix(s: Stack[OakValue], t: Stack[OakValue], out: Stack[OakValue] = Stack[OakValue]()): Stack[OakValue] = {
    if (s.isEmpty || t.isEmpty || !s(0).equals(t(0))) {
      return out
    } else {
      commonPrefix(s.slice(1, s.size), t.slice(1, t.size), out.push(s(0)))
    }
  }
  
  /**
   * Merges output stacks of two program states.
   */
  private def joinStacks(env1:Environment, env2:Environment): Stack[OakValue] = {
    
    val s1 = env1.getOutput
    val s2 = env2.getOutput
    
    val prefix = commonPrefix(s1.reverse, s2.reverse)
    val output1 = s1.reverse.slice(prefix.size, s1.size)
    val output2 = s2.reverse.slice(prefix.size, s2.size)

    if (compareStacks(output1, output2)) {
      return prefix ++ output1
    } else {
      var stv1 = StringValue(output1.foldLeft("")((a, b) => a + b))
      var stv2 = StringValue(output2.foldLeft("")((a, b) => a + b))

      return prefix.push(Choice(env1.getConstraint, stv1, stv2)).reverse
    }
  }
  
  /**
   * Recursively compares two stacks of Value objects. Two stacks are
   * equal if their size is equal, their top element is equal and the
   * stack.pop stack are equal (-> recursive definition).
   */
  private def compareStacks(s1: Stack[OakValue], s2: Stack[OakValue]): Boolean = {
    var a = s1
    var b = s2
    if (a.isEmpty != b.isEmpty) {
      return false
    }
    if (a.isEmpty && b.isEmpty) {
      return true
    }
    val element_a = a.top
    a = a.pop
    val element_b = b.top
    b = b.pop
    try {
      if (((element_a == null) && (element_b != null)) || (!element_a.equals(element_b)))
        return false
      return compareStacks(a, b)
    } finally {
      a = a.push(element_a)
      b = b.push(element_b)
    }
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
      key => smap += key -> Choice( env1.getConstraint, m1.get(key).get, null )
    }

    (m2.keySet -- m1.keySet).foreach {
      key => smap += key -> Choice( env2.getConstraint , m2.get(key).get, null)
    }

    (m1.keySet intersect m2.keySet).foreach { key =>
      if (!m1.get(key).get.toString().equals(m2.get(key).get.toString())) {
        smap += key -> Choice( env1.getConstraint , m1.get(key).get, m2.get(key).get)
      } else {
        smap += key -> m1.get(key).get
      }
    }
    return smap
  }

  // TODO use real constraints
  override def fork(constraintS: String): (Environment, Environment) = {
    return (new BranchEnvironment(parent, variables, output, calls, constraint + " && " + constraintS, functions), new BranchEnvironment(parent, variables, output, calls, constraint + " && NOT(" + constraintS + ")", functions))
  }

  // TODO use real constraints
  override def withConstraint(constraintS: String): Environment = {
    return new SimpleEnvironment(parent, variables, output, calls, constraint + " && " + constraintS, functions)
  }

  override def getFunctionEnv(functionDef: FunctionDef): Environment = {
    return new FunctionEnv(this, Map[String, OakValue](), new Stack[OakValue], getCalls.push(functionDef.getName), getConstraint(), getFunctions())
  }

}