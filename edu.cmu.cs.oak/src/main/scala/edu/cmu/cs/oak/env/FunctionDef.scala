package edu.cmu.cs.oak.env

import com.caucho.quercus.statement.Statement

/**
  * Created by stefan on 04/05/16.
  */
case class FunctionDef(name: String, args: Array[String], statement: Statement, hr: Boolean) {
  def getName = name

  def getArgs = args

  def getStatement = statement

  def hasReturn = hr
}
