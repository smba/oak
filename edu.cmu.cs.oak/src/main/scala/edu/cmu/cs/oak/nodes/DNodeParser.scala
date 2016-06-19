package edu.cmu.cs.oak.nodes

import scala.collection.mutable.ListBuffer

import edu.cmu.cs.oak.value.DoubleValue
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.SymbolValue
import java.net.URL
import edu.cmu.cs.oak.value.BooleanValue
import java.nio.file.Paths
import edu.cmu.cs.oak.value.NullValue

/**
 * Parser XML -> DNode
 */
object DNodeParser {
  
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