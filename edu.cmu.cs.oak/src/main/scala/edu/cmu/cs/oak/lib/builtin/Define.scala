package edu.cmu.cs.oak.lib.builtin

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.core.InterpreterPluginProvider
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.env.Environment
import java.nio.file.Path
import com.caucho.quercus.expr.Expr
import edu.cmu.cs.oak.core.Interpreter
import edu.cmu.cs.oak.value.NullValue

class Define extends InterpreterPlugin {
  
  override def getName(): String = "define"

  override def visit(provider: InterpreterPluginProvider, args: List[Expr], loc: (Path, Int), env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[Interpreter]

    /* Assert that the function has two arguments */
    assert(args.size == 2)

    val constantIdentifier = interpreter.evaluate(args(0), env)._1
    val constantValue = interpreter.evaluate(args(1), env)._1
    
    interpreter.addConstants( constantIdentifier.toString , constantValue )
    
    null 
  }
  
}