package edu.cmu.cs.oak.lib.builtin

import java.nio.file.Path

import scala.annotation.elidable
import scala.annotation.elidable.ASSERTION

import com.caucho.quercus.expr.Expr

import edu.cmu.cs.oak.core.Interpreter
import edu.cmu.cs.oak.core.InterpreterPluginProvider
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.value.BooleanValue
import edu.cmu.cs.oak.value.OakValue

class Defined extends InterpreterPlugin {
  
  override def getName(): String = "defined"

  override def visit(provider: InterpreterPluginProvider, args: List[Expr], loc: (Path, Int), env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[Interpreter]

    /* Assert that the function has one argument */
    assert(args.size == 1)

    val b = BooleanValue( (interpreter.constants.keySet contains args(0).toString.replace("\"", "")) )
  
    return b
  }
  
}