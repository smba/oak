package edu.cmu.cs.oak.env

import scala.collection.immutable.Stack
import edu.cmu.cs.oak.constraints.Constraint

trait Environment {
  
  /**
   * Updates a variable value in the environment.
   */
  def update(name:String, value:OakValue): Environment = ???
  
  /**
   * Looks up a variable value in the environment
   */
  def lookup(name:String): OakValue = ???
  
  /**
   * Adds an value (string, symbolic) to the output.
   */
  def addOutput(value:OakValue): Environment = ???
  
  /**
   * Merges two environments, e.g.:
   * val env_ = env1 join env2
   */
  def join(env:Environment): Environment 
  
  /**
   * Creates a plain environment only. Function symbols are
   * available. Parent environment is linked to this instance 
   * and vice versa.
   */
  def createSubEnv(): Environment = ???
  
  /**
   * 
   */
  def isGlobalEnvironment(): Boolean = ???
  
  def getVariables: Map[String, OakValue]
  def getOutput: Stack[OakValue]
  def getCalls: Stack[String]
  def getConstraint: Constraint
}