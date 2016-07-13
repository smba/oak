package edu.cmu.cs.oak.lib.builtin

import java.nio.file.Path

import com.caucho.quercus.expr.Expr
import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.lib.InterpreterPluginProvider
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.core.SymbolFlag
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.env.OakHeap

class Substr extends InterpreterPlugin {

  override def getName(): String = "substr"

  override def visit(provider: InterpreterPluginProvider, args: List[Expr], loc: Path, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has been o*/
    assert(args.size < 4 && args.size > 1)

    val s = interpreter.evaluate(args.head, env)
    s match {
      case string: StringValue => {
        try {
          var start = interpreter.evaluate(args(1), env).asInstanceOf[IntValue].value.toInt
          val end = if (args.size == 3) {
            interpreter.evaluate(args(2), env).asInstanceOf[IntValue].value.toInt
          } else {
            string.value.length()
          }
          if (start < 0) {
            start = string.value.length() + start
          }
          return StringValue(string.value.slice(start, end), string.file, string.lineNr)
        } catch {
          case e: ClassCastException => SymbolValue("substr", OakHeap.getIndex, SymbolFlag.FUNCTION_CALL)
        }
      }
      case _ => SymbolValue("substr()", OakHeap.getIndex, SymbolFlag.FUNCTION_CALL)
    }

  }

}