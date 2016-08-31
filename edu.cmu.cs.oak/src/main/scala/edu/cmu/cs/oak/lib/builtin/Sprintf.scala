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

object Companion {

  def sprintf(text: String, pars: List[Any]): String = {
    var tex = text
    val matches = "%.".r.findAllMatchIn(text).toList
    if (matches.size != pars.size) {
      return text
    }
    if (tex != null && matches != null && pars != null) {
      (matches zip pars).foreach {
        case (matche, par) => {
          if (matche != null && par != null) {
            tex = tex.replaceFirst(matche.toString, par.toString.replaceAll("\\$", ""))
          }
        }
      }
      tex
    } else {
      text
    }

  }
}

// FIXME TODO Formatting
class Sprintf extends InterpreterPlugin {

  override def getName(): String = "sprintf"

  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has been o*/
    //assert(args.size == 1)

    val value = args.head

    value match {
      case sv: StringValue => {
        env.recordTouchedLiteral(sv)
        return StringValue(Companion.sprintf(sv.value, args.tail), sv.file, sv.lineNr)
      }
      case _ => {
        env.addOutput(DNode.createDNode(value, loc))
      }
    }

    return IntValue(1)
  }

}

// FIXME TODO Formatting
class Vsprintf extends InterpreterPlugin {

  override def getName(): String = "vsprintf"

  def sprintf(text: String, pars: List[Any]): String = {
    var tex = text
    val matches = "%.".r.findAllMatchIn(text).toList
    if (matches.size != pars.size) {
      return text
    }
    (matches zip pars).foreach {
      case (matche, par) => {
        if (tex != null && par != null && matche != null) {
          tex = tex.replaceFirst(matche.toString, par.toString.replaceAll("\\$", ""))
        } else {
          ""
        }
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
        env.recordTouchedLiteral(sv)
        StringValue(sprintf(sv.value, args.tail), sv.file, sv.lineNr)
      }
      case _ => {
        env.addOutput(DNode.createDNode(value, loc))
      }
    }

    return IntValue(1)
  }

}

class Printf extends InterpreterPlugin {

  override def getName(): String = "printf"

  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has been o*/
    //assert(args.size == 1)

    val value = args.head

    value match {
      case sv: StringValue => {
        env.addOutput(DNode.createDNode(StringValue(Companion.sprintf(sv.value, args.tail), sv.file, sv.lineNr), loc))
      }
      case _ => {
        env.addOutput(DNode.createDNode(value, loc))
      }
    }

    return IntValue(1)
  }

}