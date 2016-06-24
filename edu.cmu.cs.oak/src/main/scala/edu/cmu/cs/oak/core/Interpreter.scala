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
import scala.collection.mutable.Stack
import edu.cmu.cs.oak.value.ObjectValue
import org.slf4j.LoggerFactory

abstract class Interpreter {

  /**
   * Evaluates an expression in the context of a given environment.
   *
   * @param e Expression to evaluate
   * @param env Environment/Context to evaluate the expression in
   *
   * @return  the resulting OakValue, 
   */
  def evaluate(expr: Expr, env: Environment): OakValue

  /**
   * Executes a statement in the context of a given environment.
   *
   * @param s Statement to execute
   * @param env Environment/Context to execute the statement in
   *
   * @return A ControlCode indicating success or failure of the statement execution
   */
  def execute(s: Statement, env: Environment): ControlCode.Value
  
  // Logger for the interpreter instance
  val logger = LoggerFactory.getLogger(classOf[Interpreter])

  /** 
   *  Path to the *currently* executed source file. This path is cached and changed
   *  while including references source files.  
   *  */
  var path: Path = null

  /** Entry point of the program, i.e., the 
   *  project's root folder. 
   *  */
  var rootPath: Path = null

  /** Map of constants that are defined during the execution 
   *  of a PHP script. 
   *  */
  var constants = Map[String, OakValue]()

  /**
   * (Mutable) include stack (similar to a call stack) to keep track 
   * of recent includes in order to avoid recursion and manage
   * "require_once" statements.
   */
  val includes = Stack[Path]()

  /**
   * Assigns passed arguments to the corresponding function context (environment).
   * 
   * @param function FunctionDef instance representing the function or method to execute
   * @param env Environment The current environment of the outer program (caller's) context
   * @param functionEnv Environment The function/method (callee's) context to prepare 
   * @param args List of Exprs that are passed for the function/method call
   */
  def prepareFunctionOrMethod(function: FunctionDef, env: Environment, functionEnv: Environment, args: List[Expr]) {
    (function.getArgs.slice(0, args.length) zip args).foreach {
      t =>
        {
          val functionVal = if (t._1.startsWith("&")) {
            try {
              env.getVariables.get(t._2.toString).get
            } catch {
              case nsee: NoSuchElementException => {
                throw new RuntimeException("Only variables can be passed by reference.")
              }
            }
          } else {
            evaluate(t._2, env)
          }
          functionEnv.update("$" + t._1.replace("&", ""), functionVal)
        }
    }
    if (function.getArgs.length > args.length) {
      function.getArgs.slice(args.length, function.getArgs.length).foreach {
        a =>
          {
            val default = try {
              function.defaults.get(a).get
            } catch {
              case nsee: NoSuchElementException => {
                throw new NoSuchElementException("Default argument not found")
              }
            }
            val defaultValue = evaluate(default, env)
            functionEnv.update("$" + a.replace("&", ""), defaultValue)
          }
      }
    }
  }
  
  /**
   * Defines a constant used during the program execution.
   * 
   * @param name Name of the constant
   * @param value Value of the Constant
   */
  def defineConstant(name: String, value: OakValue) {
    constants += (name -> value)
  }
  
}