package edu.cmu.cs.oak.analysis

case class Node(id: String) {
  def toXml: scala.xml.Elem = {
    <node id={ id }/>
  }
}

case class IncludeEdge(from: Node, to: Node) {
  def toXml: scala.xml.Elem = {
    <edge source={ from.id } target={ to.id }/>
  }
}

class GraphListener(name: String) {

  var nodes = Map[String, Node]()
  var edges = Set[IncludeEdge]()

  def addEdge(from1: String, to1: String) {

    val from = from1.replace("\"", "")
    val to = to1.replace("\"", "")

    if (!nodes.keySet.contains(from)) {
      nodes += (from -> Node(from))
    }
    if (!nodes.keySet.contains(to)) {
      nodes += (to -> Node(to))
    }

    edges += IncludeEdge(nodes.get(from).get, nodes.get(to).get)
  }

  def toXml: scala.xml.Elem = {
    <graphml xmlns='http://graphml.graphdrawing.org/xmlns' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation='http://graphml.graphdrawing.org/xmlns/1.0/graphml.xsd'>
      <graph id={ name } edgedefault='directed'>
        { for (node <- nodes.keySet.toList.sorted) yield { nodes.get(node).get.toXml } }
        { for (edge <- edges) yield { edge.toXml } }
      </graph>
    </graphml>
  }
}