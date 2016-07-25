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


class ArraySlice extends InterpreterPlugin {

  override def getName(): String = "array_slice"

  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has two arguments */
    assert(args.size > 1) 
    
    val array = args.head
    array match {
      case av: ArrayValue => {
        try {
          val offset = args(1).asInstanceOf[IntValue].value
          val length = if (args.size > 2) args(2).asInstanceOf[IntValue].value else av.array.size
          
          val start = if (offset >= 0) offset else av.array.size + offset
    
          val end = if (length >= 0) math.min(start + length, av.array.size) else math.max(av.array.size + length, 0)
          
          
          val arraySliced = new ArrayValue()
          av.array.slice(start.toInt, end.toInt).foreach {
            
            case (k, ref) =>{
              arraySliced.setRef(k, ref)
            }
          }
          
          
          return arraySliced
        } catch {
          case e: Exception =>  SymbolValue("array_slice("+e+")", OakHeap.getIndex, SymbolFlag.DUMMY)
        }
      }
      case _ => SymbolValue("array_slice()b", OakHeap.getIndex, SymbolFlag.DUMMY)
    }
    
  }

}