package edu.cmu.cs.oak.lib.builtin

import java.nio.file.Path

import com.caucho.quercus.expr.Expr

import edu.cmu.cs.oak.lib.InterpreterPluginProvider
import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.value.BooleanValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.BooleanValue

class Defined extends InterpreterPlugin {
  
  override def getName(): String = "defined"

  override def visit(provider: InterpreterPluginProvider, args: List[Expr], loc: Path, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has one argument */
    assert(args.size == 1)
    
    val b = BooleanValue( env.getConstant(args(0).toString.replace("\"", "")).isInstanceOf[NullValue])
  
    return b
  }
  
}