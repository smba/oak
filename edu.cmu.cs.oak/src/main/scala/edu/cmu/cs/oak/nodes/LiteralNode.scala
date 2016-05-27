package edu.cmu.cs.oak.nodes

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.analysis.inlcude.OutputGraphListener

case class LiteralNode(lv: OakValue) extends DNode {

  override def traverse(listener: OutputGraphListener) {
    
  }
  
  def getChildren(): Seq[DNode] = null

  def toXml: scala.xml.Elem = {
    <literal>
      { lv.toXml }
    </literal>
  }
  
  override def ifdefy(): List[String] = List(lv.toString)

  override def toString(): String = lv+""
}
