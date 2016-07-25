package edu.cmu.cs.oak.core

import scala.collection.mutable.Stack

import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakValueSequence
import scala.collection.mutable.ListBuffer


class Tree(value: OakValue) {

  var children = List[Tree]()

  override def toString = value.toString
  
  def addLeave(value: OakValue) {
    children.size match {
      case 0 => children = new Tree(value) :: children
      case _ => children.foreach { c => c.addLeave(value) }
    }
  }

  def addTrees(values: Seq[OakValue]) {
    children.size match {
      case 0 => children ++= values.map { v => new Tree(v) }
      case _ => children.foreach { c => c.addTrees(values) }
    }
  }
  
  def plainTraverse(paths: ListBuffer[OakValueSequence], stack: Stack[OakValue] = Stack[OakValue]()) {
    stack.push(value)
    children.size match {
      case 0 => {
        paths.append( new OakValueSequence(stack.reverse.toList.slice(1, stack.size)))
      }
      case _ => children.foreach { c => c.plainTraverse(paths, stack)  }
    }
    stack.pop
  }

}

object Tree {
  def construct(xs: List[OakValue]): Tree = {
    val root = new Tree(NullValue("root"))
    xs.foreach {
      x =>
        x match {
          case l: OakValueSequence => root.addTrees(l.getSequence)
          case i: OakValue => root.addLeave(i)
          case null => null
        }
    }
    root
  }
}