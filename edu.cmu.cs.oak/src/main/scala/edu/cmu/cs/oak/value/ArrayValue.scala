package edu.cmu.cs.oak.value

import scala.collection.mutable.LinkedHashMap

import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.env.OakHeap
import edu.cmu.cs.oak.exceptions.VariableNotFoundException

/**
 * 
 */
class ArrayValue extends OakValue {

  var array = LinkedHashMap[OakValue, Reference]()
  
  var current = 0

  def set(index: OakValue, value: OakValue, env: Environment) {
    
    if (index.isInstanceOf[StringValue]) {
      index.asInstanceOf[StringValue].setLocation(null)
    }
    
    // initialize internal pointer (key)
    if (current == null) current = 1
    
    val ref = if (array.keySet.contains(index)) {
      this.getRef(index)
    } else {
      Reference("arrayVal" + OakHeap.getIndex, "") //FIXME find variable name
    }
    array.put(index, ref)
    env.insert(ref, value)
  }
  
  def getCurrent(): Reference = {
    if (array.size == 0) {
      null
    } else {
      val key = array.keys.toList(current)
      return  array.get(key).get
    }
  }
  
  def getNext(): Reference = {
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
      return if (index.isInstanceOf[IntValue]) {
        try {
         env.extract(array.values.toList(index.asInstanceOf[IntValue].value.toInt))
        } catch {
          case e: Exception => {
//            throw new ArrayIndexOutOfBoundsException("Index " + index + "  not found in key set.");
            NullValue("something went wrong")
          }
        }
      } else {
        NullValue("something went wrong")
      }
    }
  }
  
  def setRef(index: OakValue, ref: Reference): Unit = {
    
    if (index.isInstanceOf[StringValue]) {
      index.asInstanceOf[StringValue].setLocation(null)
    }
    
    array.put(index, ref)
  }
  
  def getRef(index: OakValue): Reference = {
    val ref = array.get(index)
    if (ref.isEmpty) {
      throw new VariableNotFoundException(index.toString())
    } else {
      ref.get
    }
  }
  
  def getKeys(): List[OakValue] = array.keySet.toList

  override def toString(): String = "Array[" + array.mkString(", ") + "]"

  def cloneArrayValue(): ArrayValue = {
    val av = new ArrayValue()
    array.foreach {
      case (k, v) => av.setRef(k, v) 
    }
    av
  }
  
  override def equals(that: Any): Boolean = {
    that match {
      case av: ArrayValue => {
        if (av.getSize() != array.size) {
          false
        }
        (array zip av.array).map {
          case ((k1, ref1), (k2, ref2)) => {
            (k1 equals k2) && (ref1 equals ref2)
          }
        }.fold(true)(_ && _)
      }
      case _ => false
    }
  }
  
  /**
   * Create a deep copy of the array using new references
   */
  def deepCopy(env: Environment): ArrayValue = {
    val av = new ArrayValue()
    this.array.foreach {
      case (key, ref) => {
        env.extract(ref) match {
          case array_value: ArrayValue => {
            
            if (array_value.getSize() == 0) {
              av.set(key, new ArrayValue(), env)
            } else {
              av.set(key, array_value.deepCopy(env), env)
            }
            // compare this (ArrayValue instance and matched array_value instance)
          }
          case ov: OakValue => {
            av.set(key, ov, env)
          }
          case null => av.set(key, NullValue(""), env)
        }
      }
    }
    av
  }
  
  def hasKey(kc: OakValue) = this.array.contains(kc)
  
  override def isEmpty() = (array.size == 0)
}