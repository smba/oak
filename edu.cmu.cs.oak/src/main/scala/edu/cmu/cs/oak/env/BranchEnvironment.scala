package edu.cmu.cs.oak.env

import scala.collection.immutable.{Map, Stack}

/**
  * Created by stefan on 04/05/16.
  */
class BranchEnvironment(parent: Environment,
                        variables: Map[String, OakValue],
                        output: Stack[OakValue],
                        calls: Stack[String],
                        constraint: String,
                        functions: Map[String, FunctionDef]) extends SimpleEnvironment(
  parent: Environment,
  variables: Map[String, OakValue],
  output: Stack[OakValue],
  calls: Stack[String],
  constraint: String,
  functions: Map[String, FunctionDef]) {


  def getReturn(): OakValue = lookup("return")
}
