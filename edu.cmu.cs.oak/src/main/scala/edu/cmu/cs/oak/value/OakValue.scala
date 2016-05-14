package edu.cmu.cs.oak.value

import edu.cmu.cs.oak.nodes.DNode

trait OakValue {
  def toDModel(): DNode = DNode.createDNode(this)
}