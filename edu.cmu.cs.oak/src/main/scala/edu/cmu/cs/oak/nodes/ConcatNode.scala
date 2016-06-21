package edu.cmu.cs.oak.nodes

import edu.cmu.cs.oak.value.OakValue
import scala.collection.mutable.ListBuffer
import edu.cmu.cs.oak.analysis.inlcude.OutputGraphListener

case class ConcatNode(var values: List[DNode]) extends DNode {
  
  def getChildren(): Seq[DNode] = values.toSeq
  
  def addOutput(outputNode: DNode) {
    if (!outputNode.isEmpty()) {
      this.values ::= outputNode
    }
  }

  override def toXml = {
    <Concat>
      {
        for (value <- values.reverse) yield { value.toXml }
      }
    </Concat>
  }

  override def ifdefy(): List[String] = {
    var seqence = List[String]()
    values.reverse.foreach {
      v => seqence ++= v.ifdefy()
    }
    seqence
  }
  
  override def toString() = values.reverse.mkString("")
  
  override def isEmpty() = (values.size == 0)
}