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

/**
 * 
 */
class Print extends InterpreterPlugin {

  override def getName(): String = "print"

  override def visit(provider: InterpreterPluginProvider, args: List[Expr], loc: (Path, Int), env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[Interpreter]

    /* Assert that the function has been o*/
    assert(args.size == 1)

    args.foreach {
      a =>
        {
          val value = interpreter.evaluate(a, env)._1
          val toAdd = value match {
            case s: StringValue => {
              s.setLocation((interpreter.path.toString diff interpreter.rootPath.toString, loc._2))
              s
            }
            case _ => value
          }
          env.addOutput(toAdd)
        }
    }

    return IntValue(1)
  }

}