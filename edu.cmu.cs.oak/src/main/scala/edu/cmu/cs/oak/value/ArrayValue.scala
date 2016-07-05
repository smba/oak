package edu.cmu.cs.oak.value

import scala.collection.mutable.HashMap
import edu.cmu.cs.oak.env.OakHeap
import edu.cmu.cs.oak.env.Environment

/**
 * 
 */
class ArrayValue extends OakValue {

  val array = HashMap[OakValue, OakVariable]()

  def set(index: OakValue, value: OakValue, env: Environment) {
    val ref = if (array.keySet.contains(index)) {
      this.getRef(index)
    } else {
      OakVariable("arrayVal" + OakHeap.getIndex, "") //FIXME find variable name
    }
    array.put(index, ref)
    env.insert(ref, value)
  }

  def getSize(): Int = array.size

  def get(index: OakValue, env: Environment): OakValue = {
    if (array.keySet.contains(index)) {
      return env.extract(array.get(index).get)
    } else {
      throw new ArrayIndexOutOfBoundsException("Index " + index + "  not found in key set.")
    }
  }
  
  def setRef(index: OakValue, ref: OakVariable): Unit = {
    array.put(index, ref)
  }
  
  def getRef(index: OakValue): OakVariable = {
    array.get(index).get
  }
  
  def getKeys(): List[OakValue] = array.keySet.toList

  override def toString(): String = "[" + array.mkString(", ") + "]"

  def cloneArrayValue(): ArrayValue = {
    val av = new ArrayValue()
    array.foreach {
      case (k, v) => av.setRef(k, v) 
    }
    av
  }
}