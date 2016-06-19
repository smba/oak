package edu.cmu.cs.oak.nodes

import edu.cmu.cs.oak.analysis.inlcude.OutputGraphListener
import edu.cmu.cs.oak.value.OakValue

case class LiteralNode(lv: OakValue) extends DNode {

  def getChildren(): Seq[DNode] = null

  def toXml: scala.xml.Elem = {
    <Literal Text={lv.toString} />
  }
  
  override def ifdefy(): List[String] = List(lv.toString)

  override def toString(): String = lv+""
  
  override def isEmpty() = (lv == null)
}
