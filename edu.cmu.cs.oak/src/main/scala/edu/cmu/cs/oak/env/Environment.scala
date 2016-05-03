package edu.cmu.cs.oak.env

import scala.collection.immutable.Stack

trait Environment {
  
  /**
   * Updates a variable value in the environment.
   */
  def update(name: String, value: OakValue): Environment
  
  /**
   * Looks up a variable value in the environment
   */
  def lookup(name: String): OakValue
  
  /**
   * Adds an value (string, symbolic) to the output.
   */
  def addOutput(value: OakValue): Environment
  
  /**
   * Merges two environments, e.g.:
   * val env_ = env1 join env2
   */
  def join(env: Environment): Environment

  /**
    * Returns a tuple of two copies of this, where the constrained is
    * joined with either the argument constraint or NOT(constraint)
    *
    * @param constraint Constraint to add to the path condition
    * @return Tuple of two alternate branch environments
    */
  def fork(constraint: String): (Environment, Environment)

  /**
    * Returns a new copy of this, where argument constraint is
    * added to the path condition.
    *
    * @param constraint Constraint to add to the path condition
    * @return Environment with respective path condition
    */
  def withConstraint(constraint: String): Environment
  
  /**
   * 
   */
  def isGlobalEnvironment(): Boolean
  
  def getVariables: Map[String, OakValue]
  def getOutput: Stack[OakValue]
  def getCalls: Stack[String]

  def getConstraint: String
}