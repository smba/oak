package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.{ FunctionDef, OakValue, OakVariable }

import scala.collection.immutable.Stack
import scala.collection.mutable.ListBuffer
import edu.cmu.cs.oak.value.ClassDef
import edu.cmu.cs.oak.value.ObjectValue
import edu.cmu.cs.oak.nodes.DNode

trait Environment extends EnvListener {

  /**
   * Updates a variable in the environment, i.e. it stores a variable
   * assignment, such as '$i = 8'.
   *
   * @paran name Name of the variable
   * @param value OakValue to assign to the variable
   */
  def update(name: String, value: OakValue)

  /**
   * Looks up an variable and returns its context-dependant value.
   *
   * @param name Name of the variable
   *
   * @return value Value of the variable
   */
  def lookup(name: String): OakValue

  /**
   * Returns a DNode tree of the environment's output.
   * @return root node of the DNode tree
   */
  def getOutputModel(): DNode

  /**
   * Add output to environment.
   * @param value Value to add to output
   */
  def addOutput(value: OakValue)

  /**
   * Get the output sequence from the environment.
   * @return output as sequence of values
   */
  @deprecated def getOutput(): List[OakValue]

  /**
   * Manipulation of references via the environment
   *  @param name of the variabke
   *  @param reference to point to
   */
  def setRef(name: String, ref: OakVariable)

  /**
   * Get the current reference of a variable at runtime.
   * @param name Name of the variable
   * @return reference that variable 'name points to
   */
  def getRef(name: String): OakVariable

  /**
   * Adds a function definition to the environment.
   * @param value Function definition to add
   */
  def addFunction(value: FunctionDef)

  /**
   * Looks up a function definition in the environment.
   * @param name Name of the function
   * @return corresponding function definition
   */
  def getFunction(name: String): FunctionDef

  /**
   * Adds a class definition to the environment.
   * @param value ClassDef to add
   */
  def addClass(value: ClassDef)

  /**
   * Looks up a class definition in the environment.
   * @param name Name of the class
   * @return corresponding class definition
   */
  def getClass(name: String): ClassDef

  /**
   * Returns the current call stack at runtime.
   * @return Stack of strings where each string denotes a function call
   */
  def getCalls(): Stack[String]

  /**
   * Returns the environment's parent environment (null if top-level env)
   * @param parent environment
   */
  def getParent(): EnvListener

  /**
   * Receive and store output from a child environment
   * @param value Sequence of output values to add
   */
  def receiveOutput(value: Seq[OakValue])

  /**
   * Returns the environments path condition
   * @return path condition
   */
  def getConstraint(): String

  /**
   * Prepends the passed output to the environments output.
   * @param pre List of values to add
   */
  def prependOutput(pre: List[OakValue])

  /**
   * Returns the environments map of variable names to references
   * @return map of variable names to references
   */
  def getVariables(): Map[String, OakVariable]

  /**
   * Serializes the entire state, i.e., serializes variable names 
   * and values to XML.
   * TODO implement parser for serialized environment
   * 
   * @return XML representation of the environment
   */
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