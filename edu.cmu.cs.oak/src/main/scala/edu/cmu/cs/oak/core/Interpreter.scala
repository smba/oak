package edu.cmu.cs.oak.core

import com.caucho.quercus.env.Value
import com.caucho.quercus.expr._
import com.caucho.quercus.statement._
import edu.cmu.cs.oak.env._
import org.slf4j.LoggerFactory

class Interpreter {

}

object Interpreter {

  // Logging for the interpreter
  val logger = LoggerFactory.getLogger(classOf[Interpreter])

  /**
   * Execution method. Executes a given statement and returns a
   * new environment, i.e., transforms a program state.
   *
   * @param stmt Statement to be executed
   * @param env Context to execute the statement with
   */
  def execute(stmt: Statement, env: Environment): Environment = stmt match {

    /*
     * Statement of the form
     * <Statement>; <Statement>; ...
     */
    case s: BlockStatement => {
      var env_ = env
      s.getStatements.foreach {
        stmt => env_ = execute(stmt, env_)
      }
      return env_
    }

    /*
     * Statement of the form
     * echo <Expr>;
     */
    case s: EchoStatement => {
      val expr = Interpreter.accessField(s, "_expr").asInstanceOf[Expr]
      return env.addOutput(evaluate(expr, env))
    }

    /*
     * Statement of the form
     * <Var> = <Expr>;
     */
    case s: ExprStatement => {

      // TODO Refactor variable access by reflection!
      val e = Interpreter.accessField(s, "_expr").asInstanceOf[Expr]
      e match {
        case b: BinaryAssignExpr => {

          // TODO Refactor variable access by reflection!
          val name = Interpreter.accessField(e, "_var").asInstanceOf[AbstractVarExpr]
          val expr = Interpreter.accessField(e, "_value").asInstanceOf[Expr]

          return name match {
            case n: VarExpr => {
              env.update(name.toString(), evaluate(expr, env))
            }
            case _ => throw new RuntimeException()
          }
        }
        case _ => throw new RuntimeException("unimplemented")
      }
    }


    /*
     * Statements of the form
     * if (<Expr>) {
     *    <Statement>
     * } else {
     *    <Statement>
     * }
      */
    case s: IfStatement => {

      /* Retrieve the condition and both statements from the IfStatement AST node.
       * TODO Refactor variable access by reflection! */
      val test = Interpreter.accessField(s, "_test").asInstanceOf[Expr]
      val trueBlock = Interpreter.accessField(s, "_trueBlock").asInstanceOf[Statement]
      val falseBlock = Interpreter.accessField(s, "_falseBlock").asInstanceOf[Statement]

      /*
      Concrete if possible, symbolic otherwise
       */
      return evaluate(test, env) match {

        /* Execute a single branch */
        case b: BooleanValue => {
          if (b.value) {
            return execute(trueBlock, env)
          } else {
            return execute(falseBlock, env)
          }
        }

        /* Execute both branches (-> symbolically) and merge environments */
        case b: SymbolicValue => {

          // TODO use real constraints
          val envs = env.fork(test.toString)
          return execute(trueBlock, envs._1) join (execute(falseBlock, envs._2))
        }

        case _ => throw new RuntimeException()
      }
    }


    /*
     * Statements of the form
     * while (<Expr>) {
     *    <Statement>
     * }
     */
    case s: WhileStatement => {

      /* Retrieve the condition and both statements from the WhileStatement AST node.
       * TODO Refactor variable access by reflection! */
      val test = Interpreter.accessField(s, "_test").asInstanceOf[Expr]
      val block = Interpreter.accessField(s, "_block").asInstanceOf[Statement]

      // TODO use real constraints
      return execute(block, env.withConstraint(test.toString)) join (env)
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

      // TODO Refactor variable access by reflection!
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
      } /*
       * Numeric Value
       */ else if (e.isNumber) {
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
        throw new RuntimeException("Array values not implemented yet!")
      } else if (e.isAssign()) {
        throw new RuntimeException("AssignExpr not implemented yet!")
      } else {
        throw new RuntimeException()
      }
    }

    /*
     * Literal expressions, such as string, number or boolean literals.
     */
    case e: LiteralUnicodeExpr => {
      return new StringValue(e.toString.slice(1, e.toString.length - 1))
    }

    /*
     * Variable expression of the form $<ID>
     */
    case e: VarExpr => {
      return env.lookup(e.toString)
    }

    /*
     * Negated (boolean) expression of the form !<Expr>
     */
    case e: UnaryNotExpr => {
      return evaluate(e.getExpr, env) match {

        // default case: e evaluates to a BooleanValue
        case e: BooleanValue => {
          e.not
        }

        // exceptional case: Found symbolic value -> return symbolic value
        case v2: SymbolicValue => SymbolValue(e)

        case _ => throw new RuntimeException()
      }
    }

    /*
     * Binary expressions of the form v1 <operator> v2
     */
    case ae: AbstractBinaryExpr => {

      // Retrieve the operands e1, e2 of the binary expression
      val e1 = ae.getLeft
      val e2 = ae.getRight

      return try {

        // evaluate e1 and match by type
        evaluate(e1, env) match {

          // expecting a boolean expression, where e1 AND e2 are BooleanValues
          case v1: BooleanValue => {
            evaluate(e2, env) match {

              // default case: e1 AND e2 are BooleanValues
              case v2: BooleanValue => {
                ae match {
                  case e: BinaryAndExpr => {
                    v1 && v2
                  }
                  case e: BinaryOrExpr => {
                    v1 || v2
                  }
                }
              }

              // exceptional cases: return symbolic value and track unresolved expression
              case v2: SymbolicValue => SymbolValue(ae)
              case v2: NumericValue => SymbolValue(ae)
              case v2: StringValue => SymbolValue(ae)

              case _ => throw new RuntimeException()
            }
          }

          // expecting a arithmetic expression, where e1 AND e2 are NumericValues
          case v1: NumericValue => {
            evaluate(e2, env) match {

              // default case: e1 AND e2 are NumericValues
              case v2: NumericValue => {
                ae match {
                  case e: BinaryAddExpr => {
                    v1 + v2
                  }
                  case e: BinarySubExpr => {
                    v1 - v2
                  }
                  case e: BinaryMulExpr => {
                    v1 * v2
                  }
                  case e: BinaryDivExpr => {
                    v1 / v2
                  }
                  case e: BinaryModExpr => {
                    v1 % v2
                  }
                  case e: BinaryLtExpr => {
                    v1 < v2
                  }
                  case e: BinaryGtExpr => {
                    v1 > v2
                  }
                  case e: BinaryEqExpr => {
                    BooleanValue(v1 == v2)
                  }
                }
              }

              // exceptional cases: return symbolic value and track unresolved expression
              case v2: SymbolicValue => SymbolValue(ae)
              case v2: BooleanValue => SymbolValue(ae)
              case v2: StringValue => SymbolValue(ae)

              case _ => throw new RuntimeException()
            }
          }

          // exceptional case: Any binary expression, where e1 is symbolic -> return a symbolic value
          case v1: SymbolicValue => SymbolValue(ae)
        }
      } catch {
        case e: Exception => throw new RuntimeException(e)
      }
    }

    case _ => throw new RuntimeException("evaluate() not implemented for AST class " + e.getClass + ".")
  }

  /**
    * Utility method to access private or protected fields of compiled
    * sources.
    *
    * TODO Replace!
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
}