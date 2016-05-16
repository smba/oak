package edu.cmu.cs.oak.nodes

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.StringValue

case class LiteralNode(lv: OakValue) extends DNode {
  
  def getChildren(): Seq[DNode] =  null
  
  def toXml: scala.xml.Elem = {
    <literal>
			{lv.toXml}
    </literal>
  }
  
  override def toString(): String = lv.toString
}
