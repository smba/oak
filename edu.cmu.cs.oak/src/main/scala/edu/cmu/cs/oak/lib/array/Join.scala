package edu.cmu.cs.oak.lib.array

import java.nio.file.Path

import scala.annotation.migration

import com.caucho.quercus.expr.Expr

import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.lib.InterpreterPluginProvider
import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.SymbolicValue
import edu.cmu.cs.oak.core.SymbolFlag
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.env.OakHeap
import com.caucho.quercus.Location

class Join extends InterpreterPlugin {

  override def getName(): String = "join"

  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has been o*/
    assert(args.size > 0)
    val pieces = args(if (args.size == 1) 0 else 1)
    val glue = if (args.size == 1) "" else args(1).toString()
    
    return pieces match {
      case av: ArrayValue => {
        StringValue(av.array.values.mkString(glue), "", 0)
      }
      case s: SymbolicValue => s
      case _ => SymbolValue("", OakHeap.getIndex, SymbolFlag.DUMMY)
    }

    return args(2)
  }

}