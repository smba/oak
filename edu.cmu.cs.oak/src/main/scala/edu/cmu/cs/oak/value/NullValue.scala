package edu.cmu.cs.oak.value

import edu.cmu.cs.oak.value.OakValue

/**
 * Singleton object (-> Null object) representing a null-like value for undefined 
 * variables. 
 */
object NullValue extends OakValue {
  
  /** Empty per default */
  override def isEmpty() = true
  
  /** If exported to XML, NullValue is represented by <undef />
   *  for example in Choices, it's completely omitted if exported 
   *  as a single value.
   *  */
  def toXml = {
    <undef />
  }
  
  override def toString() = "NullValue"

}