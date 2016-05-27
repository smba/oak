package edu.cmu.cs.oak.lib.array

import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.core.InterpreterPluginProvider
import com.caucho.quercus.expr.Expr
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.core.Interpreter
import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.env.heap.OakHeap
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.value.IntValue
import java.net.URL
import java.nio.file.Path

class Count extends InterpreterPlugin {
  
  override def getName(): String = "count"
  
  override def visit(provider: InterpreterPluginProvider, args: List[Expr],loc: (Path, Int), env: Environment): OakValue = {
    
    
    
    /* Assert that the function has only 
     * been called with exactly one argument. */
    assert(args.size == 1)
    
    val res = provider.asInstanceOf[Interpreter].evaluate(args(0), env)._1 
    res match {
      case a: ArrayValue => {
        return IntValue(a.getSize)
      }
      case _ =>  return SymbolValue(args(0).toString, OakHeap.getIndex())
    }
    
  }
  
}