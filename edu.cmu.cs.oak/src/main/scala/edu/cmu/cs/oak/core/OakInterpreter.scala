package edu.cmu.cs.oak.core

import java.util.LinkedHashMap

import scala.annotation.elidable.ASSERTION
import scala.collection.immutable.Stack
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer

import org.slf4j.LoggerFactory

import com.caucho.quercus.env.Value
import com.caucho.quercus.expr.AbstractBinaryExpr
import com.caucho.quercus.expr.AbstractVarExpr
import com.caucho.quercus.expr.ArrayGetExpr
import com.caucho.quercus.expr.BinaryAddExpr
import com.caucho.quercus.expr.BinaryAndExpr
import com.caucho.quercus.expr.BinaryAppendExpr
import com.caucho.quercus.expr.BinaryAssignExpr
import com.caucho.quercus.expr.BinaryAssignRefExpr
import com.caucho.quercus.expr.BinaryDivExpr
import com.caucho.quercus.expr.BinaryEqExpr
import com.caucho.quercus.expr.BinaryGtExpr
import com.caucho.quercus.expr.BinaryLtExpr
import com.caucho.quercus.expr.BinaryModExpr
import com.caucho.quercus.expr.BinaryMulExpr
import com.caucho.quercus.expr.BinaryOrExpr
import com.caucho.quercus.expr.BinarySubExpr
import com.caucho.quercus.expr.CallExpr
import com.caucho.quercus.expr.Expr
import com.caucho.quercus.expr.FunArrayExpr
import com.caucho.quercus.expr.LiteralExpr
import com.caucho.quercus.expr.LiteralUnicodeExpr
import com.caucho.quercus.expr.ObjectNewExpr
import com.caucho.quercus.expr.UnaryNotExpr
import com.caucho.quercus.expr.VarExpr
import com.caucho.quercus.program.Arg
import com.caucho.quercus.program.ClassField
import com.caucho.quercus.program.Function
import com.caucho.quercus.program.InterpretedClassDef
import com.caucho.quercus.program.QuercusProgram
import com.caucho.quercus.statement.BlockStatement
import com.caucho.quercus.statement.ClassDefStatement
import com.caucho.quercus.statement.EchoStatement
import com.caucho.quercus.statement.ExprStatement
import com.caucho.quercus.statement.IfStatement
import com.caucho.quercus.statement.ReturnStatement
import com.caucho.quercus.statement.Statement
import com.caucho.quercus.statement.WhileStatement

import edu.cmu.cs.oak.env.BranchEnv
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.env.OakHeap
import edu.cmu.cs.oak.env.SimpleEnv
import edu.cmu.cs.oak.exceptions.UnexpectedTypeException
import edu.cmu.cs.oak.exceptions.UnimplementedException
import edu.cmu.cs.oak.lib.InterpreterPlugin
import edu.cmu.cs.oak.lib.array.Count
import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.value.BooleanValue
import edu.cmu.cs.oak.value.ClassDef
import edu.cmu.cs.oak.value.DoubleValue
import edu.cmu.cs.oak.value.FunctionDef
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.value.NumericValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakValueSequence
import edu.cmu.cs.oak.value.OakVariable
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.value.SymbolicValue
import edu.cmu.cs.oak.value.ObjectValue
import edu.cmu.cs.oak.value.OakValueSequence
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.value.ClassDef
import edu.cmu.cs.oak.value.ObjectValue
import com.caucho.quercus.expr.ThisFieldExpr
import com.caucho.quercus.expr.ObjectMethodExpr
import java.util.ArrayList
import com.caucho.quercus.statement.ReturnRefStatement
import com.caucho.quercus.expr.ObjectFieldExpr

class OakInterpreter extends Interpreter with InterpreterPluginProvider {

  /** Logger for the interpreter */
  val logger = LoggerFactory.getLogger(classOf[Interpreter])

  def execute(program: QuercusProgram): (String, Environment) = {

    OakHeap.clear()

    val env = new SimpleEnv(null, Stack[String](), "true")

    /** Write all function definitions to the heap */
    val funcIterator = program.getFunctionList.iterator
    while (funcIterator.hasNext) {

      val func = Interpreter.defineFunction(funcIterator.next())

      // Add function to the global environment
      env.addFunction(func)
    }

    val res = execute(program.getStatement, env)

    return res
  }

  /*
     * Statement of the form
     * <Statement>; <Statement>; ...
     */
  def execute(stmt: BlockStatement, env: Environment): (String, Environment) = {
    var env_ = env
    stmt.getStatements.foreach {
      s => env_ = execute(s, env_)._2
    }
    return ("OK", env_)
  }

  /*
     * Statement of the form
     * echo <Expr>;
     */
  def execute(stmt: EchoStatement, env: Environment): (String, Environment) = {
    val expr = Interpreter.accessField(stmt, "_expr").asInstanceOf[Expr]
    val v = evaluate(expr, env)
    env.addOutput(v._1)
    return ("OK", env)
  }

  /*
   * Statement of the form
   * <Var> = <Expr>;
   */
  def execute(s: ExprStatement, env: Environment): (String, Environment) = {
    // TODO Refactor variable Interpreter.access by reflection!
    val e = Interpreter.accessField(s, "_expr").asInstanceOf[Expr]

    e match {
      case b: BinaryAssignExpr => {

        // TODO Refactor variable Interpreter.access by reflection!
        val name = Interpreter.accessField(e, "_var").asInstanceOf[AbstractVarExpr]
        val expr = Interpreter.accessField(e, "_value").asInstanceOf[Expr]

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

            /* Utility, transfer names with $xxx[i] to $xxx */
            val pattern = "\\](\\s|\\d|[^(\\[|\\])])*\\[".r
            val arrayValueName = (pattern replaceFirstIn (name.toString.reverse, "")).reverse

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
            return ("OK", env)
          }

          /* Assignment to object field */
          case dis: ThisFieldExpr => {
            val fieldName = Interpreter.accessField(dis, "_name").asInstanceOf[com.caucho.quercus.env.StringValue]

            /* $this points to an array, hence we perform an assignment to $this[name] */
            val thisValue = env.lookup("$this").asInstanceOf[ArrayValue]
            thisValue.set(StringValue(fieldName.toString), evaluate(expr, env)._1)

            env.update("$this", thisValue)

            ("OK", env)
          }

          case of: ObjectFieldExpr => {

            val objName = Interpreter.accessField(of, "_objExpr").asInstanceOf[Expr].toString()
            val obj = env.lookup(objName).asInstanceOf[ObjectValue]
            val fieldName = Interpreter.accessField(of, "_name").asInstanceOf[com.caucho.quercus.env.StringValue].toString()
            val ref = obj.fields.getRef(StringValue(fieldName))

            OakHeap.insert(ref, evaluate(expr, env)._1)

            ("OK", env)
          }
          case _ => throw new RuntimeException(name.getClass + " unexpected " + expr.toString())
        }
      }

      /**
       * TODO Seiteneffekte des Ausdruckes!
       */
      case c: CallExpr => {
        evaluate(c, env)
        return ("OK", env)
      }

      /* Assignment of a reference, i.e., something like $var = &$value; */
      case e: BinaryAssignRefExpr => {

        /* Retrieve the var and value value */
        val name = Interpreter.accessField(e, "_var").asInstanceOf[VarExpr] // AbstractVarExpr?
        val value = Interpreter.accessField(e, "_value").asInstanceOf[Expr]

        /* Update the environment so that 
         * + $var is mapped to the same reference as evaluate($value)
         * */
        val oakVariable = value match {
          case varexpr: VarExpr => {

            /* Look up the name of the referenced variable in the String -> OakVariable map*/
            env.getVariables.get(varexpr.toString).get
          }
          case aget: ArrayGetExpr => {

            // Get the reference of the stuff
            val exx = Interpreter.accessField(aget, "_expr").asInstanceOf[Expr]
            val expr = evaluate(exx, env)._1
            val index = evaluate(Interpreter.accessField(aget, "_index").asInstanceOf[Expr], env)._1

            assert(expr.isInstanceOf[ArrayValue] && index.isInstanceOf[OakValue])
            val ref = expr.asInstanceOf[ArrayValue].getRef(index)
            ref
          }

          /*
           * Since we are inside a BinaryAssignRefExpr, we assume that the returned value 
           * actually is a OakVariable/reference
           */
          case ome: ObjectMethodExpr => {

            val envi = evaluate(ome, env) // return value

            val ref = envi._1.asInstanceOf[OakVariable]
            ref
          }

          case _ => throw new RuntimeException(value + " (" + value.getClass + ") is unimplemented.")
        }

        /* Update */
        env.setRef(name.toString, oakVariable)

        return ("OK", env)

      }

      case o: ObjectMethodExpr => {
        val env_ = evaluate(o, env)._2
        ("OK", env_)
      }

      case _ => throw new RuntimeException(e.getClass + " is not implemented for ExprStatement.")
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
  def execute(s: IfStatement, env: Environment): (String, Environment) = {

    /* Retrieve the condition and both statements 
     * from the IfStatement AST node via reflection. */
    val test = Interpreter.accessField(s, "_test").asInstanceOf[Expr]
    val trueBlock = Interpreter.accessField(s, "_trueBlock").asInstanceOf[Statement]
    val falseBlock = Interpreter.accessField(s, "_falseBlock").asInstanceOf[Statement]

    /* Execute the statement either concrete (-> either one branch)
     * or symbolic by executing both branches and merging them
     * together afterwards. */
    return evaluate(test, env)._1 match {

      /* If the branch condition evaluates to a concrete value (of type
       * BoolenanValue, one of the two branches is selected and executed. */
      case b: BooleanValue => {
        if (b.value) {
          return execute(trueBlock, env)
        } else {
          return execute(falseBlock, env)
        }
      }

      /* Execute both branches (-> symbolically) and merge environments */
      case b: SymbolicValue => {

        // TODO use da real constraints
        val branches = env.fork(test.toString)

        /* Execute both branches with the corresponding branch environments. */
        val res1 = execute(trueBlock, branches._1)._2
        val res2 = execute(falseBlock, branches._2)._2

        /* Merge the two environments res1 and res2. */
        val res = res1.asInstanceOf[BranchEnv] join res2.asInstanceOf[BranchEnv]
        res.prependOutput(env.getOutput)

        return ("OK", res)
      }

      case _ => throw new RuntimeException("Evaluation of branch condition failed.")
    }
  }

  def execute(s: WhileStatement, env: Environment): (String, Environment) = {

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
  def execute(s: ReturnStatement, env: Environment): (String, Environment) = {
    // TODO Refactor variable Interpreter.access by reflection!
    val expr = Interpreter.accessField(s, "_expr").asInstanceOf[Expr]
    val v = evaluate(expr, env)

    env.update("$return", evaluate(expr, env)._1)
    return ("OK", env)
  }

  /**
   * Adds a class definition to the environment.
   */
  def execute(s: ClassDefStatement, env: Environment): (String, Environment) = {
    val interpreted = Interpreter.accessField(s, "_cl").asInstanceOf[InterpretedClassDef]

    // TODO clas declaration 
    val name = interpreted.getName
    val fields = Interpreter.accessField(interpreted, "_fieldMap").asInstanceOf[LinkedHashMap[com.caucho.quercus.env.StringValue, ClassField]]

    val methods = Interpreter.accessField(interpreted, "_functionMap").asInstanceOf[LinkedHashMap[com.caucho.quercus.env.StringValue, Function]]

    /* Define all methods as functions */
    val classMethodNames = List(methods.keySet().toArray: _*)

    val classMethodDefs = ListBuffer[FunctionDef]()
    classMethodNames.foreach { name => classMethodDefs.append(Interpreter.defineFunction(methods.get(name))) }

    /* Add all methods ex*/
    var classMethodDefMap = Map[String, FunctionDef]()
    classMethodDefs.foreach {
      fd =>
        {
          if (!fd.name.equals("__construct")) {
            classMethodDefMap = classMethodDefMap + (fd.name -> fd)
          }
        }
    }

    /* Get all field names */
    val classFieldNamez = List(fields.keySet().toArray: _*)
    val classFieldNames = classFieldNamez.map { name => name.toString() }

    val classDef = ClassDef(name, classFieldNames, classMethodDefMap)

    /* Add construcotrs to the class definition. */
    classMethodDefs.takeWhile {
      fd => fd.name.equals("__construct")
    }.foreach {
      fd => classDef.addConstructor(fd.args.size, fd)
    }
    env.addClass(classDef)
    return ("OK", env)
  }

  def execute(s: ReturnRefStatement, env: Environment): (String, Environment) = {
    // TODO Refactor variable Interpreter.access by reflection!

    val u = Interpreter.accessField(s, "_expr").asInstanceOf[Expr]

    val returnRef = u match {

      case t: ThisFieldExpr => {
        val fieldName = Interpreter.accessField(t, "_name").asInstanceOf[com.caucho.quercus.env.StringValue].toString()
        val thisArray = env.lookup("$this").asInstanceOf[ArrayValue]
        thisArray.getRef(StringValue(fieldName))
      }
      case v: VarExpr => {
        env.getVariables().get(v.toString).get
      }
      case a: ArrayGetExpr => {
        val array = env.lookup(Interpreter.accessField(a, "_expr").asInstanceOf[Expr].toString()).asInstanceOf[ArrayValue]
        val index = evaluate(Interpreter.accessField(a, "_index").asInstanceOf[Expr], env)._1
        array.getRef(index)
      }
    }

    env.setRef("$return", returnRef)
    return ("OK", env)
  }

  override def execute(stmt: Statement, env: Environment): (String, Environment) = stmt match {

    case s: BlockStatement => execute(s, env)
    case s: EchoStatement => execute(s, env)
    case s: ExprStatement => execute(s, env)
    case s: IfStatement => execute(s, env)
    case s: WhileStatement => execute(s, env)
    case s: ReturnStatement => execute(s, env)
    case s: ClassDefStatement => execute(s, env)
    case s: ReturnRefStatement => execute(s, env)
    case _ => throw new RuntimeException(stmt.getClass + " unimplemented.")
  }

  def evaluate(e: LiteralExpr, env: Environment): (OakValue, Environment) = {
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

    } else {
      throw new RuntimeException()
    }
  }

  def evaluate(e: LiteralUnicodeExpr, env: Environment): (OakValue, Environment) = {
    return (new StringValue(e.toString.slice(1, e.toString.length - 1)), env)
  }

  def evaluate(e: VarExpr, env: Environment): (OakValue, Environment) = {
    val value = env.lookup(e.toString)
    return value match {
      case ref: OakVariable => (OakHeap.extract(ref), env)
      case _ => (value, env)
    }
  }

  def evaluate(e: UnaryNotExpr, env: Environment): (OakValue, Environment) = {
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

  def evaluate(ae: AbstractBinaryExpr, env: Environment): (OakValue, Environment) = {

    // Retrieve the operands e1, e2 of the binary expression
    val e1 = ae.getLeft
    val e2 = ae.getRight

    return try {

      // evaluate e1 and match by type
      val e1v = evaluate(e1, env)._1
      e1v match {

        // expecting a boolean expression, where e1 AND e2 are BooleanValues
        case v1: BooleanValue => {
          evaluate(e2, env)._1 match {

            // default case: e1 AND e2 are BooleanValues
            case v2: BooleanValue => {
              (ae match {
                case e: BinaryAndExpr => {
                  v1 && v2
                }
                case e: BinaryOrExpr => {
                  v1 || v2
                }
              }, env)
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
              (ae match {
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
              }, env)
            }

            // exceptional cases: return symbolic value and track unresolved expression
            case v2: SymbolicValue => (SymbolValue(ae, OakHeap.getIndex), env)
            case v2: BooleanValue => (SymbolValue(ae, OakHeap.getIndex), env)
            case v2: StringValue => (SymbolValue(ae, OakHeap.getIndex), env)

            case v2: ArrayValue => throw new UnexpectedTypeException(e2, "Can't compare array with " + v1.getClass + ".")
            case _ => throw new UnexpectedTypeException(e2, evaluate(e2, env)._1 + "")
          }
        }

        case v1: StringValue => {
          //TODO CONCAT?
          return (new SymbolValue(ae, OakHeap.getIndex), env)
        }

        case v1: VarExpr => {
          return (evaluate(v1, env)._1, env) // TODO more cases
        }

        // exceptional case: Any binary expression, where e1 is symbolic -> return a symbolic value
        case v1: SymbolicValue => (SymbolValue(ae, OakHeap.getIndex), env)

        case _ => throw new UnexpectedTypeException(e1, " any type1 ")
      }
    } catch {
      case e: Exception => throw new RuntimeException(e)
    }
  }

  def evaluate(e: CallExpr, env: Environment): (OakValue, Environment) = {

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

      /* The library function plugin visits and evaluates the expression. */
      return (this.accept(getPlugin(name), args.toList, env), env)
    }

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
      t =>
        {
          val functionVal = if (t._1.startsWith("&")) {
            // is reference arg
            try {
              env.getVariables.get(t._2.toString).get
            } catch {
              case nsee: NoSuchElementException => throw new RuntimeException("Only variables can be passed by reference.")
            }
          } else {
            evaluate(t._2, env)._1
          }
          functionEnv.update("$" + t._1.replace("&", ""), functionVal)
        }
    }

    val result = execute(function.getStatement, functionEnv)
    val resultEnv = result._2

    //write output during the function call to the parent environment
    resultEnv.getParent().receiveOutput(resultEnv.getOutput())

    val returnValue = try {
      resultEnv.lookup("$return")
    } catch {
      case e: Exception => StringValue("komishc")
    }
    return (returnValue, resultEnv)
  }

  def evaluate(arrayExpr: FunArrayExpr, env: Environment): (OakValue, Environment) = {
    val valueExprList = Interpreter.accessArrayFields[Expr](Interpreter.accessField(arrayExpr, "_values").asInstanceOf[Array[Expr]])
    val array = new ArrayValue()
    (valueExprList zipWithIndex).foreach {
      case (v: Expr, i: Int) => array.set(IntValue(i), evaluate(v, env)._1)
    }
    return (array, env)
  }

  def evaluate(arrayGet: ArrayGetExpr, env: Environment): (OakValue, Environment) = {
    val exx = Interpreter.accessField(arrayGet, "_expr").asInstanceOf[Expr]
    val expr = evaluate(exx, env)._1
    val index = evaluate(Interpreter.accessField(arrayGet, "_index").asInstanceOf[Expr], env)._1
    expr match {
      case av: ArrayValue => {
        val value = av.get(index)
        return (value, env)
      }
      case _ => throw new RuntimeException("Not an array.")
    }
  }

  def evaluate(b: BinaryAppendExpr, env: Environment): (OakValue, Environment) = {
    /* Get all values that are being concatenated*/
    val expressions = ListBuffer[Expr]()
    var expr = b
    
    expressions.append(expr.getValue)
    do {
      expr = expr.getNext
      expressions.append(expr.getValue)
    } while (expr.getNext != null)
    
    val value = OakValueSequence(expressions.toList.map { e => evaluate(e, env)._1 })

    return (value, env)
  }

  /*
  * $objExpr->methodName(args...)
  * 
  */
  def evaluate(e: ObjectMethodExpr, env: Environment): (OakValue, Environment) = {
    val objExpr = Interpreter.accessField(e, "_objExpr").asInstanceOf[Expr]
    val methodName = Interpreter.accessField(e, "_methodName").asInstanceOf[com.caucho.quercus.env.StringValue].toString()
    val args = Interpreter.accessField(e, "_args").asInstanceOf[Array[Expr]]
    /*
     * Create new Environment
     */
    val objEnv = env.createObjectEnvironment(env.lookup(objExpr.toString()).asInstanceOf[ObjectValue]) // assuming this is sth like $obj

    val method = env.lookup(objExpr.toString()).asInstanceOf[ObjectValue].getClassdef().getMethods(methodName)

    assert(method.args.size == args.size)
    (method.args, args).zipped.foreach {
      (name, expr) => objEnv.update("$" + name, evaluate(expr, env)._1)
    }

    val objectValBefore = env.lookup(objExpr.toString()).asInstanceOf[ObjectValue]

    val envRes = execute(method.statement, objEnv)._2

    val arrayVal = envRes.lookup("$this").asInstanceOf[ArrayValue]
    val returnVal = try {
      if (method.returnsRef()) {
        envRes.getRef("$return")
      } else {
        envRes.lookup("$return")
      }
    } catch {
      case e: Exception => IntValue(404)
    }

    arrayVal.getKeys.foreach { key => objectValBefore.set(key.toString, arrayVal.get(key)) }
    env.update(objExpr.toString(), objectValBefore)
    (returnVal, env)

  }

  def evaluate(b: ObjectNewExpr, env: Environment): (OakValue, Environment) = {

    // class name
    val name = Interpreter.accessField(b, "_name").asInstanceOf[String]

    /** Arguments passed to constructor */
    val args = Interpreter.accessField(b, "_args").asInstanceOf[Array[Expr]]

    /** Create a new object Value */
    val obj = ObjectValue("Object Doe", env.getClass(name))
    val objEnv = env.createObjectEnvironment(obj)
    val constructor = env.getClass(name).getConstructor(args.size) // match by number of args

    /* Execute constructor in the object environment and keep its variable $this:
     *  
     *  - assign all evaluated arg expressions to the constructor args
     *   */
    assert(constructor.args.size == args.size)
    (constructor.args, args).zipped.foreach {
      (name, expr) => objEnv.update("$" + name, evaluate(expr, env)._1)
    }

    val envConstructed = execute(constructor.statement, objEnv)._2

    val thisArray = envConstructed.lookup("$this").asInstanceOf[ArrayValue]
    thisArray.getKeys.foreach { key => obj.set(key.toString, thisArray.get(key)) }

    (obj, env)
  }

  def evaluate(t: ThisFieldExpr, env: Environment): (OakValue, Environment) = {
    val fieldName = Interpreter.accessField(t, "_name").asInstanceOf[com.caucho.quercus.env.StringValue].toString()
    val ref = env.lookup("$this").asInstanceOf[ArrayValue].getRef(StringValue(fieldName))
    (ref, env)
  }

  override def evaluate(e: Expr, env: Environment): (OakValue, Environment) = e match {
    case e: LiteralExpr => evaluate(e, env)
    case e: LiteralUnicodeExpr => evaluate(e, env)
    case e: VarExpr => evaluate(e, env)
    case e: UnaryNotExpr => evaluate(e, env)
    case ae: AbstractBinaryExpr => evaluate(ae, env)
    case e: CallExpr => evaluate(e, env)
    case arrayExpr: FunArrayExpr => evaluate(arrayExpr, env)
    case arrayGet: ArrayGetExpr => evaluate(arrayGet, env)
    case b: BinaryAppendExpr => evaluate(b, env)
    case o: ObjectNewExpr => evaluate(o, env)
    case o: ObjectMethodExpr => evaluate(o, env)
    case t: ThisFieldExpr => evaluate(t, env)
    case _ => throw new RuntimeException(e + " (" + e.getClass + ") unimplemented.")

  }
}