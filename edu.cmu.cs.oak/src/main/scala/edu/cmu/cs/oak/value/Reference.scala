package edu.cmu.cs.oak.value

case class Reference(name: String, variable: String) extends OakValue {
  def getName(): String = name
  override def isEmpty() = false
}