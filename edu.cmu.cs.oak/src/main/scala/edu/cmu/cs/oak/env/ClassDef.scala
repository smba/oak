package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.ObjectValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.exceptions.VariableNotFoundException

case class ClassDef(name: String, var fields: Map[String, OakValue], methods: Map[String, FunctionDef], constants: Map[String, OakValue], var staticFields: Map[String, OakValue],  parent: String) {
  
  /**
   * Constructors
   */
  var constructors = Map[Int, FunctionDef]()
  
  def addConstructor(i: Int, fd: FunctionDef): Unit = {
    constructors += (i -> fd)
  }
  
  def getConstructor(i: Int): FunctionDef = {
    try {
      constructors.get(i).get
    } catch {
      case nsee: NoSuchElementException => throw new NoSuchElementException("Constructor for " + name + " with " + i + " arguments not found (" + constructors.size + " constructor(s) available).")
    }
  }
  
  def getDefaultObject(env: Environment): ObjectValue = {
    val obj = new ObjectValue("default", this)
    fields.foreach {
      field =>  obj.set(field._1, NullValue(""), env)
    }
    obj
  }
  
  def initFields(fieldsInit: Map[String, OakValue]) {
    fields = fieldsInit
  }
  
  def initStaticField(fieldName: String, value: OakValue) {
    staticFields += (fieldName -> value)
  }
  
  def getName(): String = name
  def getMethods(fname: String): FunctionDef = {
    if (methods.keySet contains fname) {
      methods.get(fname).get
    } else {
      throw new VariableNotFoundException("Method " + name + "::" + fname + " not found.")
    }
  }
  def getFields(): List[String] = fields.keySet.toList
  def getFieldMap(): Map[String, OakValue] = fields
  def getConstants() = constants
  def getStaticFields() = staticFields
  
}