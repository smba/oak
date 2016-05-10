package edu.cmu.cs.oak.core

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.lib.InterpreterPlugin
import com.caucho.quercus.expr.Expr
import edu.cmu.cs.oak.env.Environment

trait InterpreterPluginProvider {
  
  def getPlugins(): List[String]
  def getPlugin(name: String): InterpreterPlugin
  def loadPlugin(plugin: InterpreterPlugin)
  def accept(plugin: InterpreterPlugin, args: List[Expr], env: Environment): OakValue
}