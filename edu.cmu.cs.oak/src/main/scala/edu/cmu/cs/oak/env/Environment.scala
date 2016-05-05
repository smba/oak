package edu.cmu.cs.oak.env

import scala.collection.immutable.Stack
import scala.collection.mutable.ListBuffer

/**
  * Created by stefan on 04/05/16.
  */
abstract class Environment extends Context {

  /**
    * This buffer is a cache for output of sub environments, such as
    * function environments.
    */
  var outputBuffer = new ListBuffer[OakValue]

  /**
    * Updates a variable value in the environment.
    *
    * @param name
    * @param value
    * @return
    */
  def update(name: String, value: OakValue): Environment = ???

  /**
    * Looks up a variable value in the environment
    * #
    *
    * @param name
    * @return
    */
  def lookup(name: String): OakValue = ???


  /**
    * Adds an value (string, symbolic) to the output.
    *
    * @param value
    * @return
    */
  def addOutput(value: OakValue): Environment = ???

  /**
    * Merges two environments, e.g.:
    * val env_ = env1 join env2
    *
    * @param env Environment to join this environment with
    * @return joined environment
    */
  def join(env: Environment): Environment = ???

  /**
    * Returns a tuple of two copies of this, where the constrained is
    * joined with either the argument constraint or NOT(constraint)
    *
    * @param constraint Constraint to add to the path condition
    * @return Tuple of two alternate branch environments
    */
  def fork(constraint: String): (Environment, Environment) = ???

  /**
    * Returns a new copy of this, where argument constraint is
    * added to the path condition.
    *
    * @param constraint Constraint to add to the path condition
    * @return Environment with respective path condition
    */
  def withConstraint(constraint: String): Environment = ???

  /**
    * Returns a child environment
    *
    * @return
    */
  def getFunctionEnv(functionDef: FunctionDef): Environment = ???

  /**
    * Writes the cached outputs to the output stream
    *
    * @return
    */
  def writeBufferToOutput(): Environment = {
    var tempEnv = this
    outputBuffer.foreach {
      o => tempEnv = tempEnv.addOutput(o)
    }
    return tempEnv
  }

  /**
    * Re-assigns an empty buffer
    */
  def resetBuffer(): Unit = {
    outputBuffer = new ListBuffer[OakValue]
  }

  /**
    * Sends the buffer content to the parent environment
    */
  def sendBufferToParent(): Unit = {
    outputBuffer.foreach {
      o => this.getParent.receive(o)
    }
  }

  /**
    * Adds v to the outputBuffer
    *
    * @param v
    */
  def receive(v: OakValue): Unit = {
    outputBuffer.append(v)
  }

  def getOutputBuffer(): ListBuffer[OakValue] = outputBuffer

  /* GETTER methods */
  def getParent(): Environment = ???

  def getVariables(): Map[String, OakValue] = ???

  def getOutput(): Stack[OakValue] = ???

  def getCalls(): Stack[String] = ???

  def getConstraint(): String = ???

  def getFunctions(): Map[String, FunctionDef] = ???
}
