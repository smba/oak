package edu.cmu.cs.oak.lib.builtin

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.env.heap.OakHeap
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.core.InterpreterPluginProvider
import edu.cmu.cs.oak.env.Environment
import com.caucho.quercus.expr.Expr
import edu.cmu.cs.oak.core.Interpreter
import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.value.StringValue
import java.net.URL
import java.nio.file.Path
import edu.cmu.cs.oak.nodes.LiteralNode
import edu.cmu.cs.oak.nodes.DNode

/**
 * 
 */
class Print extends InterpreterPlugin {

  override def getName(): String = "print"

  override def visit(provider: InterpreterPluginProvider, args: List[Expr], loc: Path, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[Interpreter]

    /* Assert that the function has been o*/
    assert(args.size == 1)

    args.reverse.foreach {
      a =>
        {
          val value = interpreter.evaluate(a, env)._1
          env.addOutput( DNode.createDNode(value, expr = a) )
        }
    }

    return IntValue(1)
  }

}