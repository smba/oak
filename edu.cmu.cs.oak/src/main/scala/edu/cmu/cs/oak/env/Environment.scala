package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.{FunctionDef, OakValue, OakVariable}

import scala.collection.immutable.Stack
import scala.collection.mutable.ListBuffer

trait Environment {

  def update(name: String, value: OakValue)
  def lookup(name: String): OakValue

  def getVariables(): Map[String, OakVariable]

  def addOutput(value: OakValue)
  
  def defineFunction(value: FunctionDef)
  def getFunction(name: String): FunctionDef

  
  def getCalls(): Stack[String]

  def getParent(): EnvListener

  def getOutput(): List[OakValue]
  def clearOutput()

  def createFunctionEnvironment(f: String): Environment

  def getConstraint(): String

  def fork(constraint: String): (BranchEnv, BranchEnv)
  def prependOutput(pre: List[OakValue])
}