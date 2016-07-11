package edu.cmu.cs.oak.value

case class NullValue(name: String) extends OakValue {
  
  
  override def toString = "NullValue("+name+")"
  
  def toXml = {
    <undef />
  }
  
  override def isEmpty() = true
}