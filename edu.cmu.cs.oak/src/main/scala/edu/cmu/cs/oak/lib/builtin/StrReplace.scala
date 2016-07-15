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
import java.util.regex.PatternSyntaxException
import com.caucho.quercus.Location

class StrReplace extends InterpreterPlugin {

  override def getName(): String = "str_replace"

  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has been o*/
    assert(args.size < 5 && args.size > 1, args.size)

    var search = args.head
    var replace = args(1)
    var subject = args(2)

    if (search.isInstanceOf[StringValue] && replace.isInstanceOf[StringValue] && subject.isInstanceOf[StringValue]) {
      val searchs = search.asInstanceOf[StringValue].toString()
      val replaces = replace.asInstanceOf[StringValue].toString()
      val subjects = subject.asInstanceOf[StringValue]
      return try {
        StringValue(subjects.value.replaceAll(searchs, replaces), subjects.file, subjects.lineNr)
      } catch {
        case c: PatternSyntaxException => SymbolValue("str_replace()", OakHeap.getIndex, SymbolFlag.EXPR_LEFT_UNEVALUATED)
      }
    } else {
      SymbolValue("str_replace()", OakHeap.getIndex, SymbolFlag.EXPR_LEFT_UNEVALUATED)
    }
    null
  }

}