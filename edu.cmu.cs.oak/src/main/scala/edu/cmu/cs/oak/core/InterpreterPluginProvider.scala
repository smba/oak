package edu.cmu.cs.oak.core

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.lib.InterpreterPlugin
import com.caucho.quercus.expr.Expr
import edu.cmu.cs.oak.env.Environment
import scala.collection.mutable.HashMap

trait InterpreterPluginProvider {
  
  val plugins = HashMap[String, InterpreterPlugin]()
  
    /*
   * Library mechanism
   */
  def getPlugin(name: String): InterpreterPlugin = {
    plugins.get(name).get
  }
  
  def getPlugins(): List[String] = {
    plugins.keySet.toList
  }
  
  def loadPlugin(plugin: InterpreterPlugin): Unit = {
    plugins.put(plugin.getName, plugin)
  }
  
  def accept(plugin: InterpreterPlugin, args: List[Expr], env: Environment): OakValue = {
    plugin.visit(this, args, env)
  }

}