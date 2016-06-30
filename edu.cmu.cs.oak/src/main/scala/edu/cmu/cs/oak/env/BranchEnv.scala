package edu.cmu.cs.oak.env

import scala.collection.immutable.Map
import scala.collection.immutable.Stack

import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.nodes.SelectNode
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakVariable
import edu.cmu.cs.oak.env.Delta
import edu.cmu.cs.oak.exceptions.VariableNotFoundException
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.env.FunctionDef
import edu.cmu.cs.oak.env.ClassDef
import edu.cmu.cs.oak.env.OakHeap
import java.time.Instant
import java.time.Duration

/**
 * This class encapsulates all merging functionality used for branching
 * control flow.
 *
 * @author Stefan Muehlbauer <s.muehlbauer@andrew.cmu.edu>
 */
class BranchEnv(parent: Environment, calls: Stack[Call], constraint: String) extends Environment(parent: Environment, calls: Stack[Call], constraint: String) {

  /**
   * Set of changed ("dirty") variables. These variables are considered
   * when merging different environments.
   */
  //var updates = Set[String]()
  
  /**
   * "New" (conditional) class definitions
   */
  var updatedClassDefs = Set[String]()

  /**
   * In addition to {@see edu.cmu.cs.oak.env.Environment#update}, we
   * keep track of the modification of variables.
   */
  override def update(name: String, value: OakValue): Unit = {
    super.update(name, value)
    //updates += name
  }
  
  override def toString() = "BranchEnv" + this.hashCode() + "[" + this.constraint +"]"

}
/**
 * Static methods used for the join operation of BranchEnvs.
 */
object BranchEnv {

  /**
   * Merges the output of two or more environments by constructing a
   * SelectNode that represents the variational output.
   *
   * @param envs List of BranchEnvironments of which we want to merge
   * @param constraints List of constraints of the environments we want to merge
   * @return merged output
   */
  private def joinOutput(envs: List[BranchEnv], constraints: List[String]): DNode = {
    if ((envs.size == 2) && (constraints.size == 1)) {
      SelectNode(constraints.head, envs.head.output, envs(1).output)
    } else {
      SelectNode(constraints.head, envs.head.output, joinOutput(envs.tail, constraints.tail))
    }
  }

  /**
   * Merges the variable values of two or more environments by constructing a
   * Choice value that represents the variational value.
   *
   * @param envs List of BranchEnvironments of which we want to merge
   * @param constraints List of constraints of the environments we want t
import edu.cmu.cs.oak.analysis.inlcude.OutputGraphListener

case class SelectNode(condition: String, v1: DNode, v2: DNode) extends DNode {
  o merge
   * @param variable Name of the variable
   * @return Merged value (should be a Choice value)
   *
   */
  private def joinVariable(envs: List[BranchEnv], constraints: List[String], variable: String): OakValue = {
    if ((envs.size == 2) && (constraints.size == 1)) {
      Choice(constraints(0), try { 
        envs(0).lookup(variable)
      } catch {
        case vnfe: VariableNotFoundException => NullValue("")
      }, try {
        envs(1).lookup(variable)
      } catch {
        case vnfe: VariableNotFoundException => NullValue("")
      })
    } else {
      Choice(constraints(0), try {
        envs(0).lookup(variable)
      } catch {
        case vnfe: VariableNotFoundException => NullValue("")
      }, joinVariable(envs.tail, constraints.tail, variable))    
    }
  }

  /**
   * Array values use references (i.e, OakVariable instances) internally to point to
   * their elements. Since array (may) change during the execution, we union all
   * environments heaps (OakVariable -> OakValue) in order to preserve the references.
   *
   * @param envs List of BranchEnvironments of which we want to merge
   * @return mapping from OakVariables to OakValues of all BranchEnvs passed
   *
   * 
   */
  private def joinHeaps(envs: List[BranchEnv]): Map[OakVariable, OakValue] = {
    envs.map { env => env.references.toMap } reduce (_ ++ _)
  }
  
  def join(envs: List[BranchEnv], constraints: List[String]): Delta = {

    /* 1) JOIN UPDATED VARIABLES
     * All variables that have been changed during at least one branch execution
     * are selected and joined separately.
     */
    val updatedVariableNames = envs.map { env => env.variables.map(vv=>vv._1).toSet }.foldLeft(Set[String]())(_ union _)
    val updatedVariableMap = updatedVariableNames.map { name => (name, joinVariable(envs, constraints, name)) }.toMap

    /* 2) JOIN (or UNION) HEAP
     * In order to preserve references after the join, we union all references of
     * the BranchEnv's heaps.
     */
    val joinedHeap = joinHeaps(envs)

    /**
     * 3) JOIN OUTPUT
     */
    val joinedOutput = joinOutput(envs, constraints)
    
    /**
     * 4) Globals
     */
    var allGlobals = Set[String]()
    val gloabls = envs.map { e => e.globalVariables.foreach { g => allGlobals += g } }
    
    new Delta(joinedOutput, updatedVariableMap, joinedHeap, allGlobals)
  }
}
