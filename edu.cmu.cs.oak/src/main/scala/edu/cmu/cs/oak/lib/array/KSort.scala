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


class KSort extends InterpreterPlugin {

  override def getName(): String = "ksort"

  override def visit(provider: InterpreterPluginProvider, args: List[Expr], loc: Path, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has two arguments */
    assert(args.size == 1) 
    
    val array = interpreter.evaluate(args.head, env)
    
    array match {
      case av: ArrayValue => {
        return av // TODO remove stub with implementation
      }
      case _ => SymbolValue("ksort()", OakHeap.getIndex, SymbolFlag.DUMMY)
    }
    
  }

}