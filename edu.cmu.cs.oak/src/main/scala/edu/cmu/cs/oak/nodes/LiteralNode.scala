package edu.cmu.cs.oak.nodes

import edu.cmu.cs.oak.analysis.inlcude.OutputGraphListener
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.StringValue

case class LiteralNode(lv: StringValue) extends DNode {

  assert(lv.location != null)
  
  def getChildren(): Seq[DNode] = null

  def toXml: scala.xml.Elem = {
    <Literal Text={lv.toString} Length={lv.toString.length.toString()} File={try {lv.location.getFileName().toString()} catch { case n: NullPointerException => ""}} Line={try {lv.location.getLineNumber().toString()} catch { case n: NullPointerException => "0"}}/>
  }
  
  override def ifdefy(): List[String] = List(lv.toString)

  override def toString(): String = lv+""
  
  override def isEmpty() = (lv == null)
}
