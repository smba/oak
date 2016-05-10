package edu.cmu.cs.oak.nodes

import edu.cmu.cs.oak.value.OakValue

case class LiteralNode(lv: OakValue) extends DNode {
  def getChildren(): Seq[DNode] =  null
  
  override def toString(): String = lv.toString
}