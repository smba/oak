package edu.cmu.cs.oak.core

import java.lang.reflect.Field

import scala.collection.mutable.ListBuffer

import com.caucho.quercus.expr.Expr
import com.caucho.quercus.program.Arg
import com.caucho.quercus.program.Function
import com.caucho.quercus.statement.Statement

import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.value.FunctionDef
import edu.cmu.cs.oak.value.OakValue
import java.nio.file.Path
import java.nio.file.Paths

trait Interpreter {
  
  /** Path to the currently executed script */
  var path: Path = null
  
  /** Line of the currently executed script */
  var currentLineNr: Int = 0
  
  /** Root path; where to find the program entry point. */
  var rootPath: Path = null
  
  /** Constants */
  var constants = Map[String, OakValue]()

  /**
   * Evaluates an expression in the context of a given environment.
   * 
   * @param e Expression to evaluate
   * @param env Environment/Context to evaluate the expression in
   * 
   * @return Tuple containing (a) the resulting OakValue, (b) the resulting environment with regard to possible side effects
   */
  def evaluate(e: Expr, env: Environment): (OakValue, Environment)
  
  /**
   * Executes a statement in the context of a given environment.
   * 
   * @param s Statement to execute
   * @param env Environment/Context to execute the statement in
   * 
   * @return Tuple containing (a) a control code, (b) the resulting environment
   */
  def execute(s: Statement, env: Environment): (String, Environment)

}

object Interpreter {
  
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
           
      val allFields = getAllDeclaredFields(Map[String, Field](), obj.getClass)
      allFields.get(name).get
      
    } catch {
      case e: NoSuchFieldException => throw new RuntimeException(e)
    }
    
    if (! field.isAccessible()) {
      field.setAccessible(true)
    }
    
    val value = try {
      field.get(obj)
    } catch {
      case e: IllegalAccessException => throw new RuntimeException(e)
    }
    value
  }

  @deprecated def accessArrayFields[T](obj: scala.Array[T]): List[T] = {
    val size = obj.length
    var out = List[T]()
    for (i <- 0 to size - 1) {
      out = out ++ List(java.lang.reflect.Array.get(obj, i).asInstanceOf[T])
    }
    out
  }
  
  /**
   * Returns all declared fields of a given object including its supertypes.
   * 
   * @param fields Map: String -> Field
   * @param type Class type 
   */
  @deprecated private def getAllDeclaredFields(fields: Map[String, Field], typ: Class[_]): Map[String, Field] = {
    var fs = fields
    fs ++= typ.getDeclaredFields.toList.map { x => (x.getName -> x) }
    if (typ.getSuperclass() != null) {
        fs ++= getAllDeclaredFields(fields, typ.getSuperclass())
    }
    return fs
  }
  
  /**
   * Defines a function. The defined function will be accessible during the 
   * program execution.
   * 
   * @param fu Function instance retrieved from the QuercusProgram to execute
   * 
   * @return FunctionDef instance to be stored by the Intepreter
   */
  def defineFunction(fu: Function): FunctionDef = {

    val f = fu.asInstanceOf[Function]
    
    // TODO Refactor variable Interpreter.access by reflection!
    val hasReturn = Interpreter.accessField(f, "_hasReturn").asInstanceOf[Boolean]

    val returnsRef = Interpreter.accessField(f, "_isReturnsReference").asInstanceOf[Boolean]
    val args = ListBuffer[String]()
    accessField(f, "_args").asInstanceOf[Array[Arg]].foreach {
      a => args.append((if (a.isReference()) "&" else "") + a.getName.toString())
    }
    val statement = Interpreter.accessField(f, "_statement").asInstanceOf[Statement]
    
    // Add function to the global environment
    return new FunctionDef(f.getName, args.toArray, statement, hasReturn, returnsRef)
  }
  
  
}