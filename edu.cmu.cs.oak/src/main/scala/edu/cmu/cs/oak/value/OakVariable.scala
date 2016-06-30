package edu.cmu.cs.oak.value

case class OakVariable(name: String, variable: String) extends OakValue {
  //assert(!(name equals "arrayVal8"))
  def getName(): String = name
}