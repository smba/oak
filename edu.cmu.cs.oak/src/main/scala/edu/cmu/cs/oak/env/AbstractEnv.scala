package edu.cmu.cs.oak.env

import scala.collection.immutable.Stack
import scala.collection.mutable.ListBuffer

import org.slf4j.LoggerFactory

import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.ClassDef
import edu.cmu.cs.oak.value.FunctionDef
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakValueSequence
import edu.cmu.cs.oak.value.OakVariable
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.value.ObjectValue
import edu.cmu.cs.oak.nodes.DNode

/**
 * Programs state and program state operations.
 * 
 * @author Stefan Muehlbauer <smuhlbau@andrew.cmu.edu>
 */
abstract class AbstractEnv(parent: EnvListener, calls: Stack[String], constraint: String) extends Environment {

  /**
   * Map of variable identifiers and variable references.
   * In various contexts, variables can refer to the same value.
   * For variable reference handling {@see {@link #edu.cmu.cs.oak.env.OakHeap}}.
   */
  var variables = Map[String, OakVariable]()

  /**
   * Mutable output trace. All output genereated in this environment will
   *  be cached and sent to the parent environment unless this is the global
   *  environment (parent == null).
   */
  var output = new ListBuffer[OakValue]()

  /**
   * Map of class definitions. All classes defined during the program execution
   * are stored here.
   */
  var classDefs = Map[String, ClassDef]()

  /**
   * Logger instance for environments.
   */
  val logger = LoggerFactory.getLogger(classOf[AbstractEnv])

  /**
   * Updates a variable in the environment, i.e. it stores a variable
   * assignment, such as '$i = 8'.
   *
   * @paran name Name of the variable
   * @param value OakValue to assign to the variable
   */
  override def update(name: String, value: OakValue): Unit = value match {
    case a: OakVariable => {
      variables += (name -> a)
    }
    case _ => {
      if (variables.contains(name)) {
        OakHeap.insert(variables.get(name).get, value)
      } else {
        val variable = new OakVariable(name + OakHeap.getIndex())
        OakHeap.insert(variable, value)
        variables += (name -> variable)
      }
    }
  }

  /**
   * Looks up an variable and returns its context-dependant value.
   *
   * @param name Name of the variable
   *
   * @return value Value of the variable
   */
  override def lookup(name: String): OakValue = {
    val variable = {
      val opt = variables.get(name)
      if (!opt.isEmpty) {
        opt.get
      } else {
        throw new RuntimeException("Unassigned variable " + name + ".")
      }
    }
    val ret = try {
      OakHeap.extract(variable)
    } catch {
      case e: Exception => throw new RuntimeException(e)
    }
    ret
  }
  
  /**
   * Creates a D Model tree of the (symbolic) output that
   * can easily be traversed. 
   * 
   * @return Root node of the D Model tree 
   */
  final override def getOutputModel(): DNode = DNode.createDNode(output.toList)
  
  
  
  override def receiveOutput(value: Seq[OakValue]) = {
    value.foreach {
      v => output += v
    }
  }

  override def addClass(value: ClassDef): Unit = {
    classDefs += (value.getName -> value)
  }
  override def getClass(name: String): ClassDef = {
    try {
      classDefs.get(name).get
    } catch {
      case nsee: NoSuchElementException => throw new RuntimeException("Class " + name + " is not defined.")
    }
  }

  def ifdefy(node: OakValue): List[String] = {
    var res = List[String]()
    node match {
      case seq: OakValueSequence => {
        seq.getSequence.foreach { v => res = res ++ ifdefy(v) }
      }
      case ite: Choice => {
        res = res ++ List("#if " + ite.getConstraint())
        res = res ++ ifdefy(ite.getV1()).map { x => x.trim }
        res = res ++ List("#else")
        res = res ++ ifdefy(ite.getV2()).map { x => x.trim }
        res = res ++ List("#endif")
      }
      case s: SymbolValue => res = res.::(s.toString())
      case null => res
      case _ => res = res.::(node.toString())
    }
    res
  }

  override def setRef(name: String, ref: OakVariable): Unit = {
    variables += (name -> ref)
  }

  override def getRef(name: String): OakVariable = {
    variables.get(name).get
  }
  
  final override def prependOutput(pre: List[OakValue]) { output = pre.to[ListBuffer] ++ getOutput }
  final override def addFunction(value: FunctionDef) { OakHeap.defineFunction(value) }
  final override def getFunction(name: String): FunctionDef = { OakHeap.getFunction(name) }
  final override def addOutput(value: OakValue) { output += value }

  def ifdefy(): List[String] = {
    var res = List[String]()
    output.foreach { n => res = res ++ ifdefy(n) }
    return res
  }
  
  def toXml = {
    <state>
			{for (key <- variables.keySet) yield 
			  <variable>
			    <key>{key}</key>
					<value>{OakHeap.extract(variables.get(key).get).toXml}</value>
				</variable>}
		</state>
  }
  
  /* ----- Getter methods ----- */
  final override def getCalls(): Stack[String] = calls
  final override def getParent(): EnvListener = parent
  final override def getOutput(): List[OakValue] = output.toList
  final override def getVariables(): Map[String, OakVariable] = variables
  final override def getConstraint(): String = constraint
}