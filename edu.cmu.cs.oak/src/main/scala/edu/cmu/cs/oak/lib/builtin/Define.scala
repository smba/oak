package edu.cmu.cs.oak.lib.builtin

import java.nio.file.Path

import scala.annotation.elidable
import scala.annotation.elidable.ASSERTION

import com.caucho.quercus.expr.Expr

import edu.cmu.cs.oak.core.Interpreter
import edu.cmu.cs.oak.core.InterpreterPluginProvider
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.value.OakValue

class Define extends InterpreterPlugin {
  
  override def getName(): String = "define"

  override def visit(provider: InterpreterPluginProvider, args: List[Expr], loc: Path, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[Interpreter]

    /* Assert that the function has two arguments */
    assert(args.size == 2)

    val constantIdentifier = interpreter.evaluate(args(0), env)._1
    val constantValue = interpreter.evaluate(args(1), env)._1
    
    interpreter.addConstants( constantIdentifier.toString , constantValue )
    
    null 
  }
  
}