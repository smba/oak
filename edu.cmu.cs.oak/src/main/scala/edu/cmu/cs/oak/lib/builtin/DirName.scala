package edu.cmu.cs.oak.lib.builtin

import com.caucho.quercus.Location

import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.exceptions.VariableNotFoundException
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.lib.InterpreterPluginProvider
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.StringValue
import java.nio.file.Paths
import java.nio.file.Files

class DirName extends InterpreterPlugin {

  override def getName(): String = "dirname"

  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]
    val path = args.head.toString()
    
    if (Files.isDirectory(Paths.get(path))) {
      return StringValue(Paths.get(path).toAbsolutePath().getParent.toString(), "", 0)
    } else {
      return StringValue(Paths.get(path).toAbsolutePath().getParent.toString(), "", 0)
    }
  }

}