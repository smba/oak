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
  private var fields = new ArrayValue()

  /**
   * Name of the object
   */
  def getName(): String = name

  override def toString() = "ObjectValue" + this.hashCode()

  override def equals(that: Any): Boolean = {
    that match {
      case ov: ObjectValue => {
        ov.fields equals fields
      }
      case _ => false
    }
  }

  /**
   * Returns the class definition of the object value.
   * @return ClassDef
   */
  def getClassDef(): ClassDef = objectClass

  def getFields(): ArrayValue = fields

  def get(fieldKey: String, env: Environment): OakValue = {
    return fields.get(StringValue(fieldKey, "", 0), env) //FIXME is this right?
  }

  def set(fieldKey: String, value: OakValue, env: Environment): Unit = {
    fields.set(StringValue(fieldKey, "", 0), value, env) //FIXME is this right?
  }

  def getFieldRef(fieldKey: String): OakVariable = {
    return fields.getRef(StringValue(fieldKey, "", 0))
  }

  def compare(that: ObjectValue, env: Environment): Boolean = {
    if (!(objectClass equals that.objectClass)) {
      return false
    }
    val x = fields.array.map { case (k, v) => env.extract(v) equals env.extract(that.fields.array.get(k).get) }.toList.fold(true)(_ && _)
    return x

  }

  def cloneObjectValue(): ObjectValue = {
    val obj = ObjectValue(name, objectClass)
    obj.fields = fields.cloneArrayValue()
    obj
  }

  override def isEmpty() = false
}