package edu.cmu.cs.oak.lib

import edu.cmu.cs.oak.core.InterpreterPluginProvider
import com.caucho.quercus.expr.Expr
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.env.Environment

trait InterpreterPlugin {
  
  def getName(): String
  def visit(provider: InterpreterPluginProvider, args: List[Expr], env: Environment): OakValue
  
}