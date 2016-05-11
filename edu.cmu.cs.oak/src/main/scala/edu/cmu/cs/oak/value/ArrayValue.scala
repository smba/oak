package edu.cmu.cs.oak.value

import scala.collection.mutable.HashMap
import edu.cmu.cs.oak.env.OakHeap

class ArrayValue extends OakValue {

  val array = HashMap[OakValue, OakVariable]()

  def set(index: OakValue, value: OakValue) {
    val ovariable = new OakVariable("arrayVal" + OakHeap.getIndex)
    OakHeap.insert(ovariable, value)
    array.put(index, ovariable)
  }

  def getSize(): Int = array.size

  def get(index: OakValue): OakValue = {
    if (array.keySet.contains(index)) {
      return OakHeap.extract(array.get(index).get)
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

  override def toString(): String = "[" + array.mkString(", ") + "]"

}