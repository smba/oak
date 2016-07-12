package edu.cmu.cs.oak.core

import scala.collection.mutable.Stack

import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakValueSequence

object IncludePathConstructor extends App {

  val seq = new OakValueSequence(List(NullValue("1"), NullValue("2"), new OakValueSequence(List(NullValue("1"), NullValue("2"))), NullValue("4"), new OakValueSequence(List(NullValue("44"), NullValue("66")))))

  val tri = Tree.construct(seq.getSequence)
  println(tri.traverse())
}

class Tree(value: OakValue) {

  var children = List[Tree]()

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

  def traverse(stack: Stack[OakValue] = Stack[OakValue]()): List[OakValueSequence] = {
    stack.push(value)
    val list = children.size match {
      case 0 => return List(new OakValueSequence(stack.reverse.toList.slice(1, stack.size)))
      case _ => {
        var list = List[OakValueSequence]()
        children.foreach { c =>
          list ++= c.traverse(stack)
        }
        list
      }
    }
    stack.pop
    list
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
        }
    }
    root
  }
}