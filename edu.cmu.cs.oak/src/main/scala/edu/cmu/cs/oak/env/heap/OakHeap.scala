package edu.cmu.cs.oak.env.heap

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakVariable

class OakHeap(var varval: Map[OakVariable, OakValue]) {

  def insert(variable: OakVariable, value: OakValue): Unit = {
    varval += (variable -> value)
  }

  def extract(variable: OakVariable): OakValue = {
    val opt = varval.get(variable)
    if (!opt.isEmpty) {
      return opt.get
    } else {
      throw new RuntimeException("Variable " + variable.getName() + " is undefined.")
    }
  }
  
  def unsetVariable(ref: OakVariable) {
    varval -= ref
  }

  def getVariables() = varval.keySet

  def clear(): Unit = {
    varval = Map[OakVariable, OakValue]()
  }
}

object OakHeap {

  private var index: Long = 1

  def getIndex(): Long = {
    val i = index
    index += 1
    return i
  }

  def clear(): Unit = {
    index = 0L
  }

}