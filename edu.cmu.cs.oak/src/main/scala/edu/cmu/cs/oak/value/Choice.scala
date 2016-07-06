package edu.cmu.cs.oak.value

import edu.cmu.cs.oak.env.Delta
import edu.cmu.cs.oak.env.Constraint

case class Choice(p: Constraint, var v1: OakValue, var v2: OakValue) extends SymbolicValue {

  def getConstraint(): Constraint = p

  def getV1(): OakValue = v1
  def getV2(): OakValue = v2
  
  def setV1(v1: OakValue) { this.v1 = v1 }
  def setV2(v2: OakValue) { this.v2 = v2 }
  
  def applyToObjects(func: ObjectValue => Unit) {
    v1 match {
      case c: Choice => c.applyToObjects(func)
      case o: ObjectValue => func(o)
      case _ => {}
    }
  }

}