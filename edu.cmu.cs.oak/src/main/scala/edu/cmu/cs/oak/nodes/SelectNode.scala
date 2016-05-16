package edu.cmu.cs.oak.nodes

case class SelectNode(condition: String, v1: DNode, v2: DNode) extends DNode {
  
  def getChildren(): Seq[DNode] = List(v1, v2)
  
  override def toXml = {
    <select>
			<condition>
        {condition}
      </condition>
			<trueNode>
        {v1.toXml}
      </trueNode>
			<falseNode>
        {v2.toXml}
      </falseNode>
    </select>
  }
}