package edu.cmu.cs.oak.lib.builtin

import java.nio.file.Path
import java.nio.file.Paths

import scala.annotation.elidable.ASSERTION

import com.caucho.quercus.Location

import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.env.Call
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.lib.InterpreterPluginProvider
import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.SymbolValue

class CallUserFunc extends InterpreterPlugin {

  override def getName(): String = "call_user_func"

  override def visit(provider: InterpreterPluginProvider, args: List[OakValue], loc: Location, env: Environment): OakValue = {

    val interpreter = provider.asInstanceOf[OakInterpreter]

    /* Assert that the function has two arguments */
    assert(args.size > 0)

    val callback = args.head
    val parameters = args.tail

    val rv = callback match {

      // Case 1: Callback function
      case sv: StringValue => {
        val function = env.getFunction(sv.value)
        val argsE = parameters

        val args = parameters
        val functionCall = Call(sv.value, (Paths.get(sv.file), sv.lineNr), args)
        val functionEnv = Environment.createFunctionEnvironment(env, functionCall)

        assert(args.size > 0)

        interpreter.prepareFunctionOrMethod(function, env, functionEnv, args)
        interpreter.execute(function.statement, functionEnv)
        env.weaveDelta(functionEnv.getDelta())

        try {
          functionEnv.lookup("$return")
        } catch {
          case e: Exception => NullValue
        }

      }

      // Case 2: Callback method 
      case av: ArrayValue => {
        //        val obj = av.get(IntValue(0), env).asInstanceOf[ObjectValue] // ObjectValue
        //        val methodName = av.get(IntValue(1), env).asInstanceOf[StringValue] // StringValue
        //
        //        val method = obj.getClassDef().getMethods(methodName.value)
        //        val args = param_arr
        //        args match {
        //          case args: ArrayValue => {
        //            val arguments = args.array.map { case (k, v) => env.extract(v) }.toList
        //
        //            val methodCall = Call(obj.objectClass.name + "." + methodName, (Paths.get(methodName.file), methodName.lineNr), arguments)
        //            val methodEnv = Environment.createMethodEnvironment(env, obj, methodCall)
        //            interpreter.prepareFunctionOrMethod(method, env, methodEnv, arguments)
        //            interpreter.execute(method.statement, methodEnv)
        //            env.weaveDelta(methodEnv.getDelta())
        //
        //            try {
        //              methodEnv.lookup("$return")
        //            } catch {
        //              case e: Exception => NullValue("")
        //            }
        //          }
        //          case v: OakValue => v
        //        }
      }
      
      case NullValue => NullValue
      case s: SymbolValue => s
      case _ => null
    }

    null
  }

}