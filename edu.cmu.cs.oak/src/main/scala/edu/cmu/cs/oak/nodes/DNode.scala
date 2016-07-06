package edu.cmu.cs.oak.nodes

import com.caucho.quercus.expr.Expr

import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.NumericValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakValueSequence
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.value.SymbolicValue
import com.caucho.quercus.Location
import java.security.MessageDigest
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.nodes.UndefNode
import edu.cmu.cs.oak.nodes.UndefNode

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
        case (x, y) => assert(chop(x) equals chop(y) ); chop(x) equals chop(y); 
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
      case c: Choice => {
        SelectNode(c.p, createDNode(c.v1, location), createDNode(c.v2, location))
      }
      case se: OakValueSequence => {
        ConcatNode(se.getSequence.reverse.map { v => createDNode(v, location) } )
      }
      case sv: StringValue => {
        LiteralNode(sv.value, sv.getFileName(), sv.getLineNr())
      }
      case n: NullValue => UndefNode
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
      case c: SelectNode => literals ++ extractStringLiterals(c.v1, literals) ++ extractStringLiterals(c.v2, literals)
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
  
  /*
  def flatten(node: DNode): DNode = {
    node match {
      case c: ConcatNode => ConcatNode(c.values.map(v => flatten(v)))
      case l: LiteralNode => l
      case r: RepeatNode => RepeatNode(r.constraint, flatten(r.node))
      case s: SymbolNode => s
      case UndefNode => UndefNode
      case p: SelectNode => {
        
        /*
         *  		p						 p'' with (c && c')
 				 *  	 / \					/ \
				 *  	p'  ⊥  --> 	 X	 ⊥
				 *   / \
				 *  X   ⊥
         */
        if (p.v1.isInstanceOf[SelectNode] && p.v2.isEmpty() && p.v1.asInstanceOf[SelectNode].v2.isEmpty() && !p.v1.asInstanceOf[SelectNode].v1.isEmpty()) {
          val p1 = p.v1.asInstanceOf[SelectNode]
          SelectNode(p.constraint AND p1.constraint, p1.v1, UndefNode)
        } 
        
        /*
         *  		p						 p'' with (c && !c')
 				 *  	 / \					/ \
				 *  	p'  ⊥  --> 	 X	 ⊥
				 *   / \
				 *  ⊥   X
         */
        else if (p.v1.isInstanceOf[SelectNode] && p.v2.isEmpty() && p.v1.asInstanceOf[SelectNode].v1.isEmpty() && !p.v1.asInstanceOf[SelectNode].v2.isEmpty()) {
          val p1 = p.v1.asInstanceOf[SelectNode]
          SelectNode(p.constraint AND p1.constraint.NOT, p1.v2, UndefNode)
        } 
        
        /*
         *  		p						 p'' with (!c && c')
 				 *  	 / \					/ \
				 *  	⊥   p' --> 	 X	 ⊥
				 *   		 / \
				 *      X   ⊥
         */
        else if (p.v2.isInstanceOf[SelectNode] && p.v1.isEmpty() && p.v2.asInstanceOf[SelectNode].v2.isEmpty() && !p.v2.asInstanceOf[SelectNode].v1.isEmpty()) {
          val p1 = p.v2.asInstanceOf[SelectNode]
          SelectNode(p.constraint.NOT AND p1.constraint, p1.v1, UndefNode)
        } 
        
        /*
         *  		p						 p'' with (!c && !c')
 				 *  	 / \					/ \
				 *  	⊥   p' --> 	 X	 ⊥
				 *   		 / \
				 *      ⊥   X
         */
        else if (p.v2.isInstanceOf[SelectNode] && p.v1.isEmpty() && p.v2.asInstanceOf[SelectNode].v1.isEmpty() && !p.v2.asInstanceOf[SelectNode].v2.isEmpty()) {
          val p1 = p.v2.asInstanceOf[SelectNode]
          SelectNode(p.constraint.NOT AND p1.constraint.NOT, p1.v2, UndefNode)
        } 
        
        /*
         *  		p						 
 				 *  	 / \  -->   ⊥
				 *  	⊥   ⊥
         */
        else if (p.v1.isEmpty() && p.v2.isEmpty()) {
          UndefNode
        } 
        
        else {
          SelectNode(p.constraint, flatten(p.v1), flatten(p.v2))
        }
      }
    }
  }
  * */
  
}

