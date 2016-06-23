package edu.cmu.cs.oak.nodes

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.StringValue

case class LiteralNode(sv: String, file: String, lineNr: Int) extends DNode {

  //assert(lv.location != null)
  
  def getChildren(): Seq[DNode] = null

  def toXml: scala.xml.Elem = {
    <Literal Text={sv} Length={sv.length.toString()} File={file} Line={lineNr.toString()} />
  }
  
  override def ifdefy(): List[String] = List(sv)

  override def toString(): String = sv
  
  override def isEmpty() = (sv == null)
}
