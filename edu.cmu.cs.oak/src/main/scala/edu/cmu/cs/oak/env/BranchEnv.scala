package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.{ Choice, OakValue, StringValue }

import scala.collection.immutable.{ Map, Stack }
import edu.cmu.cs.oak.value.OakVariable
import scala.collection.mutable.ListBuffer
import edu.cmu.cs.oak.value.OakValueSequence
import scala.collection.mutable.HashSet
import edu.cmu.cs.oak.nodes.SelectNode
import edu.cmu.cs.oak.nodes.ConcatNode
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.value.NullValue

/**
 * This class encapsulates all merging functionality used for branching
 * control flow.
 * 
 * @author Stefan Muehlbauer <s.muehlbauer@andrew.cmu.edu>
 */
class BranchEnv(parent: EnvListener, calls: Stack[String], constraint: String) extends Environment(parent: EnvListener, calls: Stack[String], constraint: String) {

  /**
   * Set of changed ("dirty") variables. These variables are considered
   * when merging different environments.
   */
  var updates = Set[String]()
  
  /**
   * In addition to {@see edu.cmu.cs.oak.env.Environment#update}, we
   * keep track of the modification of variables.
   */
  override def update(name: String, value: OakValue): Unit = {
    super.update(name, value)
    updates += name
  }
  
  /**
   * Merges the output of two environments by constructing a 
   * SelectNode that represents the variational output.
   * 
   * @param constraint Constraint to construct the SelectNode with.
   * @param env1 Environment #1 to merge output from
   * @param env2 Environment #2 to merge output from
   * @return merged output
   */
  private def joinOutput(constraint: String, env1: Environment, env2: Environment): DNode = {
    SelectNode(constraint, env1.output, env2.output)
  }
  

  /**
   * Join this branch environment with another branch environment.
   * @param that BranchEnv instance to join with
   * @return merged Environment instance
   */
  final def join(constraint: String, that: Environment): Environment = {

    /* Assert that both this and that environment link to the 
     * same parent environment. */

    /* Create a new result environment of the same tyoe
     * as the parent environment. 
     * 
     * */
    val env = parent.asInstanceOf[Environment] 

    /* Compute symbolic output of merged branches and add
     	* it to the output of the result environment. */
    val joinedOutput = joinOutput(constraint, this, that)
    env.addOutput(joinedOutput)

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

object BranchEnv {
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
    
    def select(outputs: List[(String, DNode)], default: DNode): DNode = {
      if (outputs.size == 1) {
        SelectNode(outputs(0)._1, outputs(0)._2, default)
      } else {
        SelectNode(outputs(0)._1, outputs(0)._2, select(outputs.tail, default))
      }
    }

    /* Identify all variable names */
    val variableNames = {
      var buffer = new HashSet[String]
      envs.foreach {
        case env => buffer = buffer union env.getVariables.keySet
      }
      buffer.toSet
    }

    // Find out, which variables changed by merging all update sets 
    val changed = envs.map { env => env.updates }.foldLeft(Set[String]())(_ union _)

    // Generate a choice for any changed variable
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
                  case _: Throwable => NullValue("Environment::joinN")
                }
                cMap += (env.getConstraint -> value)
              }
          }
          val defaultValue = if (default != null) {
            try {
              default.lookup(c)
            } catch {
              case _: Throwable => NullValue("Environment::joinN")
            }
          } else {
            NullValue("Environment::joinN")
          }
          choices += (c -> choice(cMap, defaultValue))
        }
    }

    /* Merge output */
    val outputChoice: DNode = {
      var outputMap = Map[String, DNode]()
      envs.foreach {
        env => outputMap += (env.getConstraint -> env.getOutput)
      }
      val defaultOutput = if (default != null) {
        try {
          default.getOutput
        } catch {
          case _: Throwable => ConcatNode(List())
        }
      } else {
        ConcatNode(List())
      }
      select(outputMap.toList, defaultOutput)
    }

    parent.addOutput(outputChoice)
    choices.foreach {
      case (name, choice) => {
        parent.update(name, choice)
      }
    }

    parent
  }
}