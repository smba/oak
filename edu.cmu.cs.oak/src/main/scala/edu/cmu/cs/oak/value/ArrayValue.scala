package edu.cmu.cs.oak.value

import scala.collection.mutable.HashMap
import edu.cmu.cs.oak.env.OakHeap
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.exceptions.VariableNotFoundException
import edu.cmu.cs.oak.core.SymbolFlag
import scala.collection.mutable.LinkedHashMap

/**
 * 
 */
class ArrayValue extends OakValue {

  var array = LinkedHashMap[OakValue, OakVariable]()
  
  var current = 0

  def set(index: OakValue, value: OakValue, env: Environment) {
    
    // initialize internal pointer (key)
    if (current == null) current = 1
    
    val ref = if (array.keySet.contains(index)) {
      this.getRef(index)
    } else {
      OakVariable("arrayVal" + OakHeap.getIndex, "") //FIXME find variable name
    }
    array.put(index, ref)
    env.insert(ref, value)
  }
  
  def getCurrent(): OakVariable = {
    if (array.size == 0) {
      null
    } else {
      val key = array.keys.toList(current)
      return  array.get(key).get
    }
  }
  
  def getNext(): OakVariable = {
    if (array.size == 0) {
      null
    } else {
      val key = array.keys.toList(current + 1)
      return  array.get(key).get
    }
  }
  
  def reset() {
    current = if (array.size == 0) 0 else 1
  }
  
  def pop(): OakValue = {
    if (array.size == 0) {
      NullValue("Array is already empty (pop)")
    } else {
      val r = array.get( array.keys.toList.reverse.head ).get
      
      this.reset() // http://php.net/manual/en/function.array-pop.php
      
      array = array.slice(0, array.size - 1)
      r
    }
  }

  def getSize(): Int = array.size

  def get(index: OakValue, env: Environment): OakValue = {
    if (array.keySet.contains(index)) {
      return try {
        env.extract(array.get(index).get)
      } catch {
        case vnfe: VariableNotFoundException => null
      }
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
  
  override def isEmpty() = (array.size == 0)
}