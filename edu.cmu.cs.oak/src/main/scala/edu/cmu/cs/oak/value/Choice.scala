package edu.cmu.cs.oak.value

case class Choice(p: String, v1: OakValue, v2: OakValue) extends SymbolicValue {
 override def toString(): String = {
   "Π⟨" + (if (v1 == null) "⊥" else v1) + ", " + (if (v2 == null) "⊥" else v2) +"⟩" 
 }
}