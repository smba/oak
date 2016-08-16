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


class ArrayFlip extends InterpreterPlugin {

  override def getName(): String = "array_flip"

  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has two arguments */
    assert(args.size > 0) 
    
    args.head match {
      case av: ArrayValue => {
        
        val flipped = new ArrayValue()
        
        av.array.foreach {
          case (k, v) => flipped.set(v, k, env)
        }
        
        flipped
      }
      case _ => NullValue
    }
    
  }
}