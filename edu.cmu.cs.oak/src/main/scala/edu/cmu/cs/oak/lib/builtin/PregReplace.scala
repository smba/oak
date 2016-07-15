package edu.cmu.cs.oak.lib.builtin

import scala.annotation.elidable
import scala.annotation.elidable.ASSERTION

import com.caucho.quercus.Location

import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.lib.InterpreterPluginProvider
import edu.cmu.cs.oak.value.OakValue

class PregReplace extends InterpreterPlugin {

  override def getName(): String = "preg_replace"

  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has been o*/
    assert(args.size > 2)

    // TODO remove stub with implementation
    return args(2)
  }

}