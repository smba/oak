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


class ArrayPush extends InterpreterPlugin {

  override def getName(): String = "array_push"

  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has two arguments */
    assert(args.size > 1) 
    
    args.head match {
      case ref: Reference => {
        val array_to_push_to = env.extract(ref).asInstanceOf[ArrayValue]
        val s = array_to_push_to.array.size
        args.tail.foreach {
          arg => array_to_push_to.set(IntValue(s), arg, env)
        }
        env.insert(ref, array_to_push_to)
      }
    }

    IntValue(args.tail.size)
    
  }
}