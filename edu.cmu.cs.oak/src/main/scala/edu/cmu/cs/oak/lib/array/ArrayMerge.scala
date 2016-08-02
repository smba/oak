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


class ArrayMerge extends InterpreterPlugin {

  override def getName(): String = "array_merge"

  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has two arguments */
    assert(args.size > 1) 
    
    val merged = new ArrayValue()
    
    var i = 0
    
    args.foreach {
      case arg => arg match {
        case av: ArrayValue => {
          av.array.foreach {
            case (key, value) => {
              if (key.isInstanceOf[IntValue]) {
                merged.set(IntValue(i), env.extract(value), env)
                i += 1
              } else {
                merged.set(key, value, env)
              }
            }
          }
        }
        case _ => {}
      }
    }
    
    return merged
  }
}