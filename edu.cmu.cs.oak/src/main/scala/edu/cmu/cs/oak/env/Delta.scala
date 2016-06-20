package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakVariable
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.value.ClassDef

class Delta(output: DNode, variables: Map[String, OakValue], varval: Map[OakVariable, OakValue], classes: Set[ClassDef], globals: Set[String]) {
  def joinedOutput = output
  def joinedVariables = variables
  def joinedHeap = varval
  def joinedClassDefs = classes
  def joinedGlobals = globals
}