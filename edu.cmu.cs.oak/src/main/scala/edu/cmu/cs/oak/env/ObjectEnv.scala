package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.ObjectValue
import scala.collection.immutable.Stack

/**
 * This environment is used for method execution.
 */
case class ObjectEnv(parent: EnvListener, calls: Stack[String], constraint: String, obj: ObjectValue) extends AbstractEnv(parent: EnvListener, calls: Stack[String], constraint: String) {
  
  this.update("$this", obj.getFields)
  
}