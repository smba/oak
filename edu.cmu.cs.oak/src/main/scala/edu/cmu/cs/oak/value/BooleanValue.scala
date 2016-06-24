package edu.cmu.cs.oak.value

case class BooleanValue(v:Boolean) extends OakValue {
  
  // Syntactic sugar
  def &&(v2:BooleanValue): BooleanValue = BooleanValue(v && v2.value)
  def ||(v2:BooleanValue): BooleanValue = BooleanValue(v || v2.value)
  def not(): BooleanValue = BooleanValue(!v)
  def value = v
 }