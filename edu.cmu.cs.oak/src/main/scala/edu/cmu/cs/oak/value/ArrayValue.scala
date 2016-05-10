package edu.cmu.cs.oak.value

import scala.collection.mutable.HashMap

class ArrayValue extends OakValue {

  val array = HashMap[OakValue, OakValue]()

  def set(index: OakValue, value: OakValue) {
    array.put(index, value)
  }

  def getSize(): Int = array.size

  def get(index: OakValue): OakValue = {
    if (array.keySet.contains(index)) {
      return array.get(index).get
    } else {
      throw new ArrayIndexOutOfBoundsException("Index " + index + "  not found in key set.")
    }
  }

  override def toString(): String = "[" + array.mkString(", ") + "]"

}