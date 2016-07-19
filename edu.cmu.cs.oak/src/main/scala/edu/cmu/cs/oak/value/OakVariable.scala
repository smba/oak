package edu.cmu.cs.oak.value

case class OakVariable(name: String, variable: String) extends OakValue {
  def getName(): String = name
  override def isEmpty() = false
}