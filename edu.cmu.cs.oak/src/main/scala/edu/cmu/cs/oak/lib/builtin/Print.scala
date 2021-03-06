package edu.cmu.cs.oak.lib.builtin

import java.nio.file.Path

import com.caucho.quercus.expr.Expr

import edu.cmu.cs.oak.lib.InterpreterPluginProvider
import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.value.OakValue
import com.caucho.quercus.Location

/**
 * 
 */
class Print extends InterpreterPlugin {

  override def getName(): String = "print"

  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has been o*/
    assert(args.size == 1)
    
    val value = args.head

    env.addOutput( DNode.createDNode(value, loc) )

    return IntValue(1)
  }

}