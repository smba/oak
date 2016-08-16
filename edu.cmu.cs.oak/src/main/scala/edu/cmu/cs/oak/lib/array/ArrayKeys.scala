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


class ArrayKeys extends InterpreterPlugin {

  override def getName(): String = "array_keys"

  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has two arguments */
    assert(args.size > 0) 
    
    args.head match {
      case av: ArrayValue => {
        
        val keys = new ArrayValue()
        
        // search 
        if (args.size == 2) {
          val x = av.array.toList.filter {
            case (k, v) => args(1) equals v
          }
        } else {
          (av.array.keys.toList.zipWithIndex).foreach {
            case (key, index) => keys.set(IntValue(index), key, env)
          }
        }
        keys
      }
      case _ => NullValue
    }
    
  }
}