package edu.cmu.cs.oak.value

import scala.annotation.elidable.ASSERTION

import edu.cmu.cs.oak.env.Environment


case class ObjectValue(name: String, stereotype: ClassDef) extends OakValue {

  val fields = new ArrayValue
  
  def getName(): String = name
  
  def getClassdef(): ClassDef = stereotype
  
  def getFields(): ArrayValue = fields
  
  def get(fieldKey: String, env: Environment): OakValue = {
    //assert(stereotype.getFields.contains(fieldKey))
    return fields.get(StringValue(fieldKey, null, 0), env) //FIXME is this right?
  }
  
  def set(fieldKey: String, value: OakValue, env: Environment): Unit = {
    
    //assert(stereotype.getFields.contains(fieldKey), fieldKey)
    fields.set(StringValue(fieldKey, null, 0), value, env) //FIXME is this right?
  }
  
  override def toXml = {
    <object name={name}>
			{fields.toXml}
    </object>
  }
  
}