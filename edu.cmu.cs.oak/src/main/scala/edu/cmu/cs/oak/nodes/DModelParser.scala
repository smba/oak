//package edu.cmu.cs.oak.nodes
//
//import scala.xml.NodeSeq.seqToNodeSeq
//
//import de.fosd.typechef.featureexpr.bdd.BDDFeatureExprFactory
//import edu.cmu.cs.oak.env.Constraint
//import edu.cmu.cs.oak.env.OakHeap
//import edu.cmu.cs.oak.value.SymbolValue
//
//
//object DModelParser extends App {
//  
//  def cool(label: String): Boolean = !((label equals "#PCDATA") || (label equals "Constraint"))
//  
//  def parseNode(node: scala.xml.Node): DNode = {
//    node.label match {
//      case "DataModel" => parseDataModel(node)
//      case "Concat" => parseConcatNode(node)
//      case "Repeat" => parseRepeatNode(node)
//      case "Select" => parseSelectNode(node)
//      case "Symbolic" => parseSymbolicNode(node)
//      case "Literal" => parseLiteralNode(node)
//      case "Special" => parseUndefNode(node)
//    }
//  }
//
//  def parseDataModel(node: scala.xml.Node): DNode = {
//    parseNode(node.child.filter { n => cool(n.label) }.head)
//  }
//
//  def parseUndefNode(node: scala.xml.Node): DNode = {
//    UndefNode
//  }
//
//  def parseConcatNode(node: scala.xml.Node): DNode = {
//    ConcatNode(node.child.filter { n => cool(n.label) }.map(c => parseNode(c)).toList.reverse)
//  }
//
//  def parseRepeatNode(node: scala.xml.Node): DNode = {
//    RepeatNode(new Constraint( BDDFeatureExprFactory.createDefinedExternal((node \ "Constraint").text.trim)) , ConcatNode(node.child.filter { n => cool(n.label) }.map(c => parseNode(c)).toList.reverse))
//  }
//
//  def parseSelectNode(node: scala.xml.Node): DNode = {
//    val children = node.child.filter { n => cool(n.label) }
//    SelectNode(new Constraint(BDDFeatureExprFactory.createDefinedExternal((node \ "Constraint").text.trim)), parseNode(children(0)), parseNode(children(1)))
//  }
//
//  def parseSymbolicNode(node: scala.xml.Node): DNode = {
//    //TODO ""
//    SymbolNode(SymbolValue("", OakHeap.getIndex, null))
//  }
//
//  def parseLiteralNode(node: scala.xml.Node): DNode = {
//    val text = node.attribute("Text").head.text.trim
//    val file = ""
//    val line = 0
//    LiteralNode(text, file, line)
//  }
//}