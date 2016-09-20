package edu.cmu.cs.oak.core

//import scala.collection.mutable.HashMap
//import scala.collection.mutable.Map
//import scala.collection.mutable.ListBuffer
import edu.cmu.cs.oak.value.OakValue

//class CounterMap[A](private val entries: Map[A, Int] = new HashMap[A, Int]()) {
//
//  def count(a: A) {
//    entries.put(a, entries.getOrElse(a, 0) + 1)
//  }
//  
//  def contains(a: A): Boolean = {
//    entries contains a
//  }
//  
//  def get(a: A): Int = {
//    entries.getOrElse(a, 0)
//  }
//
//}

class ArrayUpdateRecordMap(private var entries: Map[String, Set[List[OakValue]]] = Map[String, Set[List[OakValue]]]()) {
  
  def recordIndices(name: String, indices: List[OakValue]) {
    var currentIndices = entries.getOrElse(name, Set[List[OakValue]]())
    if (!(currentIndices contains indices)) {
      currentIndices += indices
    }
    entries += (name -> currentIndices)
  }
  
  def getIndices(name: String): Set[List[OakValue]] = {
    entries.getOrElse(name, Set[List[OakValue]]())
    }
  
  def contains(name: String): Boolean = {
    entries contains name
  }
  
  def getEntries() = entries
  
  
  def +(that: ArrayUpdateRecordMap): ArrayUpdateRecordMap = {
    var entries2 = entries
    that.getEntries().keys.foreach {
      k => {
        if (entries2.keySet.contains(k)) {
          entries += (k -> (entries2.get(k).get union that.getEntries().get(k).get))
        }
      }
    }
    new ArrayUpdateRecordMap(entries2)
  }
}