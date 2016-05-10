package edu.cmu.cs.oak.core

import com.caucho.quercus.env.Value
import com.caucho.quercus.expr._
import com.caucho.quercus.program.{ Arg, QuercusProgram }
import com.caucho.quercus.statement._
import edu.cmu.cs.oak.env.{ BranchEnv, Environment, OakHeap, SimpleEnv }
import edu.cmu.cs.oak.value._
import org.slf4j.LoggerFactory

import scala.collection.immutable.Stack
import scala.collection.mutable.ListBuffer
import edu.cmu.cs.oak.lib.InterpreterPlugin
import scala.collection.mutable.HashMap
import edu.cmu.cs.oak.lib.array.Count
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.value.DoubleValue

class OakInterpreter extends Interpreter with InterpreterPluginProvider {

  val plugins = HashMap[String, InterpreterPlugin]()
  var libraryFunctions = List[String]()

  this.loadPlugin(new Count)

  /** Logger for the interpreter */
  val logger = LoggerFactory.getLogger(classOf[Interpreter])

  def execute(program: QuercusProgram): (String, Environment) = {

    OakHeap.clear()

    val env = new SimpleEnv(null, Stack[String](), "true")

    /** Write all function definitions to the heap */
    val funcIterator = program.getFunctionList.iterator
    while (funcIterator.hasNext) {
      val f = funcIterator.next()

      // TODO Refactor variable Interpreter.access by reflection!
      val hasReturn = Interpreter.accessField(f, "_hasReturn").asInstanceOf[Boolean]
      val args = ListBuffer[String]()
      Interpreter.accessField(f, "_args").asInstanceOf[Array[Arg]].foreach {
        a => args.append(a.getName.toString())
      }
      val statement = Interpreter.accessField(f, "_statement").asInstanceOf[Statement]
      // Add function to the global environment
      env.defineFunction(new FunctionDef(f.getName, args.toArray, statement, hasReturn))
    }

    val res = execute(program.getStatement, env)

    return res
  }

  override def execute(stmt: Statement, env: Environment): (String, Environment) = stmt match {

    /*
     * Statement of the form
     * <Statement>; <Statement>; ...
     */
    case s: BlockStatement => {
      var env_ = env
      s.getStatements.foreach {

        stmt => env_ = execute(stmt, env_)._2
      }
      return ("OK", env_)
    }

    /*
     * Statement of the form
     * echo <Expr>;
     */
    case s: EchoStatement => {
      val expr = Interpreter.accessField(s, "_expr").asInstanceOf[Expr]
      val v = evaluate(expr, env)

      env.addOutput(v._1)

      return ("OK", env)
    }

    /*
     * Statement of the form
     * <Var> = <Expr>;
     */
    case s: ExprStatement => {
      // TODO Refactor variable Interpreter.access by reflection!
      val e = Interpreter.accessField(s, "_expr").asInstanceOf[Expr]
      e match {
        case b: BinaryAssignExpr => {

          // TODO Refactor variable Interpreter.access by reflection!
          val name = Interpreter.accessField(e, "_var").asInstanceOf[AbstractVarExpr]
          val expr = Interpreter.accessField(e, "_value").asInstanceOf[Expr]

          System.err.println(name, expr);
          
          return name match {
            
            /* name is a variable */
            case n: VarExpr => {
              env.update(name.toString(), evaluate(expr, env)._1)
              ("OK", env)
            }
            
            /* name is something like $var[i] */
            /*
             * expr: Expr to assign to array field
             * name: name of the var to assign, e.g., $h[4]
             * 
             * name <- expr
             */
            case a: ArrayGetExpr => {
              
              val index = evaluate(Interpreter.accessField(a, "_index").asInstanceOf[Expr], env)._1
              System.err.println("index " + index.getClass);
              
              /* Utility, transfer names with $xxx[i] to $xxx */
              val pattern = "\\](\\s|\\d|[^(\\[|\\])])*\\[".r
              val arrayValueName = (pattern replaceFirstIn(name.toString.reverse, "")).reverse
              System.err.println("clearName " + arrayValueName);
              
              /* Check if there is already a variable with name 
               * name stored in the environment. If so, use this
               * value. Otherwise, 
               * 	+ create a new array value and
               * 	+ assign expr to it with key index, named name[index]
               *  + update the array variable name in the environment.
               * */
              val arrayValue = try {
                val av = env.lookup(arrayValueName)
                
                /* Assert that av is a array value. */
                assert(av.isInstanceOf[ArrayValue])
                
                /* Return array value*/
                av.asInstanceOf[ArrayValue]
              } catch {
                case ex: Exception => new ArrayValue()
              }
              
              arrayValue.set(index, evaluate(expr, env)._1)
              env.update(arrayValueName, arrayValue)
              logger.info("Updated " + arrayValueName)
              return ("OK", env)
              /*
              // TODO arrays don't need to be initialized
              val expri = try {
                System.err.println("hier " + name);
                evaluate(null, env)._1
              } catch {
                case ex: Exception => System.err.println(ex); new ArrayValue()
              }
              val index = evaluate(Interpreter.accessField(a, "_index").asInstanceOf[Expr], env)._1
               
              expr match {
                case av: ArrayValue => {
                  av.set(index, expri)
                  System.err.println(expr.getClass)
                  val pattern = "\\](\\s|\\d)*\\[".r
                  env.update(, "").reverse), av)
                  ("OK", env)
                }
                case _ => throw new RuntimeException("Not an array.")
              }
              */
            }
            case _ => throw new RuntimeException("Did not expect expression of type " + name.getClass + ".")
          }
        }

        /**
         * TODO Seiteneffekte des Ausdruckes!
         */
        case c: CallExpr => {
          evaluate(c, env)
          return ("OK", env)
        }
        case _ => throw new RuntimeException("Expression " + e.toString + " of class " + e.getClass + " is not implemented for ExprStatement")
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
       * TODO Refactor variable Interpreter.access by reflection! */
      val test = Interpreter.accessField(s, "_test").asInstanceOf[Expr]
      val trueBlock = Interpreter.accessField(s, "_trueBlock").asInstanceOf[Statement]
      val falseBlock = Interpreter.accessField(s, "_falseBlock").asInstanceOf[Statement]

      /*
      Concrete if possible, symbolic otherwise
       */
      return evaluate(test, env)._1 match {

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
          val envs = env.fork(test.toString) // two branch environments

          // create
          val env1sub = envs._1
          val env2sub = envs._2

          val res1 = execute(trueBlock, env1sub)._2
          val res2 = execute(falseBlock, env2sub)._2

          res1.getVariables().keySet.foreach {
            k => assert(!res1.lookup(k).isInstanceOf[OakVariable])
          }
          res2.getVariables().keySet.foreach {
            k => assert(!res2.lookup(k).isInstanceOf[OakVariable])
          }
          val res = res1.asInstanceOf[BranchEnv] join res2.asInstanceOf[BranchEnv]
          res.prependOutput(env.getOutput)
          return ("OK", res)
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
       * TODO Refactor variable Interpreter.access by reflection! */
      val test = Interpreter.accessField(s, "_test").asInstanceOf[Expr]
      val block = Interpreter.accessField(s, "_block").asInstanceOf[Statement]

      // TODO use real constraints
      val envs = env.fork(test.toString)
      val res1 = execute(block, envs._1)._2.asInstanceOf[BranchEnv]
      val res = res1 join envs._2
      res.prependOutput(env.getOutput)
      return ("OK", res)
    }

    /**
     * Statement of the form
     * return <-Expr>;
     */
    case s: ReturnStatement => {
      // TODO Refactor variable Interpreter.access by reflection!
      // TODO Call-by-reference
      val expr = Interpreter.accessField(s, "_expr").asInstanceOf[Expr]
      val v = evaluate(expr, env)

      env.update("return", evaluate(expr, env)._1)
      return ("OK", env)
    }

    case _ => throw new RuntimeException("execute() not implemented for AST class " + stmt + ".")
  }

  override def evaluate(e: Expr, env: Environment): (OakValue, Environment) = e match {

    /* Literals: String, Double, ...*/
    case e: LiteralExpr => {

      // TODO Refactor variable Interpreter.access by reflection!
      val value = Interpreter.accessField(e, "_value").asInstanceOf[Value]

      /*
       * Boolean Value
       */
      if (e.isBoolean) {
        if (e.isTrue()) {
          return (BooleanValue(true), env)
        } else if (e.isFalse()) {
          return (BooleanValue(false), env)
        } else {
          throw new RuntimeException()
        }
      } /*
       * Numeric Value
       */ else if (e.isNumber) {
        return try {
          if (e.isLong) {
            return (IntValue(e.toString.toInt), env)
          } else if (e.isDouble) {
            return (DoubleValue(e.toString.toDouble), env)
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
      return (new StringValue(e.toString.slice(1, e.toString.length - 1)), env)
    }

    /*
     * Variable expression of the form $<ID>
     */
    case e: VarExpr => {
      return (env.lookup(e.toString), env)
    }

    /*
     * Negated (boolean) expression of the form !<Expr>
     */
    case e: UnaryNotExpr => {
      return evaluate(e.getExpr, env)._1 match {

        // default case: e evaluates to a BooleanValue
        case e: BooleanValue => {
          (e.not, env)
        }

        // exceptional case: Found symbolic value -> return symbolic value
        case v2: SymbolicValue => (SymbolValue(e, OakHeap.getIndex), env)

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
        evaluate(e1, env)._1 match {

          // expecting a boolean expression, where e1 AND e2 are BooleanValues
          case v1: BooleanValue => {
            evaluate(e2, env)._1 match {

              // default case: e1 AND e2 are BooleanValues
              case v2: BooleanValue => {
                ae match {
                  case e: BinaryAndExpr => {
                    (v1 && v2, env)
                  }
                  case e: BinaryOrExpr => {
                    (v1 || v2, env)
                  }
                }
              }

              // exceptional cases: return symbolic value and track unresolved expression
              case v2: SymbolicValue => (SymbolValue(ae, OakHeap.getIndex), env)
              case v2: NumericValue => (SymbolValue(ae, OakHeap.getIndex), env)
              case v2: StringValue => (SymbolValue(ae, OakHeap.getIndex), env)

              case _ => throw new RuntimeException()
            }
          }

          // expecting a arithmetic expression, where e1 AND e2 are NumericValues
          case v1: NumericValue => {
            evaluate(e2, env)._1 match {

              // default case: e1 AND e2 are NumericValues
              case v2: NumericValue => {
                ae match {
                  case e: BinaryAddExpr => {
                    (v1 + v2, env)
                  }
                  case e: BinarySubExpr => {
                    (v1 - v2, env)
                  }
                  case e: BinaryMulExpr => {
                    (v1 * v2, env)
                  }
                  case e: BinaryDivExpr => {
                    (v1 / v2, env)
                  }
                  case e: BinaryModExpr => {
                    (v1 % v2, env)
                  }
                  case e: BinaryLtExpr => {
                    (v1 < v2, env)
                  }
                  case e: BinaryGtExpr => {
                    (v1 > v2, env)
                  }
                  case e: BinaryEqExpr => {
                    (BooleanValue(v1 == v2), env)
                  }
                }
              }

              // exceptional cases: return symbolic value and track unresolved expression
              case v2: SymbolicValue => (SymbolValue(ae, OakHeap.getIndex), env)
              case v2: BooleanValue => (SymbolValue(ae, OakHeap.getIndex), env)
              case v2: StringValue => (SymbolValue(ae, OakHeap.getIndex), env)

              case _ => throw new RuntimeException()
            }
          }

          case v1: StringValue => {
            //TODO CONCAT
            return (new SymbolValue(ae, OakHeap.getIndex), env)
          }
          // exceptional case: Any binary expression, where e1 is symbolic -> return a symbolic value
          case v1: SymbolicValue => (SymbolValue(ae, OakHeap.getIndex), env)
        }
      } catch {
        case e: Exception => throw new RuntimeException(e)
      }
    }

    case e: CallExpr => {

      // TODO Refactor variable Interpreter.access by reflection!
      val name = Interpreter.accessField(e, "_name").toString

      /* Retrieve the function call arguments. */
      var args = ListBuffer[Expr]()
      Interpreter.accessField(e, "_args").asInstanceOf[Array[Expr]].foreach {
        o => args += o
      }

      /* If the function has already been called, i.e., its name
       * is contained in the call stack, we just return a symbol value
       * in order to avoid recursive function calls. */
      if (env.getCalls.contains(name)) {
        return (new SymbolValue(e, OakHeap.getIndex), env)
      }

      /* If the function called refers to one implemented library function, such as
       * count($x) for arrays or concat/. for string literals, that implementation
       * will be used (instead). 
       * Each plugin implements its own library method, this.plugins is a map from 
       * the library function names to the actual plugin.
       * */
      if (getPlugins.contains(name)) {
        
        System.err.println("true with " + this.accept(getPlugin(name), args.toList, env))

        /* The library function plugin visits and evaluates the expression. */
        return (this.accept(getPlugin(name), args.toList, env), env)
      }
      System.err.println("false")

      /* Retrieve the function definition from the environment. If we fail
       * loading the function, we return with a symbol value, i.e., the called function
       * is either undefined or unimplemented. */
      val function = try {
        env.getFunction(name)
      } catch {
        case ex: Exception => return (SymbolValue(e, OakHeap.getIndex()), env)
      }

      // Assert that the number of arguments in the function call and declaration match
      assert(function.getArgs.length == args.length)

      /* CALL-BY-VALUE only */

      /**
       * Create a new (function) environment with pre-assigned arguments
       * TODO call-by-reference
       */
      val functionEnv = env.createFunctionEnvironment(name)
      (function.getArgs zip args).foreach {
        t => functionEnv.update("$" + t._1, evaluate(t._2, env)._1)
      }

      val result = execute(function.getStatement, functionEnv)
      val resultEnv = result._2

      //write output during the function call to the parent environment
      resultEnv.getParent().receiveOutput(resultEnv.getOutput())

      val returnValue = try {
        resultEnv.lookup("return")
      } catch {
        case e: Exception => null
      }
      return (returnValue, resultEnv)
    }

    /* Creates a new array value via call of function 'array(...)'
     * E.g., array(1,2,3) -> ArrayValue(IntValue(1) -> IntValue(1), ...)
     */
    case arrayExpr: FunArrayExpr => {
      val valueExprList = Interpreter.accessArrayFields[Expr](Interpreter.accessField(arrayExpr, "_values").asInstanceOf[Array[Expr]])
      val array = new ArrayValue()
      (valueExprList zipWithIndex).foreach {
        case (v: Expr, i: Int) => array.set(IntValue(i), evaluate(v, env)._1)
      }
      return (array, env)
    }

    case arrayGet: ArrayGetExpr => {
      val exx = Interpreter.accessField(arrayGet, "_expr").asInstanceOf[Expr]
      System.err.println(exx + " is da shigt " + env.getVariables())
      val expr = evaluate(exx  , env)._1
      val index = evaluate(Interpreter.accessField(arrayGet, "_index").asInstanceOf[Expr], env)._1
      expr match {
        case av: ArrayValue => {
          val value = av.get(index)
          return (value, env)
        }
        case _ => throw new RuntimeException("Not an array.")
      }
    }

    case _ => throw new RuntimeException("evaluate() not implemented for AST class " + e.getClass + ".")
  }

  /*
   * Library mechanism
   */

  override def getPlugin(name: String) = plugins.get(name).get
  override def getPlugins(): List[String] = plugins.keySet.toList
  override def loadPlugin(plugin: InterpreterPlugin) {
    System.err.println(plugins)
    plugins.put(plugin.getName, plugin)
  }
  override def accept(plugin: InterpreterPlugin, args: List[Expr], env: Environment): OakValue = plugin.visit(this, args, env)

}