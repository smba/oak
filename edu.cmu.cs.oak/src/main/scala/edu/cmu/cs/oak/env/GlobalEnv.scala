package edu.cmu.cs.oak.env

import scala.collection.immutable.{Map, Stack}
import scala.collection.mutable.ListBuffer

/**
  * Created by stefan on 04/05/16.
  */
class GlobalEnv extends Context {

  val allOutput = new ListBuffer[OakValue]
  val functions = new scala.collection.mutable.HashMap[String, FunctionDef]()

  def record(value: OakValue): Unit = {
    allOutput.append(value)
  }

  def record(value: Stack[OakValue]): Unit = {
    allOutput ++= value
  }

  def addOutput(value: OakValue): Context = {
    record(value)
    return this
  }

  /**
    * Creates an immutable environment
    *
    * @return
    */
  def createSimpleEnvironment(): Environment = {
    new SimpleEnvironment(null, Map[String, OakValue](), new Stack[OakValue], new Stack[String], "true", collection.immutable.Map(functions.toList: _*))
  }

  /**
    * Adds a Function declaration to the environment
    *
    * @param f com.caucho.quercus.program.Function instance
    * @return
    */
  def addFunction(f: FunctionDef): Unit = {
    functions += f.name -> f
  }
}
