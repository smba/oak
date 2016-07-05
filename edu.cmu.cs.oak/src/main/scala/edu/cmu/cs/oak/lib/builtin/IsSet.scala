package edu.cmu.cs.oak.lib.builtin

import java.nio.file.Path

import com.caucho.quercus.expr.Expr

import edu.cmu.cs.oak.lib.InterpreterPluginProvider
import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.value.BooleanValue
import edu.cmu.cs.oak.value.OakValue

class IsSet extends InterpreterPlugin {

  override def getName(): String = "isset"

  override def visit(provider: InterpreterPluginProvider, args: List[Expr], loc: Path, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

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
