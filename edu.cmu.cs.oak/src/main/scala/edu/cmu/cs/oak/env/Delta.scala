package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakVariable
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.nodes.SelectNode

class Delta(output: DNode, var variables: collection.mutable.AnyRefMap[String, OakValue], varval: collection.mutable.AnyRefMap[OakVariable, OakValue], staticlassFields: collection.mutable.Map[String, collection.immutable.Map[String, OakValue]], globals: Set[String], constants: Map[String, OakValue]) {
  def joinedOutput = output
  def joinedVariables = variables
  def joinedHeap = varval
  def joinedGlobals = globals
  def joinedStaticClassVariables = staticlassFields
  def joinedConstants = constants
}