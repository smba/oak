package edu.cmu.cs.oak.nodes

import edu.cmu.cs.oak.analysis.inlcude.OutputGraphListener

case class RepeatNode(node: DNode) extends DNode {
  
  println(node.getClass + "")
  
  def getChildren(): Seq[DNode] = List(node)

  def toXml(): scala.xml.Elem = {
    <Repeat>
			{node.toXml}
		</Repeat>
  }
  
  def traverse(listener: OutputGraphListener) {
    node.traverse(listener)
  }

  def ifdefy(): List[String] = {
    var l =  List("# Repeat")
    l ++= node.ifdefy()
    l ++= List("# EndRepeat")
    l
  }
  
}