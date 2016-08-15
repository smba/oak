package edu.cmu.cs.oak.lib.builtin

import com.caucho.quercus.Location

import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.core.SymbolFlag
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.env.OakHeap
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.lib.InterpreterPluginProvider
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.SymbolValue

class Strlen extends InterpreterPlugin {

  override def getName(): String = "strlen"

  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has been o*/
    assert(args.size == 1)

    args.head match {
      case sv: StringValue => {
        return IntValue(sv.value.size)
      }
      case _=> {
        SymbolValue(s"strlen(${args.head})", OakHeap.getIndex, SymbolFlag.EXPR_UNEVALUATED)
      }
    }
  }

}