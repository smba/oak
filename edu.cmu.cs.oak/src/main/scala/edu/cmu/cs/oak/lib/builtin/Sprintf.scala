package edu.cmu.cs.oak.lib.builtin

import java.nio.file.Path

import com.caucho.quercus.expr.Expr
import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.lib.InterpreterPluginProvider
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.nodes.DNode
import com.caucho.quercus.Location
import edu.cmu.cs.oak.value.StringValue


// FIXME TODO Formatting
class Sprintf extends InterpreterPlugin {

  override def getName(): String = "sprintf"

  def sprintf(text: String, pars: List[Any]): String = {
    var tex = text
    val matches = "%.".r.findAllMatchIn(text).toList
    assert(matches.size == pars.size)
    (matches zip pars).foreach {
      case (matche, par) => {
        tex = tex.replaceFirst(matche.toString, par.toString.replaceAll("\\$", ""))
      }
    }
    tex
  }
  
  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has been o*/
    //assert(args.size == 1)
    
    val value = args.head
    
    value match {
      case sv: StringValue => {
        StringValue(sprintf(sv.value, args.tail), sv.file, sv.lineNr)
      }
      case _ => {
        env.addOutput( DNode.createDNode(value, loc) )
      }
    }

    return IntValue(1)
  }

}