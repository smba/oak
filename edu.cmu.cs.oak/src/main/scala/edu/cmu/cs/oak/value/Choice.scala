package edu.cmu.cs.oak.value

case class Choice(p: String, v1: OakValue, v2: OakValue) extends SymbolicValue {

  def getConstraint(): String = p

  def getV1(): OakValue = v1
  def getV2(): OakValue = v2
  
  override def toXml = {
    <choice>
			<condition>
        {p}
      </condition>
			<trueNode>
        {v1.toXml}
      </trueNode>
			<falseNode>
        {v2.toXml}
      </falseNode>
    </choice>
  }
}