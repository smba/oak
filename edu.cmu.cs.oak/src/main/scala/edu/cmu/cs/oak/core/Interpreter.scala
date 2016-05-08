package edu.cmu.cs.oak.core

import com.caucho.quercus.expr._
import com.caucho.quercus.statement.Statement
import edu.cmu.cs.oak.env._
import edu.cmu.cs.oak.value.OakValue

trait Interpreter {

  /**
   * Utility method to access private or protected fields of compiled
   * sources.
   *
   * TODO Integrate Quercus source code and discard use of reflection API!
   *
   * @param obj  Object to access private of protected field of
   * @param name Identifier of the field to access
   * @return Value of the specified field at runtime
   */
  @deprecated def accessField(obj: AnyRef, name: String): Object = {
    val field = try {
      obj.getClass.getDeclaredField(name)
    } catch {
      case e: NoSuchFieldException => throw new RuntimeException(e)
    }
    field.setAccessible(true)
    val value = try {
      field.get(obj)
    } catch {
      case e: IllegalAccessException => throw new RuntimeException(e)
    }
    value
  }

  def evaluate(e: Expr, env: Environment): (OakValue, Environment)

  def execute(e: Statement, env: Environment): (String, Environment)

}