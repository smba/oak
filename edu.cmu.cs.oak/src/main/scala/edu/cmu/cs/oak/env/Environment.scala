package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.{ FunctionDef, OakValue, OakVariable }

import scala.collection.immutable.Stack
import scala.collection.mutable.ListBuffer
import edu.cmu.cs.oak.value.ClassDef
import edu.cmu.cs.oak.value.ObjectValue
import edu.cmu.cs.oak.nodes.DNode

trait Environment extends EnvListener {

  def update(name: String, value: OakValue)
  def lookup(name: String): OakValue
  def getOutputModel(): DNode
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
  def receiveOutput(value: Seq[OakValue])

  def getConstraint(): String

  def prependOutput(pre: List[OakValue])
  def toXml: scala.xml.Elem
}

/**
 * Includes static methods used by any environment.
 */
object Environment {

  /**
   * Creates a new function environment that is used to
   * execute a function call. The function call is documented
   * via the environments call stack.
   *
   * @param dis Parent environment to bounce output to
   * @param f Name of the function
   *
   * @return FunctionEnv
   */
  def createFunctionEnvironment(dis: Environment, f: String): FunctionEnv = {
    return new FunctionEnv(dis, dis.getCalls push f, dis.getConstraint)
  }

  /**
   * Creates a new object environment that is used to
   * execute a function call.
   *
   * @param dis Parent environment to bounce output to
   * @param f Name of the function
   *
   * @return ObjectEnv
   */
  def createObjectEnvironment(dis: Environment, obj: ObjectValue): ObjectEnv = {
    return ObjectEnv(dis, dis.getCalls(), dis.getConstraint, obj)
  }

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