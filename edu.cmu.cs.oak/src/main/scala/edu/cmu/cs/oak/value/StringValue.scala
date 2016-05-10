package edu.cmu.cs.oak.value

case class StringValue(value:String) extends OakValue {
  override def toString() = value
}