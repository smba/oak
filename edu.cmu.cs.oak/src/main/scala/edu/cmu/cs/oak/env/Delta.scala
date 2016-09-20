package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.Reference
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.nodes.SelectNode
import scala.collection.mutable.AnyRefMap
import edu.cmu.cs.oak.value.StringValue

class Delta(output: DNode, var variables: collection.mutable.AnyRefMap[String, OakValue], varval: collection.mutable.AnyRefMap[Reference, OakValue], staticlassFields: collection.mutable.Map[String, collection.immutable.Map[String, OakValue]], globals: Set[String], constants: Map[String, OakValue], functionDefs: AnyRefMap[String, FunctionDef], classDefs: AnyRefMap[String, ClassDef], touched: Set[StringValue], include_history: Map[(String, Int), Boolean], undefinedFunctions: Map[String, Int]) {
  def joinedOutput = output
  def joinedVariables = variables
  def joinedHeap = varval
  def joinedGlobals = globals
  def joinedStaticClassVariables = staticlassFields
  def joinedConstants = constants
  def joinedFunctionDefs = functionDefs
  def joinedClassDefs = classDefs
  def joinedTouchedStringLiterals = touched
  def joinedIncludeHistory = include_history
  def joinedUndefinedfunctions = undefinedFunctions
}