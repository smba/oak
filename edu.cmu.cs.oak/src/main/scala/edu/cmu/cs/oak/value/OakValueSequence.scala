package edu.cmu.cs.oak.value

case class OakValueSequence(seq: List[OakValue]) extends SymbolicValue {
  override def toString() = "Λ{" + (seq mkString ", ") + "}"
  def getSequence = seq
  
  override def toXml = {
    <concat>
			{for (value <- seq) yield 
			  <concatItem>
			    {value.toXml}
				</concatItem>}
    </concat>
  }
}