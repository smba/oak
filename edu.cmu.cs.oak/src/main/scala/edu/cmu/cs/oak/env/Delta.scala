package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.Reference
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.nodes.SelectNode
import scala.collection.mutable.AnyRefMap

class Delta(output: DNode, var variables: collection.mutable.AnyRefMap[String, OakValue], varval: collection.mutable.AnyRefMap[Reference, OakValue], staticlassFields: collection.mutable.Map[String, collection.immutable.Map[String, OakValue]], globals: Set[String], constants: Map[String, OakValue], functionDefs: AnyRefMap[String, FunctionDef], classDefs: AnyRefMap[String, ClassDef]) {
  def joinedOutput = output
  def joinedVariables = variables
  def joinedHeap = varval
  def joinedGlobals = globals
  def joinedStaticClassVariables = staticlassFields
  def joinedConstants = constants
  def joinedFunctionDefs = functionDefs
  def joinedClassDefs = classDefs
}