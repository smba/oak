package edu.cmu.cs.oak.nodes

import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.analysis.inlcude.OutputGraphListener

case class SymbolNode(sv: SymbolValue) extends DNode {

  def getChildren(): Seq[DNode] = null

  override def toXml = {
    <Symbolic Text={sv.toString()} />
  }
  
  override def ifdefy(): List[String] = List(sv.toString())

}