package edu.cmu.cs.oak.nodes

import edu.cmu.cs.oak.value.OakValue

case class ConcatNode(values: Seq[DNode]) extends DNode {
  
  def getChildren(): Seq[DNode] = values.toSeq
  
  override def toXml = {
    <concat>
			{for (value <- values) yield 
			  <concatItem>
			    {value.toXml}
				</concatItem>}
    </concat>
  } // {for (value <- values) yield <node>{value}</node>}
  
}