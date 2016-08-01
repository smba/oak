package edu.cmu.cs.oak.env

class OakHeap() {
//
//  val varval = collection.mutable.Map[OakVariable, OakValue]()
//  val updated = collection.mutable.Map[OakVariable, OakValue]()
//  
//  def insert(variable: OakVariable, value: OakValue): Unit = {
//    updated.put(variable, value)
//    varval.put(variable -> value)
//  }
//
//  def extract(variable: OakVariable): OakValue = {
//    val opt = varval.get(variable)
//    if (!opt.isEmpty) {
//      return opt.get
//    } else {
//      throw new VariableNotFoundException("Variable " + variable.getName() + " is undefined.")
//    }
//  }
//  
//  def unsetVariable(ref: OakVariable) {
//    varval -= ref
//  }
//  
//  def getUpdatedVarval() = updated
//
//  def getHeapMap() = 
//  
//  def getVariables() = varval.keySet
//
//  def clear() {
//    varval.clear()
//  }
}

object OakHeap {

  var index: Long = 1

  def getIndex(): Long = {
    val i = index
    index += 1
    return i
  }

  def clear(): Unit = {
    index = 0L
  }

}