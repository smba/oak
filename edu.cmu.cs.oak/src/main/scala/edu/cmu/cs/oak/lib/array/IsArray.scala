package edu.cmu.cs.oak.lib.array

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.env.heap.OakHeap
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.core.InterpreterPluginProvider
import edu.cmu.cs.oak.env.Environment
import java.nio.file.Path
import com.caucho.quercus.expr.Expr
import edu.cmu.cs.oak.core.Interpreter
import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.value.BooleanValue
import edu.cmu.cs.oak.lib.InterpreterPlugin

class IsArray extends InterpreterPlugin {
  override def getName(): String = "is_array"
  
  override def visit(provider: InterpreterPluginProvider, args: List[Expr],loc: (Path, Int), env: Environment): OakValue = {
    
    
    
    /* Assert that the function has only 
     * been called with exactly one argument. */
    assert(args.size == 1)
    
    val v = provider.asInstanceOf[Interpreter].evaluate(args(0), env)._1
    return BooleanValue(v match {
      case a: ArrayValue => true
      case _ => false
    } )
    
  }
}