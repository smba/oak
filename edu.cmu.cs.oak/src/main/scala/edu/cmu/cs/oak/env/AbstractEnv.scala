package edu.cmu.cs.oak.env

import scala.collection.immutable.Stack
import scala.collection.mutable.ListBuffer

import org.slf4j.LoggerFactory

import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.FunctionDef
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakValueSequence
import edu.cmu.cs.oak.value.OakVariable
import edu.cmu.cs.oak.value.SymbolValue

/**
 * Programs state and program state operations.
 */
abstract class AbstractEnv(parent: EnvListener, calls: Stack[String], constraint: String) extends Environment {

  /** Map of variable identifiers and variable references.
   *  In various contexts, variables can refer to the same value.
   *	For variable reference handling {@see {@link #edu.cmu.cs.oak.env.OakHeap}}. */
  var variables = Map[String, OakVariable]()
  
  /** Mutable output trace. All output genereated in this environment will
   *  be cached and sent to the parent environment unless this is the global
   *  environment (parent == null). */
  var output = new ListBuffer[OakValue]()

  val logger = LoggerFactory.getLogger(classOf[AbstractEnv])

  /**
   * Updates a variable in the environment, i.e. it stores a variable
   * assignment, such as '$i = 8'.
   * 
   * @paran name Name of the variable
   * @param value OakValue to assign to the variable
   */
  override def update(name: String, value: OakValue): Unit = {
    
    if (variables.contains(name)) {
      OakHeap.insert(variables.get(name).get, value)
    } else {
      val variable = new OakVariable(name + OakHeap.getIndex())
      OakHeap.insert(variable, value)
      variables += (name -> variable)
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

  override def prependOutput(pre: List[OakValue]) {
    output = pre.to[ListBuffer] ++ getOutput
  }
  
  final override def defineFunction(value: FunctionDef): Unit = {
    OakHeap.defineFunction(value)
  }

  final override def getFunction(name: String): FunctionDef = {
    OakHeap.getFunction(name)
  }

  final override def getCalls(): Stack[String] = calls

  final override def addOutput(value: OakValue): Unit = {
    output += value
  }

  override def createFunctionEnvironment(f: String): Environment = {
    return new FunctionEnv(this, calls push f, constraint)
  }

  final override def getParent(): EnvListener = this.parent
  /*
  final override def clearOutput(): Unit = {
    output = new ListBuffer[OakValue]()
  }
  * override def receiveOutput(value: OakValue) = {
    output += value
  }
  */
  final override def getOutput(): List[OakValue] = this.output.toList

  
  override def receiveOutput(value: Seq[OakValue]) = {
    value.foreach {
      v => output += v
    }
  }

  override def getVariables(): Map[String, OakVariable] = variables

  override final def getConstraint(): String = constraint

  /**
   * Splits an environment into two branch environments that 
   * can be joined afterwards.
   * 
   * @param newConstraint Path constrained to add to the branches
   * 
   * @param Tuple of two branch environments 
   */
  override def fork(newConstraint: String): (BranchEnv, BranchEnv) = {
    val b1 = new BranchEnv(this, calls, constraint + " && " + newConstraint)
    val b2 = new BranchEnv(this, calls, constraint + " && NOT(" + newConstraint + ")")

    /* Add variables of parent environment to the branch environments. */
    this.getVariables.keySet.foreach {
      k =>
        {
          b1.update(k, this.lookup(k))
          b2.update(k, this.lookup(k))
        }
    }

    return (b1, b2)
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
  
  def ifdefy(): List[String] = {
    var res = List[String]()
    output.foreach { n => res = res ++ ifdefy(n) }
    return res
  }
}