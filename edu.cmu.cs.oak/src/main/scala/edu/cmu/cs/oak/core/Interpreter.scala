package edu.cmu.cs.oak.core

import com.caucho.quercus.expr.AbstractVarExpr
import com.caucho.quercus.expr.BinaryAssignExpr
import com.caucho.quercus.expr.Expr
import com.caucho.quercus.expr.LiteralExpr
import com.caucho.quercus.statement.BlockStatement
import com.caucho.quercus.statement.EchoStatement
import com.caucho.quercus.statement.ExprStatement
import com.caucho.quercus.statement.Statement
import edu.cmu.cs.oak.env.Environment
import com.caucho.quercus.env.Value
import edu.cmu.cs.oak.env.OakValue
import edu.cmu.cs.oak.env.StringValue
import com.caucho.quercus.expr.VarExpr
import edu.cmu.cs.oak.env.BooleanValue
import edu.cmu.cs.oak.env.IntValue
import edu.cmu.cs.oak.env.DoubleValue
import com.caucho.quercus.expr.LiteralUnicodeExpr

object Interpreter {

  /**
   * Execution method. Executes a given statement and returns a
   * new environment, i.e., transforms a program state.
   *
   * @param stmt Statement to be executed
   * @param env Context to execute the statement with
   */
  def execute(stmt: Statement, env: Environment): Environment = stmt match {

    case s: BlockStatement => {
      var env_ = env
      s.getStatements.foreach {
        stmt => env_ = execute(s, env_)
      }
      return env_
    }

    case s: EchoStatement => {
      val expr = Interpreter.accessField(s, "_expr").asInstanceOf[Expr]
      return env.addOutput(evaluate(expr, env))
    }

    /*
     * -> Assign statement
     */
    case s: ExprStatement => {
      val e = Interpreter.accessField(s, "_expr").asInstanceOf[Expr]
      e match {
        case b: BinaryAssignExpr => {
          val name = Interpreter.accessField(e, "_var").asInstanceOf[AbstractVarExpr]
          val expr = Interpreter.accessField(e, "_value").asInstanceOf[Expr]

          return name match {
            case n: VarExpr => {
              env.update(name.toString(), evaluate(expr, env))
            }
            case _ => throw new RuntimeException("unimplemented")
          }
        }
        case _ => throw new RuntimeException("unimplemented")
      }
    }
    
    case _ => throw new RuntimeException("execute() not implemented for AST class " + stmt.getClass + ".")
  }

  /**
   * Expression evaluation method. Evaluates a given expression and returns a
   * value.
   *
   * @param e Expression to be evaluated
   * @param env Context to evaluate the expression with
   */
  def evaluate(e: Expr, env: Environment): OakValue = e match {

    /* Literals: String, Double, ...*/
    case e: LiteralExpr => {
      val value = Interpreter.accessField(e, "_value").asInstanceOf[Value]
      
      /*
       * Boolean Value
       */
      if (e.isBoolean) {
        if (e.isTrue()) {
          return BooleanValue(true)
        } else if (e.isFalse()) {
          return BooleanValue(false)
        } else {
          throw new RuntimeException()
        }
      } 
      
      /*
       * Numeric Value
       */
      else if (e.isNumber) {
        return try {
          if (e.isLong) {
            return IntValue(e.toString.toInt)
          } else if (e.isDouble) {
            return DoubleValue(e.toString.toDouble)
          } else {
            throw new NumberFormatException()
          }
        } catch {
          case e: NumberFormatException => throw new RuntimeException(e)
        }
        
      } else if (e.isArray) {
        return null
      } else if (e.isAssign()) {
        return null
      } else {
        return null
      }
    }
    
    // String literal 
    case e: LiteralUnicodeExpr => {
      return new StringValue(e.toString.slice(1, e.toString.length - 1))
    }

    case _ => throw new RuntimeException("evaluate() not implemented for AST class " + e.getClass + ".")
  }

  /**
   * Utility method. Access private fields via reflection.
   *
   * TODO replace by aspects in the future
   */
  def accessField(obj: AnyRef, name: String): Object = {
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
}