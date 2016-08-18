package edu.cmu.cs.oak.nodes

import edu.cmu.cs.oak.env.Constraint

case class SelectNode(constraint: Constraint, v1: DNode, v2: DNode) extends DNode {
    
  assert((v1 != null) && (v2 != null))
  def getChildren(): Seq[DNode] = List(v1, v2)
  
  override def toXml() = {
    <Select>
			<Constraint Text={constraint.toString()} />
      {v1.toXml}
      {v2.toXml}
    </Select>
  }
  
  override def ifdefy(): List[String] = {
    var sequence = List[String]()
//    sequence ++= List("#ifdef")
    sequence ++= List("<!-- #ifdef -->")
    sequence ++= v1.ifdefy()
//    sequence ++= List("#else")
    sequence ++= List("<!-- #else -->")
    sequence ++= v2.ifdefy()
//    sequence ++= List("#endif")
    sequence ++= List("<!-- #endif -->")
    sequence
  }
  
  override def toString() = "π(" + constraint + "," + v1 + "," + v2 + ")"
  
  override def isEmpty() = (v1.isEmpty() && v2.isEmpty())
  
}