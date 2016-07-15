package edu.cmu.cs.oak.lib

import edu.cmu.cs.oak.value.OakValue
import com.caucho.quercus.expr.Expr
import edu.cmu.cs.oak.env.Environment
import scala.collection.mutable.HashMap
import java.nio.file.Path
import edu.cmu.cs.oak.lib.builtin.Define
import edu.cmu.cs.oak.lib.builtin.Print
import edu.cmu.cs.oak.lib.array.Implode
import edu.cmu.cs.oak.lib.builtin.Defined
import edu.cmu.cs.oak.lib.array.ArrayPop
import edu.cmu.cs.oak.lib.builtin.PregReplace
import edu.cmu.cs.oak.lib.builtin.FuncGetArgs
import edu.cmu.cs.oak.lib.builtin.StrReplace
import edu.cmu.cs.oak.lib.builtin.CallUserFuncArray
import edu.cmu.cs.oak.lib.builtin.Addslashes
import edu.cmu.cs.oak.lib.builtin.DirName
import edu.cmu.cs.oak.lib.array.IsArray
import edu.cmu.cs.oak.lib.array.KSort
import edu.cmu.cs.oak.lib.builtin.Substr
import edu.cmu.cs.oak.lib.builtin.IsSet
import edu.cmu.cs.oak.lib.array.ArraySlice
import edu.cmu.cs.oak.lib.builtin.PregSplit
import edu.cmu.cs.oak.lib.array.Next
import edu.cmu.cs.oak.lib.array.Count
import edu.cmu.cs.oak.lib.array.Current
import edu.cmu.cs.oak.lib.array.Join
import edu.cmu.cs.oak.lib.array.Reset
import edu.cmu.cs.oak.lib.builtin.Sprintf
import com.caucho.quercus.Location
import edu.cmu.cs.oak.lib.builtin.IsNull
import edu.cmu.cs.oak.lib.builtin.IsObject
import edu.cmu.cs.oak.lib.builtin.IsString

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
    
  /**
   * Gets the InterpreterPlugin of name 'name'.
   * 
   * @param name Name of the library function
   * @return plugin of name 'name'
   */
  protected def getPlugin(name: String): InterpreterPlugin = {
    plugins.get(name).get
  }
  
  /**
   * Loads a plugin.
   * 
   * @param plugin Plugin to load.
   */
  private def loadPlugin(plugin: InterpreterPlugin): Unit = {
    plugins.put(plugin.getName, plugin)
  }

  protected def accept(plugin: InterpreterPlugin, args: List[OakValue], loc: Location, env: Environment): OakValue = {
    plugin.visit(this, args, loc, env)
  }
  
  protected def getPlugins(): List[String] = {
    plugins.keySet.toList
  }
  
  def loadPlugins() {
    loadPlugin(new Count)
    loadPlugin(new Print)
    loadPlugin(new DirName)
    loadPlugin(new Define)
    loadPlugin(new Defined)
    loadPlugin(new IsArray)
    loadPlugin(new IsSet)
    loadPlugin(new Sprintf)
    loadPlugin(new CallUserFuncArray)
    loadPlugin(new Substr)
    loadPlugin(new StrReplace)
    loadPlugin(new ArrayPop)
    loadPlugin(new ArraySlice)
    loadPlugin(new Current)
    loadPlugin(new Next)
    loadPlugin(new Reset)
    loadPlugin(new KSort)
    loadPlugin(new FuncGetArgs)
    loadPlugin(new PregReplace)
    loadPlugin(new Addslashes)
    loadPlugin(new Join)
    loadPlugin(new Implode)
    loadPlugin(new PregSplit)
    loadPlugin(new Implode)
    loadPlugin(new IsNull)
    loadPlugin(new IsString)
    loadPlugin(new IsObject)
  }

}