package edu.cmu.cs.oak.nodes

import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.analysis.inlcude.OutputGraphListener

case class SymbolNode(sv: SymbolValue) extends DNode {
  
  override def traverse(listener: OutputGraphListener) {  }
  
  def getChildren(): Seq[DNode] = null

  override def toXml = {
    <symbol>
      {sv.toString}
    </symbol>
  }
  
  override def ifdefy(): List[String] = List(sv.toString())

}