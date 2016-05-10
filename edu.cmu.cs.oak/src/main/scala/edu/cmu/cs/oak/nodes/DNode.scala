package edu.cmu.cs.oak.nodes

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.value.OakValueSequence
import edu.cmu.cs.oak.value.NumericValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.BooleanValue
import edu.cmu.cs.oak.value.ArrayValue

trait DNode {
  
  def getChildren(): Seq[DNode]
  override def toString(): String
  
}

object DNode {
  
  def createDNode(v: OakValue): DNode = v match {
    case c: Choice => {
      SelectNode(c.getConstraint(), createDNode(c.v1), createDNode(c.v2))
    }
    case s: SymbolValue => {
      SymbolNode(s)
    }
    case o: OakValueSequence  => {
      val nodes = o.getSequence.map { v =>createDNode(v) }.asInstanceOf[List[DNode]]
      ConcatNode(nodes)
    }
    case n: NumericValue => {
      LiteralNode(n)
    }
    case b: BooleanValue => {
      LiteralNode(b)
    }
    case s: StringValue => {
      LiteralNode(s)
    }
    case a: ArrayValue => {
      LiteralNode(a)
    }
  }
  def createDNode(vs: Seq[OakValue]): DNode = {
    val nodes = vs.map { v =>createDNode(v) }.asInstanceOf[List[DNode]]
    ConcatNode(nodes)
  }
  
}