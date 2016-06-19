package edu.cmu.cs.oak.nodes

import edu.cmu.cs.oak.value.OakValue
import scala.collection.mutable.ListBuffer
import edu.cmu.cs.oak.analysis.inlcude.OutputGraphListener

case class ConcatNode(var values: List[DNode]) extends DNode {
  
  def getChildren(): Seq[DNode] = values.toSeq
  
  def addOutput(outputNode: DNode) {
    this.values ::= outputNode
  }

  def prepend(outputNode: DNode) {
    this.values = outputNode :: values
  }
  
  override def toXml = {
    <Concat>
      {
        for (value <- values) yield { value.toXml }
      }
    </Concat>
  }

  override def ifdefy(): List[String] = {
    var seqence = List[String]()
    values.foreach {
      v => seqence ++= v.ifdefy()
    }
    seqence
  }
  
  override def isEmpty() = (values.size == 0)
}