package edu.cmu.cs.oak.lib.builtin

import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.lib.InterpreterPluginProvider
import edu.cmu.cs.oak.env.Environment
import java.nio.file.Path
import com.caucho.quercus.expr.Expr
import edu.cmu.cs.oak.value.BooleanValue
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.core.SymbolFlag
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.env.OakHeap
import edu.cmu.cs.oak.value.SymbolicValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.ObjectValue
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.value.ArrayValue
import com.caucho.quercus.Location
import java.io.File
import edu.cmu.cs.oak.env.Call
import java.nio.file.Paths

class IsNull extends InterpreterPlugin {
  override def getName(): String = "is_null"
  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {
    val interpreter = provider.asInstanceOf[OakInterpreter]
    assert(args.size == 1)
    return args.head match {
      case n: NullValue => BooleanValue(true)
      case s: SymbolicValue => SymbolValue("is_null(" + args.head + ")", OakHeap.getIndex, SymbolFlag.DUMMY)
      case _ => BooleanValue(false)
    }
  }
}

class IsObject extends InterpreterPlugin {
  override def getName(): String = "is_object"
  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {
    val interpreter = provider.asInstanceOf[OakInterpreter]
    assert(args.size == 1)
    return args.head match {
      case n: ObjectValue => BooleanValue(true)
      case s: SymbolicValue => SymbolValue("is_object(" + args.head + ")", OakHeap.getIndex, SymbolFlag.DUMMY)
      case _ => BooleanValue(false)
    }
  }
}

class IsString extends InterpreterPlugin {
  override def getName(): String = "is_string"
  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {
    val interpreter = provider.asInstanceOf[OakInterpreter]
    assert(args.size == 1)
    return args.head match {
      case n: StringValue => BooleanValue(true)
      case s: SymbolicValue => SymbolValue("is_string(" + args.head + ")", OakHeap.getIndex, SymbolFlag.DUMMY)
      case _ => BooleanValue(false)
    }
  }
}

// TODO replace stub for trim() with implementation
class Trim extends InterpreterPlugin {
  override def getName(): String = "trim"
  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {
    val interpreter = provider.asInstanceOf[OakInterpreter]
    assert(args.size < 3 && args.size > 0)
    return args.head match {
      case s: StringValue => s
      case _ => SymbolValue("trim(" + args.head + ")", OakHeap.getIndex, SymbolFlag.DUMMY)
    }
  }
}

// TODO replace stub for rtrim() with implementation
class Rtrim extends InterpreterPlugin {
  override def getName(): String = "rtrim"
  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {
    val interpreter = provider.asInstanceOf[OakInterpreter]
    assert(args.size < 3 && args.size > 0)
    return args.head match {
      case s: StringValue => s
      case _ => SymbolValue("rtrim(" + args.head + ")", OakHeap.getIndex, SymbolFlag.DUMMY)
    }
  }
}

// TODO replace stub for ltrim() with implementation
class Ltrim extends InterpreterPlugin {
  override def getName(): String = "ltrim"
  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {
    val interpreter = provider.asInstanceOf[OakInterpreter]
    assert(args.size < 3 && args.size > 0)
    return args.head match {
      case s: StringValue => s
      case _ => SymbolValue("ltrim(" + args.head + ")", OakHeap.getIndex, SymbolFlag.DUMMY)
    }
  }
}

// TODO replace stub for preg_replace_callback() with implementation
class PregReplaceCallback extends InterpreterPlugin {
  override def getName(): String = "preg_replace_callback"
  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {
    val interpreter = provider.asInstanceOf[OakInterpreter]
    assert(args.size < 6 && args.size > 2)
    return args(2)
  }
}

class Chr extends InterpreterPlugin {
  override def getName(): String = "chr"
  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {
    val interpreter = provider.asInstanceOf[OakInterpreter]
    assert(args.size == 1)
    return args.head match {
      case s: IntValue => {
        StringValue(s.value.toChar.toString, "", 0)
      }
      case _ => SymbolValue("chr(" + args.head + ")", OakHeap.getIndex, SymbolFlag.DUMMY)
    }
  }
}

class Ord extends InterpreterPlugin {
  override def getName(): String = "ord"
  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {
    val interpreter = provider.asInstanceOf[OakInterpreter]
    assert(args.size == 1)
    return args.head match {
      case s: StringValue => {
        IntValue(s.value.head.toInt)
      }
      case _ => SymbolValue("ord(" + args.head + ")", OakHeap.getIndex, SymbolFlag.DUMMY)
    }
  }
}

// TODO check 3rd argument
class Explode extends InterpreterPlugin {
  override def getName(): String = "explode"
  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {
    val interpreter = provider.asInstanceOf[OakInterpreter]
    assert(args.size < 4 && args.size > 1)

    val delimiter = args.head
    val subject = args(1)

    return subject match {
      case s: StringValue => {
        delimiter match {
          case d: StringValue => {
            try {
              val array = s.value.split(d.value)
              val av = new ArrayValue()
              array.zipWithIndex.foreach {
                case (st, i) => av.set(IntValue(i), StringValue(st, "", 0), env)
              }
              av
            } catch {
              case e: Exception => SymbolValue("explode(" + args.head + ")", OakHeap.getIndex, SymbolFlag.DUMMY)
            }
          }
          case _ => SymbolValue("explode(" + args.head + ")", OakHeap.getIndex, SymbolFlag.DUMMY)
        }
      }
      case n: NullValue => NullValue("explode()")
      case _ => SymbolValue("explode(" + args.head + ")", OakHeap.getIndex, SymbolFlag.DUMMY)
    }
    null
  }
}

// TODO check 3rd argument
class InArray extends InterpreterPlugin {
  override def getName(): String = "in_array"
  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {
    val interpreter = provider.asInstanceOf[OakInterpreter]
    assert(args.size < 4 && args.size > 1)
    val needle = args.head
    val haystack = args(1)

    haystack match {
      case av: ArrayValue => {
        BooleanValue(av.array.map { case (k, ref) => env.extract(ref) }.toSet.contains(needle))
      }
      case n: NullValue => NullValue("in_array()")
      case _ => SymbolValue("in_array(" + args.head + ")", OakHeap.getIndex, SymbolFlag.DUMMY)
    }
  }
}

class FunctionExists extends InterpreterPlugin {
  override def getName(): String = "function_exists"
  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {
    val interpreter = provider.asInstanceOf[OakInterpreter]
    assert(args.size < 2 && args.size > 0)

    val fname = args.head
    fname match {
      case sv: StringValue => {
        BooleanValue(env.containsFunction(sv.value))
      }
      case _ => NullValue("function_exists()")
    }
  }
}

class FuncGetArg extends InterpreterPlugin {
  override def getName(): String = "func_get_arg"
  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {
    val interpreter = provider.asInstanceOf[OakInterpreter]
    assert(args.size == 1)
    val i = args.head
    i match {
      case i: IntValue => {
        val av = new ArrayValue()
        env.getCalls().head.args.zipWithIndex.foreach {
          case (arg, i) => av.set(IntValue(i), arg, env)
        }
        av
      }
      case _ => NullValue("func_get_arg()")
    }
  }
}

class FileExists extends InterpreterPlugin {
  override def getName(): String = "file_exists"
  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {
    val interpreter = provider.asInstanceOf[OakInterpreter]
    assert(args.size == 1)

    val file_exists = (new File(args.head.toString)).exists()

    return BooleanValue(file_exists)

  }
}

//class ArrayWalk extends InterpreterPlugin {
//
//  override def getName(): String = "array_walk"
//  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {
//
//     val av_walked = new ArrayValue()
//
//     args.head match {
//       case av: ArrayValue => {
//         av.array.foreach {
//           case (k, v) => 
//         }
//       }
//       case esle: OakValue => {
//         // 
//       }
//     }
//     
//    return BooleanValue(null)
//
//  }
//}
