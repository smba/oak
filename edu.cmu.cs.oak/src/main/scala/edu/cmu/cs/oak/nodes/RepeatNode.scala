package edu.cmu.cs.oak.nodes

import edu.cmu.cs.oak.env.Constraint

case class RepeatNode(constraint: Constraint, node: DNode) extends DNode {

  def getChildren(): Seq[DNode] = List(node)

  def toXml(): scala.xml.Elem = {
    <Repeat>
			<Constraint Text={constraint.toString()} />
			{node.toXml}
		</Repeat>
  }

  def ifdefy(): List[String] = {
    var l =  List("# Repeat")
//    var l =  List("<!-- # Repeat -->")
    l ++= node.ifdefy()
    l ++= List("# EndRepeat")
//    l ++= List("<!-- # EndRepeat -->")
    l
  }
  
  override def isEmpty() = node.isEmpty()
  
  override def toString() = "ρ(" + node + ")"
}