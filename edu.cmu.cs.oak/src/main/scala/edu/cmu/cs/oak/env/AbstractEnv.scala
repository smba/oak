package edu.cmu.cs.oak.env

import scala.annotation.elidable
import scala.annotation.elidable.ASSERTION
import scala.collection.immutable.Stack
import scala.collection.mutable.ListBuffer

import org.slf4j.LoggerFactory

import edu.cmu.cs.oak.env.heap.OakHeap
import edu.cmu.cs.oak.exceptions.VariableNotFoundException
import edu.cmu.cs.oak.nodes.ConcatNode
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.nodes.SelectNode
import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.FunctionDef
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakValueSequence
import edu.cmu.cs.oak.value.OakVariable
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.value.OakValueRepeatSequence


abstract class AbstractEnv(parent: EnvListener, calls: Stack[String], constraint: String) extends Environment {

  

  

  
}