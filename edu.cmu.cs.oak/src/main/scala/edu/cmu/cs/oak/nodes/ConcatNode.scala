package edu.cmu.cs.oak.nodes

import edu.cmu.cs.oak.value.OakValue

case class ConcatNode(values: Seq[DNode]) extends DNode {
  
  def getChildren(): Seq[DNode] = values.toSeq
  
  override def toString(): String = {
    val sb = new StringBuilder()
    values.foreach { v => sb.append( v.toString() +"\n" ) }
    sb.toString
  }
  
}