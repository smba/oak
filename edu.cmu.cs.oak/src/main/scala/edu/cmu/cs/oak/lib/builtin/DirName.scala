package edu.cmu.cs.oak.lib.builtin

import java.nio.file.Path

import com.caucho.quercus.expr.Expr

import edu.cmu.cs.oak.core.InterpreterPluginProvider
import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.env.OakHeap
import edu.cmu.cs.oak.exceptions.VariableNotFoundException
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.SymbolValue

class DirName extends InterpreterPlugin {

  override def getName(): String = "dirname"

  override def visit(provider: InterpreterPluginProvider, args: List[Expr], loc: Path, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has been o */
    assert(args.size == 1)
    //FIXME argument"
    return if (args.head.toString startsWith("$")) {
      try {
        return env.lookup(args.head.toString)
      } catch {
        case vnfe: VariableNotFoundException => SymbolValue("", OakHeap.getIndex, null)
      }
    } else if (args.head.toString endsWith(")")) {
      try {
        val arg = interpreter.evaluate(args.head, env).toString
        val dirname = arg.substring(0, arg.lastIndexOf("/"))
        return StringValue(dirname, args.head._location.getFileName(), args.head._location.getLineNumber())
      } catch {
        case _ : Throwable => SymbolValue("", OakHeap.getIndex, null)
      }
    } else {
      try {
        val arg = args.head.toString()
        val dirname = arg.substring(0, arg.lastIndexOf("/"))
        return StringValue(dirname, args.head._location.getFileName(), args.head._location.getLineNumber())
      } catch {
        case _ : Throwable => SymbolValue("", OakHeap.getIndex, null)
      }
    }
  }

}