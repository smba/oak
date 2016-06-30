package edu.cmu.cs.oak.nodes

object UndefNode extends DNode  {
  override def toXml = <Undef />
  override def ifdefy() = List("")  
  override def toString() = ""
  override def isEmpty() = true
  override def getChildren(): Seq[DNode] = null
}