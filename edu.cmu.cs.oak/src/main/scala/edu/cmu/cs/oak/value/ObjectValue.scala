package edu.cmu.cs.oak.value

import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.env.ClassDef

/**
 * Representation olsf an object value in PHP.
 */
case class ObjectValue(name: String, objectClass: ClassDef) extends OakValue {

  /**
   * Internally an object is implemented using an array
   * whereby the classes fields represent the indices.
   */
  private val fields = new ArrayValue()
  
  /**
   * Name of the object
   */
  def getName(): String = name
  
  /**
   * Returns the class definition of the object value.
   * @return ClassDef
   */
  def getClassDef(): ClassDef = objectClass
  
  def getFields(): ArrayValue = fields
  
  def get(fieldKey: String, env: Environment): OakValue = {
    //assert(stereotype.getFields.contains(fieldKey))
    return fields.get(StringValue(fieldKey, null, 0), env) //FIXME is this right?
  }
  
  def set(fieldKey: String, value: OakValue, env: Environment): Unit = {
    
    //assert(stereotype.getFields.contains(fieldKey), fieldKey)
    fields.set(StringValue(fieldKey, null, 0), value, env) //FIXME is this right?
  }  
}