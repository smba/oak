package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakVariable
import edu.cmu.cs.oak.nodes.DNode

class Delta(output: DNode, variables: Map[String, OakValue], varval: Map[OakVariable, OakValue]) {
  def joinedOutput = output
  def joinedVariables = variables
  def joinedHeap = varval
}