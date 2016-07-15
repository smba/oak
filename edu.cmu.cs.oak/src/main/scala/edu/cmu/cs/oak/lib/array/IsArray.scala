package edu.cmu.cs.oak.lib.array

import java.nio.file.Path

import com.caucho.quercus.expr.Expr

import edu.cmu.cs.oak.lib.InterpreterPluginProvider
import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.value.BooleanValue
import edu.cmu.cs.oak.value.OakValue
import com.caucho.quercus.Location

class IsArray extends InterpreterPlugin {
  override def getName(): String = "is_array"
  
  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {
    
    
    
    /* Assert that the function has only 
     * been called with exactly one argument. */
    assert(args.size == 1)
    
    val v = args(0)
    return BooleanValue(v match {
      case a: ArrayValue => true
      case _ => false
    } )
    
  }
}