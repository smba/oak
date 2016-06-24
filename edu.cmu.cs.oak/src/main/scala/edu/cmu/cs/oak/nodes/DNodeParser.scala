package edu.cmu.cs.oak.nodes

import scala.xml.XML
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.env.heap.OakHeap
import edu.cmu.cs.oak.nodes.LiteralNode

/**
 * Parser XML -> DNode
 */
object DNodeParser extends App {

  var i = 0
  
  def parseNode(node: scala.xml.Node): DNode = {
    println(i)
    i += 1
    node.label match {
      case "DataModel"  => parseDataModel(node)
      case "Concat" => parseConcatNode(node)
      case "Repeat" => parseRepeatNode(node)
      case "Select" => parseSelectNode(node)
      case "Symbolic" => parseSymbolicNode(node)
      case "Literal" => parseLiteralNode(node)
    }
  }
  
  def parseDataModel(node: scala.xml.Node): DNode = {
    parseNode( (node \ "Concat")(0) )
  }
  
  def parseConcatNode(node: scala.xml.Node): DNode = {
    ConcatNode(node.child.filter { n => !("#PCDATA" equals n.label) }.map(c => parseNode(c)).toList)
  }
  
  def parseRepeatNode(node: scala.xml.Node): DNode = {
    RepeatNode(ConcatNode(node.child.filter { n => !("#PCDATA" equals n.label) }.map(c => parseNode(c)).toList))
  }
  
  def parseSelectNode(node: scala.xml.Node): DNode = {
    val children = node.child.filter { n => !("#PCDATA" equals n.label) }
    SelectNode((node \ "Constraint").text.trim, parseNode(children(1)), parseNode(children(2)))
  }
  
  def parseSymbolicNode(node: scala.xml.Node): DNode = {
    SymbolNode(SymbolValue((node \ "Text").text.trim, OakHeap.index, null))
  }
  
  def parseLiteralNode(node: scala.xml.Node): DNode = {
    LiteralNode((node \ "Text").text.trim, (node \ "File").text.trim, try {(node \ "Line").text.toInt} catch {case nfe: NumberFormatException => 0})
  }
  
  val xml = XML.loadFile("/home/stefan/Desktop/output2.xml")
  println(DNodeParser.parseNode(xml))
  /*
  var rootPath: String = null
  
  def init(rootPath: String) {
    this.rootPath = rootPath
  }

  // utility method
  private def parseNode(node: scala.xml.Node): DNode = {
    val c = (node.child).filter { n => n.label != "#PCDATA" }(0)
    c.label match {
      case "literal" => createLiteralNodeFromXml((node \ "literal")(0))
      case "select" => createSelectNodeFromXml((node \ "select")(0))
      case "concat" => createConcatNodeFromXml((node \ "concat")(0))
      case "symbol" => createSymbolNodeFromXml((node \ "symbol")(0))
      case _ => throw new RuntimeException("Could not match label \"" + node.label + "\".")
    }
  }

  def createLiteralNodeFromXml(node: scala.xml.Node): LiteralNode = {
    val c = (node.child).filter { n => n.label != "#PCDATA" }(0)
    c.label match {
      case "int" => LiteralNode(IntValue((node \ "int").text.trim.toInt))
      case "double" => LiteralNode(DoubleValue((node \ "double").text.toDouble))
      case "string" => LiteralNode(parseStringNode(c))//
      case "boolean" => LiteralNode( BooleanValue((node \ "boolean").text.trim.toBoolean))//
      case "undef" => LiteralNode( NullValue("DNodeParser::createLiteralNodeFromXml") )//
      case _ => throw new RuntimeException("Could not match label " + node.child(0).label + ".")
    }
  }

  def createSelectNodeFromXml(node: scala.xml.Node): SelectNode = {
    val condition = (node \ "condition").text.trim
    val trueNode = parseNode((node \ "trueNode")(0))
    val falseNode = parseNode((node \ "falseNode")(0))
    return SelectNode(condition, trueNode, falseNode)
  }

  
  def parseStringNode(node: scala.xml.Node): StringValue = {
    val url = rootPath.concat( (node \ "url").text )
    val line = (node \ "line").text.trim.toInt
    val content = (node \ "content").text
    val v = StringValue(content)
    v.setLocation((url, line))
    v
  }
  
  def createConcatNodeFromXml(node: scala.xml.Node): ConcatNode = {
    val nodeBuffer = new ListBuffer[DNode]
    (node \ "concatItem").foreach {
      c => nodeBuffer.append(parseNode(c))
    }
    ConcatNode(nodeBuffer.toList)
  }

  def createSymbolNodeFromXml(node: scala.xml.Node): SymbolNode = {
    val id = try {
      (node \ "id").text.toInt
    } catch {
      case e: Exception => 0
    }
    val expression = (node \ "expression").text
    return SymbolNode(SymbolValue(expression, id))
  }
  * */
  
  
}
