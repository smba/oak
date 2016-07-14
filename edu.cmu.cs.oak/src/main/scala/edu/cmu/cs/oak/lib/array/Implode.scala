package edu.cmu.cs.oak.lib.array

import java.nio.file.Path

import scala.annotation.migration

import com.caucho.quercus.expr.Expr

import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.core.SymbolFlag
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.env.OakHeap
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.lib.InterpreterPluginProvider
import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.value.SymbolicValue

class Implode extends InterpreterPlugin {

  override def getName(): String = "implode"

  override def visit(provider: InterpreterPluginProvider, args: List[Expr], loc: Path, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has been o*/
    assert(args.size > 0)
    val pieces = interpreter.evaluate( args(if (args.size == 1) 0 else 1), env)
    
    val glue = if (args.size == 1) {
      "" 
    } else { 
      val p = interpreter.evaluate( args(1), env) 
      p match { 
        case null => "" 
        case _ => p.toString()
      }
    }
    
    return pieces match {
      case av: ArrayValue => {
        StringValue(av.array.values.mkString(glue), "", 0)
      }
      case s: SymbolicValue => s
      case _ => pieces//SymbolValue(env.getCalls().head.toString, OakHeap.getIndex, SymbolFlag.DUMMY)
    }

    // TODO remove stub with implementation
    return interpreter.evaluate(args(2), env)
  }

}