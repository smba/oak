package edu.cmu.cs.oak.env

import com.caucho.quercus.statement.Statement
import com.caucho.quercus.expr.Expr

case class FunctionDef(name: String, args: Array[String], defaults: Map[String, Expr], statement: Statement, hr: Boolean, ref: Boolean) {
  def getName = name
  
  def getArgs = args

  def getStatement = statement

  def hasReturn = hr
  
  def returnsRef(): Boolean = ref
}