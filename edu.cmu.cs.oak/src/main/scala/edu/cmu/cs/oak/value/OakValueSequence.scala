package edu.cmu.cs.oak.value

case class OakValueSequence(seq: List[OakValue]) extends OakValue {
  override def toString() = "Λ{" + (seq mkString ", ") + "}"
  def getSequence = seq
}