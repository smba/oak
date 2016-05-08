package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.OakValue

import scala.collection.mutable.ListBuffer
import edu.cmu.cs.oak.value.OakVariable
import edu.cmu.cs.oak.value.FunctionDef

import scala.collection.immutable.Stack
import org.slf4j.LoggerFactory
import edu.cmu.cs.oak.core.Interpreter

abstract class AbstractEnv(parent: EnvListener, calls: Stack[String], constraint: String) extends Environment with EnvListener {
  
  var variables = Map[String, OakVariable]()
  var output = new ListBuffer[OakValue]()
  val logger = LoggerFactory.getLogger(classOf[AbstractEnv])
  
  override def prependOutput(pre:List[OakValue]) {
    output = pre.to[ListBuffer] ++ getOutput
  }
  
  override def update(name: String, value: OakValue): Unit = {
    
    //logger.info("UPDATE("+name+", "+value+")")
    
    /* ID already used in environment? */
    if (variables.contains(name)) {
      /*ID not used in */
      OakHeap.insert(variables.get(name).get, value)
    } else {
      val variable = new OakVariable(name + OakHeap.getIndex())
      OakHeap.insert(variable, value)
      variables += (name -> variable)
    }
    //assert(value.equals(OakHeap.extract(variables.get(name).get)))
  }
  
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
    //logger.info("	LOOKUP("+name+") = " + ret)
    return ret
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

  final override def clearOutput(): Unit = {
    output = new ListBuffer[OakValue]()
  }
  final override def getOutput(): List[OakValue] = this.output.toList

  override def receiveOutput(value: OakValue) = {
    output += value
  }
  override def receiveOutput(value: Seq[OakValue]) = {
    value.foreach {
      v => output += v
    }
  }

  override def getVariables(): Map[String, OakVariable] = variables

  override final def getConstraint(): String = constraint

  override def fork(newConstraint: String): (BranchEnv, BranchEnv) = {
    val b1 = new BranchEnv(this, calls, constraint + " && " + newConstraint)
    val b2 = new BranchEnv(this, calls, constraint + " && NOT(" + newConstraint + ")")
    
    this.getVariables().keySet.foreach {
      k => {
        b1.update(k, this.lookup(k))
        b2.update(k, this.lookup(k))
      }
    }
    
    return (b1, b2)
  }
}