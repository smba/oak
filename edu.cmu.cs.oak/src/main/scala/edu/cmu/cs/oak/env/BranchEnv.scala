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
import edu.cmu.cs.oak.value.Reference
import org.slf4j.LoggerFactory
import edu.cmu.cs.oak.value.MapChoice
import edu.cmu.cs.oak.value.ObjectValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.MapChoice

/**
 * This class encapsulates all merging functionality used for branching
 * control flow.
 *
 * @author Stefan Muehlbauer <s.muehlbauer@andrew.cmu.edu>
 */
class BranchEnv(parent: Environment, calls: Stack[Call], constraint: Constraint) extends Environment(parent: Environment, calls: Stack[Call], constraint: Constraint) {

  //New (conditional) class definitions
  var updatedClassDefs = Set[String]()

  override def toString() = "BranchEnv" + this.hashCode() + "[" + this.constraint + "]"

  /**
   * Utility method, checks whether a given constant name is defined i
   * this branch environment.
   *
   * @param name String name of the constant definition
   * @return is defined in this environment?
   */
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
      SelectNode(Map(envs.head.output -> constraints.head, envs(1).output -> constraints.head.NOT()))
    } else {
      SelectNode(Map(envs.head.output -> constraints.head, joinOutput(envs.tail, constraints.tail) -> constraints.head.NOT()))
    }
  }

  /**
   * Merges the variable values of two or more environments by constructing a
   * Choice value that represents the variational value.
   *
   * @param envs List of BranchEnvironments of which we want to merge
   * @param constraints List of constraints of the environments we want to merge
   * @param variable Name of the variable
   *
   * @return Merged value (should be a Choice value)
   *
   * TODO XXX Implement flat
   *
   */
  private def joinVariable(envs: List[BranchEnv], constraints: List[Constraint], variable: String): OakValue = {

    /*
     * First, conservatively create a choice (or whatever) value from
     * the given environments. This value will consequently be optimized 
     * in case a choice contains more than one object value of the same class.
     */
    val joined_value = if ((envs.size == 2) && (constraints.size == 1)) {
      val a = try {
        if (envs(0).hasChanged) {
          envs(0).lookup(variable)
        } else {
          NullValue
        }
      } catch {
        case vnfe: VariableNotFoundException => NullValue
      }
      val b = try {
        if (envs(1).hasChanged) {
          envs(1).lookup(variable)
        } else {
          NullValue
        }
      } catch {
        case vnfe: VariableNotFoundException => NullValue
      }
      if ((a == null) || (NullValue equals a) && (NullValue equals b) || (b == null)) {
        NullValue
      } else {
        Choice.optimized(constraints(0), a, b)
      }
    } else {
      Choice.optimized(constraints(0), try {
        if (envs(0).hasChanged) {
          envs(0).lookup(variable)
        } else { NullValue }
      } catch {
        case vnfe: VariableNotFoundException => NullValue
      }, joinVariable(envs.tail, constraints.tail, variable))
    }

    /*
     * TODO Optimization: Propagate choices to the leaves!
     * 
     * 	Choice(
     * 		Class1(name = 'A') -> φ,
     * 		Class1(name = 'B') -> χ,
     * 		Class1(name = 'C') -> ψ,
     * 		Class2(name = 'Z') -> ω,
     * 	)
     * 
     * will be transformed to
     * 
     * 	Choice(
     * 		Class1(name = Choice(
     * 			'A' -> φ
     * 			'B' -> χ
     * 			'C' -> ψ
     * 		)) -> (φ || χ || ψ),
     * 		Class2(name = 'Z') -> ω
     * 	)
     */

//    return (joined_value match {
//      case mc: MapChoice => {
//
//        // map of all objects in the MapChoice
//        val objects = mc.toMap().filter(p => p._1.isInstanceOf[ObjectValue]).map { case (k, v) => (k.asInstanceOf[ObjectValue], v) }
//
//        // group map elements by class definition name (class)
//        val grouped = objects.groupBy { _._1.getClassDef().getName() }
//
//        grouped.map {
//          case (class_name, instances) => {
//            (class_name -> joinObjects(instances, env))
//          }
//        }
//        null
//      }
//      case _ => joined_value
//    })
    return joined_value
  }

  def joinObjects(instances: Map[ObjectValue, Constraint], env: Environment): ObjectValue = {
    val class_def = instances.head._1.getClassDef()
    val o = instances.head._1

    class_def.fields.keys.foreach {
      s =>
        {
          var map = Map[OakValue, Constraint]()
          instances.foreach {
            case (object_value, constraint) => {
              val field_value = object_value.getFields().get(StringValue(s, "", 0), env)
              if (map.keySet.contains(field_value)) {
                map += (field_value -> map.get(field_value).get.AND(constraint))
              } else {
                map += (field_value -> constraint)
              }
            }
          }
          val mc = new MapChoice(map)
          o.set(s, mc, env)
        }
    }
    return o
  }

  /**
   * Array values use references (i.e, Reference instances) internally to point to
   * their elements. Since array (may) change during the execution, we union all
   * environments heaps (Reference -> OakValue) in order to preserve the references.
   *
   * @param envs List of BranchEnvironments of which we want to merge
   * @return mapping from References to OakValues of all BranchEnvs passed
   *
   */
  private def joinHeaps(envs: List[BranchEnv]): AnyRefMap[Reference, OakValue] = {
    def merge[A <: AnyRef, B](ms: List[collection.mutable.Map[A, B]]): AnyRefMap[A, B] = {
      val d = AnyRefMap[A, B]()
      ms.foreach { m => m.foreach { case (k, v) => d.put(k, v) } }
      d
    }
    merge(envs.map { env => env.references })
  }

  private def joinStaticClassField(envs: List[BranchEnv], constraints: List[Constraint], className: String, fieldName: String): OakValue = {
    if ((envs.size == 2) && (constraints.size == 1)) {
      Choice.optimized(constraints(0), try {
        envs(0).getStaticClassField(className, fieldName)
      } catch {
        case vnfe: NoSuchElementException => NullValue
      }, try {
        envs(1).getStaticClassField(className, fieldName)
      } catch {
        case vnfe: NoSuchElementException => NullValue
      })
    } else {
      Choice.optimized(constraints(0), try {
        envs(0).getStaticClassField(className, fieldName)
      } catch {
        case vnfe: NoSuchElementException => NullValue
      }, joinStaticClassField(envs.tail, constraints.tail, className, fieldName))
    }
  }

  private def joinConstants(envs: List[BranchEnv], constraints: List[Constraint], cname: String): OakValue = {
    val c1 = if (envs(0).definesConstant(cname)) envs(0).getConstant(cname) else NullValue
    if ((envs.size == 2) && (constraints.size == 1)) {
      val c2 = if (envs(1).definesConstant(cname)) envs(1).getConstant(cname) else NullValue
      Choice.optimized(constraints(0), c1, c2)
    } else {
      Choice.optimized(constraints(0), c1, joinConstants(envs.tail, constraints.tail, cname))
    }
  }

  private def joinFunctionDefs(envs: List[BranchEnv]): AnyRefMap[String, FunctionDef] = {
    val functions = AnyRefMap[String, FunctionDef]()
    envs.map(env => env.funcs).foreach {
      funcs =>
        funcs.foreach {
          case (fname, fdef) => {
            if (!functions.get(fname).isEmpty) {
              //            logger.warn("Duplicate function name " + fname + "().")
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
      funcs =>
        funcs.foreach {
          case (cname, cdef) => {
            if (!functions.get(cname).isEmpty) {
              //            logger.warn("Duplicate class definition name " + cname + ".")
            } else {
              functions.put(cname, cdef)
            }

          }
        }
    }
    functions
  }

  /**
   * Recursively merges different parallel branches to a single environment and exports
   * the environments Delta. The join operation for branches considers
   *
   * 	1) Output (D Model)
   * 	2) References
   * 	3) Variables
   * 	4) Global variables
   * 	5) Static class fields
   * 	6) Constant definitions
   * 	7) Function definitions (not conditional)
   *  8) Class definitions (not conditional)
   *  
   *  9) [For coverage evaluation only!] Touched string literals
   *  10) [optional] Include history
   *
   * @param envs List[BranchEnv] list of branch environments to be joined, each environment is a parallel branch
   * @param constraints List[Constraint] list of conditions for the corresponding branches
   */
  def join(envs: List[BranchEnv], constraints: List[Constraint]): Delta = {

    // 1) Join output
    val joinedOutput = joinOutput(envs, constraints)

    // 2) References
    val joinedHeap = joinHeaps(envs)

    // 3) Variables
    val updatedVariableNames = envs.map { env => env.variables.map(vv => vv._1).toSet }.foldLeft(Set[String]())(_ union _)
    var updatedVariableMap = AnyRefMap[String, OakValue]()
    updatedVariableNames.foreach {
      name =>
        {
          updatedVariableMap += (name -> joinVariable(envs, constraints, name))
        }
    }

    // 4) Global variables
    val allGlobals = envs.map { e => e.globalVariables }.fold(Set[String]())(_ union _).toSet

    // 5) Static class fields
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

    // 6) Constant definitions
    val updated = envs.map { e => e.constants.keySet }.fold(Set[String]())(_ union _).toSet
    val constants = updated.map { cname => (cname -> joinConstants(envs, constraints, cname)) }.toMap

    // 7) Function definitions
    val functions = joinFunctionDefs(envs)

    // 8) Class definitions
    val classes = joinClassDefs(envs)

    // 9) Touched String literals
    val touched = envs.map(env => env.touched).fold(Set[StringValue]())(_ union _).toSet
    
    // 10)
    val include_expressions = envs.map(env => env.include_history)
    val keys = include_expressions.map(e => e.keySet).fold(Set[(String, Int)]())(_ union _)
    val history = keys.map(k => (k, include_expressions.map(m => m.getOrElse(k, false)).fold(false)(_ || _))).toMap

    // Create and export new Delta instance
    new Delta(joinedOutput, updatedVariableMap, joinedHeap, updatedFieldz, allGlobals, constants, functions, classes, touched, history)
  }
}
