package edu.cmu.cs.oak.value

// For loops
case class OakValueRepeatSequence(seq: List[OakValue]) extends OakValueSequence(seq: List[OakValue]) {

  override def isEmpty() = (seq.size == 0)
  
}