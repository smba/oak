package edu.cmu.cs.oak.nodes

case class DRoot(node: DNode) extends DNode {
    
  def getChildren(): Seq[DNode] = List(node)

  def toXml(): scala.xml.Elem = node.toXml
  
  override def ifdefy(): List[String] = node.ifdefy()
  
}