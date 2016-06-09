package edu.cmu.cs.oak.value

import scala.collection.mutable.HashMap
import edu.cmu.cs.oak.env.heap.OakHeap

class ArrayValue extends OakValue {

  val array = HashMap[OakValue, OakVariable]()

  def set(index: OakValue, value: OakValue) {
    val ref = if (array.keySet.contains(index)) {
      this.getRef(index)
    } else {
      new OakVariable("arrayVal" + OakHeap.getIndex)
    }
    OakHeap.insert(ref, value)
    array.put(index, ref)
  }

  def getSize(): Int = array.size

  def get(index: OakValue): OakValue = {
    if (array.keySet.contains(index)) {
      return OakHeap.extract(array.get(index).get)
    } else {
      println(array.keySet)
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

  override def toXml = {
    <array>
			{for (key <- array.keySet) yield 
			  <arrayElement>
			    <key>
						{key.toXml}
					</key>
					<value>
						{OakHeap.extract(array.get(key).get).toXml}
					</value>
				</arrayElement>}
		</array>
  }
}