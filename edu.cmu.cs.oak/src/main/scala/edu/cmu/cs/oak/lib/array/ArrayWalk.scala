package edu.cmu.cs.oak.lib.array

import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.lib.InterpreterPluginProvider
import edu.cmu.cs.oak.env.Environment
import java.nio.file.Path
import com.caucho.quercus.expr.Expr
import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.core.SymbolFlag
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.env.OakHeap
import edu.cmu.cs.oak.value.IntValue
import com.caucho.quercus.Location
import edu.cmu.cs.oak.value.NullValue


class ArrayWalk extends InterpreterPlugin {

  override def getName(): String = "array_walk"

  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has two arguments */
    assert(args.size > 1) 
    
    args.head match {
      case av: ArrayValue => {
        av.array.foreach {
          case (key, ref) => {
            interpreter.call(args(1).toString, env.extract(ref) :: key :: args.slice(2, args.size), loc, env)
          }
        }
      }
      case _ => {
        null
      }
    }
    NullValue
  }
}