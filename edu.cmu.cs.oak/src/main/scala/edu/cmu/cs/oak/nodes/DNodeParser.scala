package edu.cmu.cs.oak.nodes

import scala.xml.XML
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.env.OakHeap
import edu.cmu.cs.oak.env.Constraint

/**
 * Parser XML -> DNode
 * http://alvinalexander.com/scala/how-to-extract-data-from-xml-nodes-in-scala
 */
object DNodeParser extends App {

  def p(label: String): Boolean = !((label equals "#PCDATA") || (label equals "Constraint"))
  
  def parseNode(node: scala.xml.Node): DNode = {
    node.label match {
      case "DataModel"  => parseDataModel(node)
      case "Concat" => parseConcatNode(node)
      case "Repeat" => parseRepeatNode(node)
      case "Select" => parseSelectNode(node)
      case "Symbolic" => parseSymbolicNode(node)
      case "Literal" => parseLiteralNode(node)
      case "Undef" => parseUndefNode(node)
    }
  }
  
  def parseDataModel(node: scala.xml.Node): DNode = {
    parseNode( (node \ "Concat")(0) )
  }
  
  def parseUndefNode(node: scala.xml.Node): DNode = {
    UndefNode
  }
  
  def parseConcatNode(node: scala.xml.Node): DNode = {
    ConcatNode(node.child.filter { n => p(n.label) }.map(c => parseNode(c)).toList.reverse)
  }
  
  def parseRepeatNode(node: scala.xml.Node): DNode = {
    RepeatNode(Constraint( (node \ "Constraint").text.trim), ConcatNode(node.child.filter { n => p(n.label) }.map(c => parseNode(c)).toList.reverse))
  }
  
  def parseSelectNode(node: scala.xml.Node): DNode = {
    val children = node.child.filter { n => p(n.label) }
    SelectNode(Constraint( (node \ "Constraint").text.trim), parseNode(children(0)), parseNode(children(1)))
  }
  
  def parseSymbolicNode(node: scala.xml.Node): DNode = {
    SymbolNode(SymbolValue(node.attribute("Text").head.text.trim, OakHeap.getIndex, null))
  }
  
  def parseLiteralNode(node: scala.xml.Node): DNode = {
    val text = node.attribute("Text").head.text.trim
    val file = node.attribute("File").size match {
      case 0 => ""
      case _ => node.attribute("File").head.text.trim
    }
    val line = try {
      node.attribute("Line").head.text.toInt
    } catch {
      case nfe: NumberFormatException => 0
    }
    LiteralNode(text, file, line)
  }
  
}
