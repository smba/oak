package edu.cmu.cs.oak.value

import edu.cmu.cs.oak.nodes.DNode

trait OakValue {
  def toXml(): scala.xml.Elem
}