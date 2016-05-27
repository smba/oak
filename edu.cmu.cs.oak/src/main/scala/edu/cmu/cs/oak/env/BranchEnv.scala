package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.{ Choice, OakValue, StringValue }

import scala.collection.immutable.{ Map, Stack }
import edu.cmu.cs.oak.value.OakVariable
import scala.collection.mutable.ListBuffer
import edu.cmu.cs.oak.value.OakValueSequence
import scala.collection.mutable.HashSet

class BranchEnv(parent: EnvListener, calls: Stack[String], constraint: String) extends AbstractEnv(parent: EnvListener, calls: Stack[String], constraint: String) {

  val updates = new HashSet[String]
  
  override def update(name: String, value: OakValue): Unit = {
    super.update(name, value)
    updates += name
  }
  
  def getUpdates = updates.toSet
  
}