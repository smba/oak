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
import com.caucho.quercus.Location

class Substr extends InterpreterPlugin {

  override def getName(): String = "substr"

  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has been o*/
    assert(args.size < 4 && args.size > 1)

    val s = args.head
    s match {
      case string: StringValue => {
        try {
          var start = args(1).asInstanceOf[IntValue].value.toInt
          val end = if (args.size == 3) {
            args(2).asInstanceOf[IntValue].value.toInt
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
      case _ => {
        s//SymbolValue("substr()", OakHeap.getIndex, SymbolFlag.FUNCTION_CALL)
      }
    }

  }

}