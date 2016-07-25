package edu.cmu.cs.oak.lib.builtin

import java.nio.file.Path
import java.nio.file.Paths

import scala.annotation.elidable
import scala.annotation.elidable.ASSERTION

import com.caucho.quercus.expr.Expr

import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.env.Call
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.lib.InterpreterPluginProvider
import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.ObjectValue
import edu.cmu.cs.oak.value.SymbolValue
import com.caucho.quercus.Location
import edu.cmu.cs.oak.value.BooleanValue


class VersionCompare extends InterpreterPlugin {

  override def getName(): String = "version_compare"

  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    return BooleanValue(true)
  }

}