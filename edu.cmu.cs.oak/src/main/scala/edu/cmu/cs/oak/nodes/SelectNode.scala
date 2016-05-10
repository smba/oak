package edu.cmu.cs.oak.nodes

case class SelectNode(condition: String, v1: DNode, v2: DNode) extends DNode {
  
  def getChildren(): Seq[DNode] = List(v1, v2)
  
  override def toString(): String = {
    val sb = new StringBuilder
    sb append "#ifdef " + condition + "\n"
    sb append  v1 +"\n"
    sb append "#else" + "\n"
    sb append  v2 + "\n"
    sb append "#end "
    sb toString
  }
  
}