package edu.cmu.cs.oak.value

case class Choice(p: String, v1: OakValue, v2: OakValue) extends SymbolicValue {
  
  def getConstraint(): String = p
  
  def getV1(): OakValue = v1
  def getV2(): OakValue = v2
  
 override def toString(): String = {
   "Π⟨" + (if (v1 == null) "⊥" else v1) + ", " + (if (v2 == null) "⊥" else v2) +"⟩" 
 }
}