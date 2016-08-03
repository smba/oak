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
import edu.cmu.cs.oak.value.Reference


class ArrayValues extends InterpreterPlugin {

  override def getName(): String = "array_values"

  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has two arguments */
    assert(args.size > 0) 
    
    
    val av_v = new ArrayValue()
    var i = 0
    args.head match {
      case av: ArrayValue => {
        av.array.foreach {
          case (k, v) => {
            av_v.setRef(IntValue(i), v)
            i += 1
          }
        }
      }
      case _ => SymbolValue("array_values(" + args.head.toString + ")", OakHeap.getIndex, SymbolFlag.AMBIGUOUS_VALUE)
    }

    av_v
    
  }
}