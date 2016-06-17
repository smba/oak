package edu.cmu.cs.oak.lib.builtin

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.core.InterpreterPluginProvider
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.env.Environment
import java.nio.file.Path
import com.caucho.quercus.expr.Expr
import edu.cmu.cs.oak.core.Interpreter

class DirName extends InterpreterPlugin {

  override def getName(): String = "dirname"

  override def visit(provider: InterpreterPluginProvider, args: List[Expr], loc: (Path, Int), env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[Interpreter]

    /* Assert that the function has been o */
    assert(args.size == 1)

    val zu = (interpreter.path.toString diff interpreter.rootPath.toString)
    val dirname = zu.substring(0, zu.lastIndexOf('/'))

    //FIXME argument"
    return StringValue(dirname, args(0)._location)
  }

}