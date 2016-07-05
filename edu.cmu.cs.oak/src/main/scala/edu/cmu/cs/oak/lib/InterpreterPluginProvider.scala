package edu.cmu.cs.oak.core

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.lib.InterpreterPlugin
import com.caucho.quercus.expr.Expr
import edu.cmu.cs.oak.env.Environment
import scala.collection.mutable.HashMap
import edu.cmu.cs.oak.lib.array.Count
import java.net.URL
import java.nio.file.Path

/**
 * Via this plugin loader the interpreter can 
 * be extended with built-in functions, such as
 * standard library functions.
 */
trait InterpreterPluginProvider {
  
  /** List of plugin names. */
  var libraryFunctions = List[String]()
  
  /** Loaded plugins, i.e., available library functions. */
  val plugins = HashMap[String, InterpreterPlugin]()
  
  /*
   * Load plugins.
   * 
   * TODO Load plugins somewhere else
   */
  this.loadPlugin(new Count)
  
  /**
   * Gets the InterpreterPlugin of name 'name'.
   * 
   * @param name Name of the library function
   * @return plugin of name 'name'
   */
  def getPlugin(name: String): InterpreterPlugin = {
    plugins.get(name).get
  }
  
  /**
   * Loads a plugin.
   * 
   * @param plugin Plugin to load.
   */
  def loadPlugin(plugin: InterpreterPlugin): Unit = {
    plugins.put(plugin.getName, plugin)
  }

  def accept(plugin: InterpreterPlugin, args: List[Expr], loc: Path, env: Environment): OakValue = {
    plugin.visit(this, args, loc, env)
  }
  
  def getPlugins(): List[String] = {
    plugins.keySet.toList
  }

}