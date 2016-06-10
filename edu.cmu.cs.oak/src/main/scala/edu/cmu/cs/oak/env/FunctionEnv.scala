package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.OakValue

import scala.collection.immutable.Stack

class FunctionEnv(parent: EnvListener, calls: Stack[String], constraint: String) extends Environment(parent: EnvListener, calls: Stack[String], constraint: String) {

  def getReturnValue(): OakValue = ???

}