package edu.cmu.cs.oak.nodes

import edu.cmu.cs.oak.value.OakValue
import scala.collection.mutable.ListBuffer
import edu.cmu.cs.oak.analysis.inlcude.OutputGraphListener

case class ConcatNode(values: Seq[DNode]) extends DNode {

  override def traverse(listener: OutputGraphListener) {
    values.foreach {
      v => {
        listener.addEdge(this.hashCode.toString, v.hashCode.toString)
        v.traverse(listener)
      }
    }
  }
  
  def getChildren(): Seq[DNode] = values.toSeq
  
  override def toXml = {
    <concat>
      {
        for (value <- values) yield <concatItem>
                                      { value.toXml }
                                    </concatItem>
      }
    </concat>
  }

  override def ifdefy(): List[String] = {
    var seqence = List[String]()
    values.foreach {
      v => seqence ++= v.ifdefy()
    }
    seqence
  }
}