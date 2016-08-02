package edu.cmu.cs.oak.lib

import scala.collection.mutable.HashMap

import com.caucho.quercus.Location

import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.lib.array.ArrayPop
import edu.cmu.cs.oak.lib.array.ArraySlice
import edu.cmu.cs.oak.lib.array.ArrayWalk
import edu.cmu.cs.oak.lib.array.ArrayMap
import edu.cmu.cs.oak.lib.array.Count
import edu.cmu.cs.oak.lib.array.Current
import edu.cmu.cs.oak.lib.array.Implode
import edu.cmu.cs.oak.lib.array.IsArray
import edu.cmu.cs.oak.lib.array.Join
import edu.cmu.cs.oak.lib.array.KSort
import edu.cmu.cs.oak.lib.array.Next
import edu.cmu.cs.oak.lib.array.Reset
import edu.cmu.cs.oak.lib.builtin.Addslashes
import edu.cmu.cs.oak.lib.builtin.CallUserFunc
import edu.cmu.cs.oak.lib.builtin.CallUserFuncArray
import edu.cmu.cs.oak.lib.builtin.Chr
import edu.cmu.cs.oak.lib.builtin.Define
import edu.cmu.cs.oak.lib.builtin.Defined
import edu.cmu.cs.oak.lib.builtin.DirName
import edu.cmu.cs.oak.lib.builtin.Explode
import edu.cmu.cs.oak.lib.builtin.FileExists
import edu.cmu.cs.oak.lib.builtin.FuncGetArg
import edu.cmu.cs.oak.lib.builtin.FuncGetArgs
import edu.cmu.cs.oak.lib.builtin.FunctionExists
import edu.cmu.cs.oak.lib.builtin.InArray
import edu.cmu.cs.oak.lib.builtin.IsNull
import edu.cmu.cs.oak.lib.builtin.IsObject
import edu.cmu.cs.oak.lib.builtin.IsString
import edu.cmu.cs.oak.lib.builtin.Localizate
import edu.cmu.cs.oak.lib.builtin.Ltrim
import edu.cmu.cs.oak.lib.builtin.Ord
import edu.cmu.cs.oak.lib.builtin.PregReplace
import edu.cmu.cs.oak.lib.builtin.PregReplaceCallback
import edu.cmu.cs.oak.lib.builtin.PregSplit
import edu.cmu.cs.oak.lib.builtin.Print
import edu.cmu.cs.oak.lib.builtin.Rtrim
import edu.cmu.cs.oak.lib.builtin.Sprintf
import edu.cmu.cs.oak.lib.builtin.StrReplace
import edu.cmu.cs.oak.lib.builtin.Substr
import edu.cmu.cs.oak.lib.builtin.Trim
import edu.cmu.cs.oak.lib.builtin.UcFirst
import edu.cmu.cs.oak.lib.builtin.VersionCompare
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.lib.array.ArrayMerge
import edu.cmu.cs.oak.lib.array.ArrayKeys
import edu.cmu.cs.oak.lib.array.ArrayFlip
import edu.cmu.cs.oak.lib.array.ArrayPush
import edu.cmu.cs.oak.lib.array.ArrayValues
import edu.cmu.cs.oak.lib.builtin.Vsprintf
import edu.cmu.cs.oak.lib.builtin.Printf

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
    
    // Import from builtin
    loadPlugin(new Chr)
    loadPlugin(new Explode)
    loadPlugin(new FuncGetArg)
    loadPlugin(new FunctionExists)
    loadPlugin(new InArray)
    loadPlugin(new Ltrim)
    loadPlugin(new Rtrim)
    loadPlugin(new Trim)
    loadPlugin(new PregReplaceCallback)
    loadPlugin(new Ord)
    loadPlugin(new FileExists)
    
    loadPlugin(new Localizate)
    loadPlugin(new CallUserFunc)
    loadPlugin(new VersionCompare)
    loadPlugin(new UcFirst)
    loadPlugin(new ArrayWalk)
    loadPlugin(new ArrayMap)
    loadPlugin(new ArrayMerge)
    loadPlugin(new ArrayKeys)
    loadPlugin(new ArrayFlip)
    loadPlugin(new ArrayPush)
    loadPlugin(new ArrayValues)
    loadPlugin(new Vsprintf)
    loadPlugin(new Printf)
  }

}