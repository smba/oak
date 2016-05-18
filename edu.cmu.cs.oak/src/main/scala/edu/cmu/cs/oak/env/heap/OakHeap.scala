package edu.cmu.cs.oak.env.heap

import edu.cmu.cs.oak.value.{FunctionDef, OakValue, OakVariable}
import org.slf4j.LoggerFactory

class OakHeap {}

object OakHeap extends VarHeap {

  val logger = LoggerFactory.getLogger(classOf[OakHeap])
  var index: Long = 1
  
  def getIndex(): Long = {
    val i = index
    index += 1
    return i
  }
  
  var varval = Map[OakVariable, OakValue]()
  var funcs =  Map[String, FunctionDef]()
  
  override def insert(variable: OakVariable, value: OakValue): Unit =  {
    varval += (variable -> value)
  }
  
  override def extract(variable: OakVariable): OakValue = {
    val opt = varval.get(variable)
    if (! opt.isEmpty) {
      return opt.get
    } else {
      logger.error("Variable " + variable.getName() + " is undefined, heap is " + varval.toString())
      throw new RuntimeException("Variable " + variable.getName() + " is undefined.")
    }
  }
  
  def defineFunction(f: FunctionDef): Unit = {
    funcs += (f.getName -> f)
  }
  
  def getFunction(name: String): FunctionDef = {
    val opt = funcs.get(name)
    if (! opt.isEmpty) {
      return opt.get
    } else {
      throw new RuntimeException("Function " + name + " is undefined.")
    }
  }
  
  override def getVariables() = varval.keySet 
  
  override def clear(): Unit = {
    index = 0L
    varval = Map[OakVariable, OakValue]()
  }
  
}