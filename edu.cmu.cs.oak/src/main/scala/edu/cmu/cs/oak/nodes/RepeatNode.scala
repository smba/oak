package edu.cmu.cs.oak.nodes

case class RepeatNode(node: DNode) extends DNode {

  def getChildren(): Seq[DNode] = List(node)

  def toXml(): scala.xml.Elem = {
    <Repeat>
			{node.toXml}
		</Repeat>
  }

  def ifdefy(): List[String] = {
    var l =  List("# Repeat")
    l ++= node.ifdefy()
    l ++= List("# EndRepeat")
    l
  }
  
  override def isEmpty() = node.isEmpty()
  
  override def toString() = "ρ(" + node + ")"
}