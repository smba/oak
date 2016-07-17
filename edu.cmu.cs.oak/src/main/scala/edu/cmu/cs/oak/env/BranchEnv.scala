package edu.cmu.cs.oak.env

import scala.collection.immutable.Map
import scala.collection.immutable.Stack
import scala.collection.mutable.AnyRefMap

import edu.cmu.cs.oak.exceptions.VariableNotFoundException
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.nodes.SelectNode
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakVariable
import org.slf4j.LoggerFactory

/**
 * This class encapsulates all merging functionality used for branching
 * control flow.
 *
 * @author Stefan Muehlbauer <s.muehlbauer@andrew.cmu.edu>
 */
class BranchEnv(parent: Environment, calls: Stack[Call], constraint: Constraint) extends Environment(parent: Environment, calls: Stack[Call], constraint: Constraint) {

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

  override def toString() = "BranchEnv" + this.hashCode() + "[" + this.constraint + "]"

  def definesConstant(name: String): Boolean = {
    (this.constants.contains(name))
  }

}
/**
 * Static methods used for the join operation of BranchEnvs.
 */
object BranchEnv {

  val logger = LoggerFactory.getLogger(classOf[BranchEnv])
  
  /**
   * Merges the output of two or more environments by constructing a
   * SelectNode that represents the variational output.
   *
   * @param envs List of BranchEnvironments of which we want to merge
   * @param constraints List of constraints of the environments we want to merge
   * @return merged output
   */
  private def joinOutput(envs: List[BranchEnv], constraints: List[Constraint]): DNode = {
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
   * @param constraints List of constraints of the environments we want to merge
   * @param variable Name of the variable
   * @return Merged value (should be a Choice value)
   *
   */
  private def joinVariable(envs: List[BranchEnv], constraints: List[Constraint], variable: String): OakValue = {
    if ((envs.size == 2) && (constraints.size == 1)) {
      val a = try {
        if (envs(0).hasChanged) {
          envs(0).lookup(variable)
        } else { NullValue("joinV") }
      } catch {
        case vnfe: VariableNotFoundException => NullValue("Vnf")
      }
      val b = try {
        if (envs(1).hasChanged) {
          envs(1).lookup(variable)
        } else { NullValue("joinV") }
      } catch {
        case vnfe: VariableNotFoundException => NullValue("Vnf")
      }
      if (a.isInstanceOf[NullValue] && b.isInstanceOf[NullValue]) {
        NullValue("joinV")
      } else {
        Choice.optimized(constraints(0), a, b)
      }
    } else {
      Choice.optimized(constraints(0), try {
        if (envs(0).hasChanged) {
          envs(0).lookup(variable)
        } else { NullValue("joinV") }
      } catch {
        case vnfe: VariableNotFoundException => NullValue("Vnf")
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
   */
  private def joinHeaps(envs: List[BranchEnv]): AnyRefMap[OakVariable, OakValue] = {
    def merge[A <: AnyRef, B](ms: List[collection.mutable.Map[A, B]]): AnyRefMap[A, B] = {
      val d = AnyRefMap[A, B]()      
      ms.foreach { m => m. foreach { case (k, v) => d.put(k, v) } }
      d
    }
    merge(envs.map { env => env.references })
  }

  private def joinStaticClassField(envs: List[BranchEnv], constraints: List[Constraint], className: String, fieldName: String): OakValue = {
    if ((envs.size == 2) && (constraints.size == 1)) {
      Choice.optimized(constraints(0), try {
        envs(0).getStaticClassField(className, fieldName)
      } catch {
        case vnfe: NoSuchElementException => NullValue("joinStCF")
      }, try {
        envs(1).getStaticClassField(className, fieldName)
      } catch {
        case vnfe: NoSuchElementException => NullValue("joinStCF")
      })
    } else {
      Choice.optimized(constraints(0), try {
        envs(0).getStaticClassField(className, fieldName)
      } catch {
        case vnfe: NoSuchElementException => NullValue("joinStCF")
      }, joinStaticClassField(envs.tail, constraints.tail, className, fieldName))
    }
  }

  private def joinConstants(envs: List[BranchEnv], constraints: List[Constraint], cname: String): OakValue = {
    val c1 = if (envs(0).definesConstant(cname)) envs(0).getConstant(cname) else NullValue("")
    if ((envs.size == 2) && (constraints.size == 1)) {
      val c2 = if (envs(1).definesConstant(cname)) envs(1).getConstant(cname) else NullValue("")
      Choice.optimized(constraints(0), c1, c2)
    } else {
      Choice.optimized(constraints(0), c1, joinConstants(envs.tail, constraints.tail, cname))
    }
  }

  private def joinFunctionDefs(envs: List[BranchEnv]): AnyRefMap[String, FunctionDef] = {
    val functions = AnyRefMap[String, FunctionDef]()
    envs.map(env => env.funcs).foreach {
      funcs => funcs.foreach {
        case (fname, fdef) => {
          if (!functions.get(fname).isEmpty) {
            logger.warn("Duplicate function name " + fname + "().")
          } else {
            functions.put(fname, fdef)
          }
          
        }
      }
    }
    functions
  }
  
  private def joinClassDefs(envs: List[BranchEnv]): AnyRefMap[String, ClassDef] = {
    val functions = AnyRefMap[String, ClassDef]()
    envs.map(env => env.classDefs).foreach {
      funcs => funcs.foreach {
        case (cname, cdef) => {
          if (!functions.get(cname).isEmpty) {
            logger.warn("Duplicate class definition name " + cname + ".")
          } else {
            functions.put(cname, cdef)
          }
          
        }
      }
    }
    functions
  }
  
  def join(envs: List[BranchEnv], constraints: List[Constraint]): Delta = {

    /* 1) JOIN UPDATED VARIABLES
     * All variables that have been changed during at least one branch execution
     * are selected and joined separately.
     */
    val updatedVariableNames = envs.map { env => env.variables.map(vv => vv._1).toSet }.foldLeft(Set[String]())(_ union _)
    var updatedVariableMap = AnyRefMap[String, OakValue]()
    updatedVariableNames.foreach {
      name =>
        {
          updatedVariableMap += (name -> joinVariable(envs, constraints, name))
        }
    }

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
     * 4) Global variables
     */
    val allGlobals = envs.map { e => e.globalVariables }.fold(Set[String]())(_ union _).toSet

    /**
     * 5) Static class fields
     */
    val fieldNames = collection.mutable.Set[(String, String)]()
    envs.foreach {
      env =>
        env.staticClassFields.foreach {
          case (c, m) => {
            m.keySet.foreach { k => fieldNames.add((c, k)) }
          }
        }
    }
    var updatedFields = AnyRefMap[String, collection.mutable.Map[String, OakValue]]()
    val merged = fieldNames.foreach {
      case (c, f) => {
        if (!(updatedFields.keySet contains c)) {
          updatedFields.put(c, collection.mutable.Map[String, OakValue]())
        }
        updatedFields.get(c).get.put(f, joinStaticClassField(envs, constraints, c, f))
      }
    }
    val updatedFieldz = updatedFields.map { case (k, m) => (k -> m.toMap) }.asInstanceOf[collection.mutable.Map[String, Map[String, OakValue]]]

    // 6) constants
    val updated = envs.map { e => e.constants.keySet }.fold(Set[String]())(_ union _).toSet
    val constants = updated.map { cname => (cname -> joinConstants(envs, constraints, cname)) }.toMap

    
    // 7) Function definitions
    val functions = joinFunctionDefs(envs)
    
    // 8) Class definition
    val classes = joinClassDefs(envs)
    
    new Delta(joinedOutput, updatedVariableMap, joinedHeap, updatedFieldz, allGlobals, constants, functions, classes)
  }
}
