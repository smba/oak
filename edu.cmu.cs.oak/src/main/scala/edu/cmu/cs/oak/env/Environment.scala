package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.{FunctionDef, OakValue, OakVariable}

import scala.collection.immutable.Stack
import scala.collection.mutable.ListBuffer
import edu.cmu.cs.oak.value.ClassDef
import edu.cmu.cs.oak.value.ObjectValue

trait Environment extends EnvListener {

  def update(name: String, value: OakValue)
  def lookup(name: String): OakValue

  def getVariables(): Map[String, OakVariable]

  def addOutput(value: OakValue)
  
  def setRef(name: String, ref: OakVariable)
  def getRef(name: String): OakVariable
  
  def addFunction(value: FunctionDef)
  def getFunction(name: String): FunctionDef

  def addClass(value: ClassDef)
  def getClass(name: String): ClassDef
  
  def getCalls(): Stack[String]

  def getParent(): EnvListener

  def getOutput(): List[OakValue]
  //def clearOutput()
  def receiveOutput(value: Seq[OakValue])
  def createFunctionEnvironment(f: String): Environment
  
  def createObjectEnvironment(obj: ObjectValue): Environment
  
  def getConstraint(): String

  def fork(constraint: String): (BranchEnv, BranchEnv)
  def prependOutput(pre: List[OakValue])
  //def ifdefy(node: OakValue): List[String]
  //def ifdefy(): List[String]
}

object Environment {
  
  /**
   * Splits an environment into two branch environments that 
   * can be joined afterwards.
   * 
   * @param newConstraint Path constrained to add to the branches
   * 
   * @param Tuple of two branch environments 
   */
  def fork(parent: Environment, newConstraint: String): (BranchEnv, BranchEnv) = {
    val b1 = new BranchEnv(parent, parent.getCalls(), parent.getConstraint() + " && " + newConstraint)
    val b2 = new BranchEnv(parent, parent.getCalls(), parent.getConstraint() + " && NOT(" + newConstraint + ")")

    /* Add variables of parent environment to the branch environments. */
    parent.getVariables.keySet.foreach {
      k =>
        {
          b1.update(k, parent.lookup(k))
          b2.update(k, parent.lookup(k))
        }
    }

    return (b1, b2)
  }
}