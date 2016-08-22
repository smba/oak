package edu.cmu.cs.oak.nodes

import java.security.MessageDigest

import com.caucho.quercus.Location

import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakValueSequence
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.env.OakHeap
import edu.cmu.cs.oak.core.SymbolFlag
import edu.cmu.cs.oak.nodes.SymbolNode
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.MapChoice


/**
 * Model for  output of a symbolically executed PHP program.
 *
 * @author Stefan Muehlbauer <smuhlbau@andrew.cmu.edu>
 */
abstract class DNode {

  /**
   * Sequence of all child nodes of the current node.
   *
   * @return Sequence of child nodes
   */
  def getChildren(): Seq[DNode]

  /**
   * Recursively serializes a DNode-based tree to XML.
   *
   * @return scala.xml.Elem XML node
   */
  def toXml(): scala.xml.Elem

  /**
   * Returns a sequence of output strings mixed with #ifdef annotations
   * @return List of output strings
   */
  def ifdefy(): List[String]

  /**
   * Compare method for DNodes.
   *
   * @param that DNode tree to compare
   * @return this.equals(that)?
   */
  def compare(that: DNode): Boolean = {

    def chop(s: String): String = {
      s.split('\n').map(_.trim.filter(_ >= ' ')).mkString.replace(" ", "")
    }

    val ifd1 = this.ifdefy()
    val ifd2 = that.ifdefy()

    if (ifd1.size == ifd2.size) {
      (ifd1 zip ifd2).map {
        case (x, y) => {
          chop(x) equals chop(y)
        }
      }.fold(true)(_ && _)
    } else {
      false
    }
  }

  def md5(s: String) = {
    MessageDigest.getInstance("MD5").digest(s.getBytes)
  }

  def isEmpty(): Boolean
}

/**
 * Utility methods in order to create a DModel from given output.
 */
object DNode {

  def createDNode(value: OakValue, location: Location): DNode = {
    value match {
      case s: SymbolValue => {
        SymbolNode(s)
      }
      case c: MapChoice => {
        val x = c.toMap().map{
          case (v, cs) => (createDNode(v, location), cs)
        }
        SelectNode(x)
      }
      case se: OakValueSequence => {
        ConcatNode(se.getSequence.reverse.map { v => createDNode(v, location) })
      }
      case sv: StringValue => {
        LiteralNode(sv.value, sv.getFileName(), sv.getLineNr())
      }
      case NullValue => UndefNode
      case null => UndefNode
      case _ => {
        LiteralNode(value.toString(), location.getFileName, location.getLineNumber)
      }
    }
  }

  /**
   * Extracts all string literals as StringValues from
   * the DModel tree.
   *
   * @param node DModel tree root node
   * @return List of StringValues, which are literals in the DModel
   */
  def extractStringLiterals(node: DNode): Set[StringValue] = {

    // utility method for DModel tree traversal
    def extractStringLiterals(node: DNode, literals: List[StringValue]): List[StringValue] = node match {
      case c: SelectNode => {
        literals ++ c.mapc.keySet.flatMap(node => extractStringLiterals(node, literals)).toList
      }
      case s: ConcatNode => {
        var lits = List[StringValue]()
        s.getChildren.foreach {
          c => lits ++= extractStringLiterals(c, List[StringValue]())
        }
        literals ++ lits
      }
      case l: LiteralNode => literals ++ List(StringValue(l.sv, l.file, l.lineNr))
      case r: RepeatNode => {
        literals ++ extractStringLiterals(r.node, literals)
      }
      case _ => List[StringValue]()
    }

    // apply utility method
    return extractStringLiterals(node, List[StringValue]()).toSet
  }

}

