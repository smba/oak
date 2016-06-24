package edu.cmu.cs.oak.value

import edu.cmu.cs.oak.env.Delta

case class Choice(p: String, var v1: OakValue, var v2: OakValue) extends SymbolicValue {

  def getConstraint(): String = p

  def getV1(): OakValue = v1
  def getV2(): OakValue = v2
  
  def setV1(v1: OakValue) { this.v1 = v1 }
  def setV2(v2: OakValue) { this.v2 = v2 }

}