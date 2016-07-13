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


class ArraySlice extends InterpreterPlugin {

  override def getName(): String = "array_slice"

  override def visit(provider: InterpreterPluginProvider, args: List[Expr], loc: Path, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has two arguments */
    assert(args.size > 1) 
    
    val array = interpreter.evaluate(args.head, env)
    array match {
      case av: ArrayValue => {
        try {
          val offset = interpreter.evaluate(args(1), env).asInstanceOf[IntValue].value
          val length = if (args.size > 2) interpreter.evaluate(args(2), env).asInstanceOf[IntValue].value else av.array.size
          
          val start = if (offset < 0) av.array.size - offset else offset
          val end = if (length > 0) if (start + length > av.array.size) av.array.size else length else av.array.size + length
          val arraySliced = new ArrayValue()
          av.array.slice(start.toInt, end.toInt).foreach {
            case (k, ref) => arraySliced.setRef(k, ref)
          }
          return arraySliced
        } catch {
          case _ =>  SymbolValue("array_slice()", OakHeap.getIndex, SymbolFlag.DUMMY)
        }
      }
      case _ => SymbolValue("array_slice()", OakHeap.getIndex, SymbolFlag.DUMMY)
    }
    
  }

}