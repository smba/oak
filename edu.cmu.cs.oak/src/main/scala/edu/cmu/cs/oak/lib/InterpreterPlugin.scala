package edu.cmu.cs.oak.lib

import com.caucho.quercus.expr.Expr
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.env.Environment
import java.net.URL
import java.nio.file.Path

trait InterpreterPlugin {
  
  def getName(): String
  def visit(provider: InterpreterPluginProvider, args: List[Expr], loc: Path, env: Environment): OakValue
  
}