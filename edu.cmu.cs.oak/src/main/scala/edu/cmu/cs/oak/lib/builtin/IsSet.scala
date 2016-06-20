package edu.cmu.cs.oak.lib.builtin

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.core.InterpreterPluginProvider
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.env.Environment
import java.nio.file.Path
import com.caucho.quercus.expr.Expr
import edu.cmu.cs.oak.core.Interpreter
import edu.cmu.cs.oak.value.BooleanValue

class IsSet extends InterpreterPlugin {

  override def getName(): String = "isset"

  override def visit(provider: InterpreterPluginProvider, args: List[Expr], loc: Path, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[Interpreter]

    /* Assert that the function has been o */
    assert(args.size == 1)

    val isSet = try {
      env.lookup(args(0).toString)
      true
    } catch {
      case _: Throwable => false
    }
    return BooleanValue(isSet)
  }

}
