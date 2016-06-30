package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.ObjectValue
import scala.collection.immutable.Stack
import edu.cmu.cs.oak.env.OakHeap

/**
 * This environment is used for method execution.
 */
@deprecated case class ObjectEnv(parent: Environment, calls: Stack[Call], constraint: String, obj: ObjectValue) extends Environment(parent: Environment, calls: Stack[Call], constraint: String) {
  
  // this als ze
  this.update("$this", obj.getFields)
}