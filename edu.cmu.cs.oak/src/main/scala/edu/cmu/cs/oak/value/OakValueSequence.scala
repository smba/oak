package edu.cmu.cs.oak.value

class OakValueSequence(seq: List[OakValue]) extends OakValue {
  override def toString() = seq mkString ""
  def getSequence = seq
}