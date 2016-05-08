package edu.cmu.cs.oak.value

import com.caucho.quercus.statement.Statement

case class FunctionDef(name: String, args: Array[String], statement: Statement, hr: Boolean) extends OakValue {
  def getName = name

  def getArgs = args

  def getStatement = statement

  def hasReturn = hr
}