package edu.cmu.cs.oak.core

import java.nio.file.Path
import java.nio.file.Paths
import java.util.LinkedHashMap

import scala.annotation.elidable.ASSERTION
import scala.collection.immutable.Stack
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks.break
import scala.util.control.Breaks.breakable
import scala.xml.PrettyPrinter

import org.slf4j.LoggerFactory

import com.caucho.quercus.env.Value
import com.caucho.quercus.expr.AbstractBinaryExpr
import com.caucho.quercus.expr.AbstractVarExpr
import com.caucho.quercus.expr.ArrayGetExpr
import com.caucho.quercus.expr.ArrayTailExpr
import com.caucho.quercus.expr.BinaryAddExpr
import com.caucho.quercus.expr.BinaryAndExpr
import com.caucho.quercus.expr.BinaryAppendExpr
import com.caucho.quercus.expr.BinaryAssignExpr
import com.caucho.quercus.expr.BinaryAssignListExpr
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
import com.caucho.quercus.expr.ConditionalExpr
import com.caucho.quercus.expr.ConstExpr
import com.caucho.quercus.expr.Expr
import com.caucho.quercus.expr.FunArrayExpr
import com.caucho.quercus.expr.FunDieExpr
import com.caucho.quercus.expr.FunEmptyExpr
import com.caucho.quercus.expr.FunExitExpr
import com.caucho.quercus.expr.FunIncludeExpr
import com.caucho.quercus.expr.FunIncludeOnceExpr
import com.caucho.quercus.expr.FunIssetExpr
import com.caucho.quercus.expr.ListHeadExpr
import com.caucho.quercus.expr.LiteralExpr
import com.caucho.quercus.expr.LiteralLongExpr
import com.caucho.quercus.expr.LiteralNullExpr
import com.caucho.quercus.expr.LiteralUnicodeExpr
import com.caucho.quercus.expr.ObjectFieldExpr
import com.caucho.quercus.expr.ObjectMethodExpr
import com.caucho.quercus.expr.ObjectNewExpr
import com.caucho.quercus.expr.ThisFieldExpr
import com.caucho.quercus.expr.ToArrayExpr
import com.caucho.quercus.expr.UnaryMinusExpr
import com.caucho.quercus.expr.UnaryNotExpr
import com.caucho.quercus.expr.UnaryPostIncrementExpr
import com.caucho.quercus.expr.UnarySuppressErrorExpr
import com.caucho.quercus.expr.VarExpr
import com.caucho.quercus.expr.VarUnsetExpr
import com.caucho.quercus.program.ClassField
import com.caucho.quercus.program.Function
import com.caucho.quercus.program.InterpretedClassDef
import com.caucho.quercus.program.QuercusProgram
import com.caucho.quercus.statement.BlockStatement
import com.caucho.quercus.statement.BreakStatement
import com.caucho.quercus.statement.ClassDefStatement
import com.caucho.quercus.statement.ContinueStatement
import com.caucho.quercus.statement.DoStatement
import com.caucho.quercus.statement.EchoStatement
import com.caucho.quercus.statement.ExprStatement
import com.caucho.quercus.statement.ForStatement
import com.caucho.quercus.statement.ForeachStatement
import com.caucho.quercus.statement.GlobalStatement
import com.caucho.quercus.statement.IfStatement
import com.caucho.quercus.statement.ReturnRefStatement
import com.caucho.quercus.statement.ReturnStatement
import com.caucho.quercus.statement.Statement
import com.caucho.quercus.statement.StaticStatement
import com.caucho.quercus.statement.SwitchStatement
import com.caucho.quercus.statement.TextStatement
import com.caucho.quercus.statement.WhileStatement

import edu.cmu.cs.oak.analysis.ASTVisitor
import edu.cmu.cs.oak.env.AbstractEnv
import edu.cmu.cs.oak.env.BranchEnv
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.env.SimpleEnv
import edu.cmu.cs.oak.env.heap.OakHeap
import edu.cmu.cs.oak.exceptions.UnexpectedTypeException
import edu.cmu.cs.oak.exceptions.VariableNotFoundException
import edu.cmu.cs.oak.lib.array.Count
import edu.cmu.cs.oak.lib.builtin.Define
import edu.cmu.cs.oak.lib.builtin.Defined
import edu.cmu.cs.oak.lib.builtin.DirName
import edu.cmu.cs.oak.lib.builtin.Print
import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.value.BooleanValue
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.ClassDef
import edu.cmu.cs.oak.value.ClassDef
import edu.cmu.cs.oak.value.DoubleValue
import edu.cmu.cs.oak.value.FunctionDef
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.NumericValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakValueSequence
import edu.cmu.cs.oak.value.OakVariable
import edu.cmu.cs.oak.value.ObjectValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.value.SymbolicValue
import edu.cmu.cs.oak.lib.array.IsArray

class OakInterpreter extends Interpreter with InterpreterPluginProvider {

  /** Logger for the interpreter */
  val logger = LoggerFactory.getLogger(classOf[Interpreter])

  this.loadPlugin(new Count)
  this.loadPlugin(new Print)
  this.loadPlugin(new DirName)
  this.loadPlugin(new Define)
  this.loadPlugin(new Defined)
  this.loadPlugin(new IsArray)
  
  def execute(path: Path): (String, Environment) = {

    // The current script is located at this.path
    this.path = path

    // The root directory is path.getParent()
    this.rootPath = path.getParent()

    val engine = new OakEngine()
    val program = engine.loadFromFile(path)
    val env = new SimpleEnv(null, Stack[String](), "true")
    OakHeap.clear()

    env.update("$_POST", SymbolValue("$_POST", OakHeap.index))
    env.update("$_GET", SymbolValue("$_GET", OakHeap.index))

    env.update("$_SERVER", SymbolValue("", OakHeap.index))
    Environment.addToGlobal("$_SERVER", env.lookup("$_SERVER"))

    val res = execute(program, env)

    res
  }

  def execute(program: QuercusProgram, env: Environment): (String, Environment) = {

    /** Write all function definitions to the heap */
    val funcIterator = program.getFunctionList.iterator
    while (funcIterator.hasNext) {

      val func = Environment.defineFunction(funcIterator.next())

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

    currentLineNr = ASTVisitor.getStatementLineNr(stmt)

    var env_ = env
    breakable {
      stmt.getStatements.foreach {
        s =>
          {
            if (s.isInstanceOf[BreakStatement]) {
              break
            } else {
              env_ = execute(s, env_)._2
            }
          }
      }
    }
    return ("OK", env_)
  }

  /*
     * Statement of the form
     * echo <Expr>;
     */
  def execute(stmt: EchoStatement, env: Environment): (String, Environment) = {
    val expr = Interpreter.accessField(stmt, "_expr").asInstanceOf[Expr]

    currentLineNr = ASTVisitor.getStatementLineNr(stmt)

    val value = evaluate(expr, env)._1
    val valueX = value match {
      case sv: StringValue => {
        sv.setLocation((path.toString diff rootPath.toString, currentLineNr))
        sv
      }
      case a: ArrayValue => {
        throw new RuntimeException("Can't echo an entire array.")
      }
      case _ => value
    }

    env.addOutput(valueX)
    env.addOutputToModel(value)

    return ("OK", env)
  }

  /*
   * Statement of the form
   * <Var> = <Expr>;
   */
  def execute(s: ExprStatement, env: Environment): (String, Environment) = {

    currentLineNr = ASTVisitor.getStatementLineNr(s)

    // TODO Refactor variable Interpreter.access by reflection!
    val e = Interpreter.accessField(s, "_expr").asInstanceOf[Expr]

    e match {

      case exit: FunExitExpr => {
        ("OK", env)
      }

      case u: VarUnsetExpr => {
        val varToUnset = Interpreter.accessField(u, "_var")
        varToUnset match {
          case varex: VarExpr => {
            env.unset(varToUnset.toString)
          }
          case ag: ArrayGetExpr => {
            val expr = Interpreter.accessField(ag, "_expr").asInstanceOf[Expr]
            if (!env.lookup(expr.toString).isInstanceOf[SymbolValue]) {
              val index = evaluate(Interpreter.accessField(ag, "_index").asInstanceOf[Expr], env)._1
              env.unsetArrayElement(expr.toString, index)
            }
          }
        }

        ("OK", env)
      }

      case b: BinaryAssignExpr => ("OK", evaluate(b, env)._2)

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
        val name = Interpreter.accessField(e, "_var").asInstanceOf[AbstractVarExpr] // AbstractVarExpr?
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

            println(env.getClass + " " + env.lookup("$arg"))
            assert(expr.isInstanceOf[ArrayValue])

            println("-01")
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

      case d: FunDieExpr => {
        //logger.info("die() not implemented.")
        ("OK", env)
      }

      case include: FunIncludeExpr => {
        val oldURL = this.path
        val expr = Interpreter.accessField(include, "_expr").asInstanceOf[Expr]
        val includePath = Paths.get(this.rootPath + "/" + evaluate(expr, env)._1.toString.replace("\"", ""))
        val program = (new OakEngine).loadFromFile(includePath)

        this.includes.push(includePath)

        //graphListener.addEdge(path.toString.substring(path.toString.lastIndexOf('/') + 1, path.toString.length()), include.toString)
        this.path = includePath
        //logger.info("Including script " + includePath)
        val res = this.execute(program, env)._2
        //logger.info("Resuming " + oldURL)
        this.path = oldURL

        this.includes.pop()
        ("OK", res)
      }

      case includeOnce: FunIncludeOnceExpr => {
        val oldURL = this.path
        val expr = Interpreter.accessField(includeOnce, "_expr").asInstanceOf[Expr]
        val includePath = Paths.get(this.rootPath + "/" + evaluate(expr, env)._1.toString.replace("\"", ""))
        val program = (new OakEngine).loadFromFile(includePath)

        if (this.includes contains includePath) {
          return ("NOT OK", env)
        }
        this.includes.push(includePath)

        //graphListener.addEdge(path.toString.substring(path.toString.lastIndexOf('/') + 1, path.toString.length()), includeOnce.toString)
        this.path = includePath
        //logger.info("Including script " + includePath)
        val res = this.execute(program, env)._2
        //logger.info("Resuming " + oldURL)
        this.path = oldURL

        this.includes.pop()
        ("OK", res)
      }

      case e: AbstractBinaryExpr => {
        ("OK", env) //evaluate(e, env)._2
      }

      case p: UnaryPostIncrementExpr => {
        val e = Interpreter.accessField(p, "_expr").asInstanceOf[Expr]
        val i = Interpreter.accessField(p, "_incr").asInstanceOf[Int]

        val result = evaluate(e, env)._1 match {
          case a: SymbolicValue => SymbolValue(p.toString, OakHeap.index)
          case b: NumericValue => {
            b + IntValue(i)
          }
          case _ => throw new RuntimeException()
        }
        env.update(e.toString, result)

        ("OK", env)
      }

      /**
       * Assignment of the form
       * list($a, $b, ...) = array(1, 2, ...)
       */
      case e: BinaryAssignListExpr => {

        // value to assign, needs to be an array
        val value = Interpreter.accessField(e, "_value").asInstanceOf[Expr]

        // list to assign array elements to
        val listHead = Interpreter.accessField(e, "_listHead").asInstanceOf[ListHeadExpr]
        val vars = Interpreter.accessField(listHead, "_varList").asInstanceOf[Array[Expr]]

        evaluate(value, env)._1 match {
          case a: ArrayValue => {
            assert(a.array.size == vars.size)
            (vars zipWithIndex).foreach {
              case (v, av) => env.update(v.toString, OakHeap.extract(a.array.get(IntValue(av)).get))
            }
          }
          case s: SymbolicValue => {
            vars.foreach {
              v => env.update(v.toString, SymbolValue(v.toString, OakHeap.index))
            }
          }
        }
        ("OK", env)
      }

      case e: UnarySuppressErrorExpr => ("OK", evaluate(e, env)._2)

      case _ => throw new RuntimeException(e.getClass + " is not implemented for ExprStatement, expr is " + e.toString())
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

    currentLineNr = ASTVisitor.getStatementLineNr(s)

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
        } else if (falseBlock != null) {
          return execute(falseBlock, env)
        } else {
          ("OK", env)
        }
      }

      /* Execute both branches (-> symbolically) and merge environments */
      case b: SymbolicValue => {

        // TODO use da real constraints
        val branches = Environment.fork(env, test.toString)

        /* Execute both branches with the corresponding branch environments. */
        val res1 = execute(trueBlock, branches._1)._2
        val res2 = try {
          execute(falseBlock, branches._2)._2
        } catch {
          case e: Exception => branches._2
        }

        /* Merge the two environments res1 and res2. */
        val res = res1.asInstanceOf[BranchEnv] join res2.asInstanceOf[BranchEnv]
        res.prependOutput(env.getOutput)
        res.prependOutputToModel(env.getOutput())
        return ("OK", res)
      }

      case _ => throw new RuntimeException("Evaluation of branch condition failed.")
    }
  }

  def execute(s: WhileStatement, env: Environment): (String, Environment) = {

    currentLineNr = ASTVisitor.getStatementLineNr(s)

    /* Retrieve the condition and both statements from the WhileStatement AST node.
     * TODO Refactor variable Interpreter.access by reflection! */
    val test = Interpreter.accessField(s, "_test").asInstanceOf[Expr]

    val env_ = evaluate(test, env)._2

    val block = Interpreter.accessField(s, "_block").asInstanceOf[Statement]

    // TODO use real constraints
    val envs = Environment.fork(env_, test.toString)
    val res1 = execute(block, envs._1)._2.asInstanceOf[BranchEnv]
    val res = res1 join envs._2

    res.prependOutput(env.getOutput)
    res.prependOutputToModel(env.getOutput)

    return ("OK", res)
  }

  /**
   * Statement of the form
   * return <-Expr>;
   */
  def execute(s: ReturnStatement, env: Environment): (String, Environment) = {
    currentLineNr = ASTVisitor.getStatementLineNr(s)

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

    currentLineNr = ASTVisitor.getStatementLineNr(s)

    val interpreted = Interpreter.accessField(s, "_cl").asInstanceOf[InterpretedClassDef]

    // TODO clas declaration 
    val name = interpreted.getName
    val fields = Interpreter.accessField(interpreted, "_fieldMap").asInstanceOf[LinkedHashMap[com.caucho.quercus.env.StringValue, ClassField]]

    val methods = Interpreter.accessField(interpreted, "_functionMap").asInstanceOf[LinkedHashMap[com.caucho.quercus.env.StringValue, Function]]

    /* Define all methods as functions */
    val classMethodNames = List(methods.keySet().toArray: _*)

    val classMethodDefs = ListBuffer[FunctionDef]()
    classMethodNames.foreach {
      name => classMethodDefs.append(Environment.defineFunction(methods.get(name)))
    }

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
    Environment.addClass(classDef)
    return ("OK", env)
  }

  def execute(s: ReturnRefStatement, env: Environment): (String, Environment) = {

    currentLineNr = ASTVisitor.getStatementLineNr(s)

    val u = Interpreter.accessField(s, "_expr").asInstanceOf[Expr]

    val returnRef = u match {

      case t: ThisFieldExpr => {
        val fieldName = Interpreter.accessField(t, "_name").asInstanceOf[com.caucho.quercus.env.StringValue].toString()

        println("-02")
        val thisArray = env.lookup("$this").asInstanceOf[ArrayValue]
        thisArray.getRef(StringValue(fieldName))
      }
      case v: VarExpr => {
        env.getVariables().get(v.toString).get
      }
      case a: ArrayGetExpr => {
        println("-03")
        val array = env.lookup(Interpreter.accessField(a, "_expr").asInstanceOf[Expr].toString()).asInstanceOf[ArrayValue]
        val index = evaluate(Interpreter.accessField(a, "_index").asInstanceOf[Expr], env)._1
        array.getRef(index)
      }
    }

    env.setRef("$return", returnRef)
    return ("OK", env)
  }

  /**
   * Statement of the form:
   * switch ($name) {
   * 	case "Alice":
   * 		echo "Hi, my name is Alice.";
   * 		break;
   * 	case "Bob":
   * 		echo "Hi, my name is Bob."
   * 		break;
   * 	default:
   * 		echo "My name is neither Alice, nor Bob.";
   * 		break;
   * }
   */
  def execute(s: SwitchStatement, env: Environment): (String, Environment) = {

    currentLineNr = ASTVisitor.getStatementLineNr(s)

    val v = Interpreter.accessField(s, "_value").asInstanceOf[Expr]
    val value = evaluate(v, env)._1
    val cases = Interpreter.accessField(s, "_cases").asInstanceOf[Array[Array[Expr]]]
    val blocks = Interpreter.accessField(s, "_blocks").asInstanceOf[Array[BlockStatement]]
    val default = Interpreter.accessField(s, "_defaultBlock").asInstanceOf[Statement]

    /**
     * Symbolic execution...
     */
    if (value.isInstanceOf[SymbolicValue] || default == null) {

      /* Create a new branch environment for each 
       * case including the default case. */
      val cazes = {
        val i = new ListBuffer[Expr]
        cases.foreach { c => i += c(0) }
        i.toList
      }
      val envs = Environment.splitN(env, v, cazes)

      /* Execute branches */
      var envs_ = (blocks.toList zip envs).map {
        case (b, env) => execute(b, env)._2.asInstanceOf[BranchEnv]
      }

      val defaultEnv = if (default != null) {
        val defaultEnv = new BranchEnv(env, env.getCalls, env.getConstraint)
        execute(default, defaultEnv)._2.asInstanceOf[BranchEnv]
      } else {
        null
      }

      /* Merge all branches again 
       * 
       * */
      val res = Environment.joinN(env, envs_, defaultEnv) //envs_.slice(1, envs_.size).foldLeft(envs_(0))(_ join _)

      return ("OK", res)
    }

    /* Get the matching case. */
    val caseValues = {
      val l = ListBuffer[OakValue]()
      cases.foreach {
        caze => l += evaluate(caze(0), env)._1
      }
      l.toList
    }

    /* Get the statement corresponding to the case. */
    val statement: Statement = if (caseValues contains value) {
      blocks(caseValues.indexOf(value))
    } else {
      default
    }

    assert(statement != null)

    /* Execute the case's statement and return environment. */
    val result = execute(statement, env)._2
    return ("OK", result)
  }

  def execute(s: BreakStatement, env: Environment): (String, Environment) = {
    ("OK", env) //TODO check
  }

  def execute(s: ContinueStatement, env: Environment): (String, Environment) = {
    ("OK", env) //TODO check
  }

  def execute(s: ForStatement, env: Environment): (String, Environment) = {

    currentLineNr = ASTVisitor.getStatementLineNr(s)

    /* Retrieve the condition and both statements from the ForStatement AST node.
     * TODO Refactor variable Interpreter.access by reflection! */
    val test = Interpreter.accessField(s, "_test").asInstanceOf[Expr]
    val init = Interpreter.accessField(s, "_init").asInstanceOf[Expr]

    val block = Interpreter.accessField(s, "_block").asInstanceOf[Statement]

    // TODO use real constraints
    val envs = Environment.fork(env, test.toString)

    val initEnv = evaluate(init, envs._1)._2
    val res1 = execute(block, initEnv)._2.asInstanceOf[BranchEnv]
    val res = res1 join envs._2
    res.prependOutput(env.getOutput)
    res.prependOutputToModel(env.getOutput())
    return ("OK", res)

  }

  def execute(s: TextStatement, env: Environment): (String, Environment) = {
    val value = Interpreter.accessField(s, "_value").asInstanceOf[com.caucho.quercus.env.StringValue]
    ("OK", env)
  }

  def execute(s: GlobalStatement, env: Environment): (String, Environment) = {
    val value = Interpreter.accessField(s, "_var").asInstanceOf[VarExpr]
    try {
      Environment.addToGlobal(value.toString, evaluate(value, env)._1)
    } catch {
      case vnfe: VariableNotFoundException => {
        Environment.addToGlobal(value.toString)
      }
    }

    ("OK", env)
  }

  def execute(s: StaticStatement, env: Environment): (String, Environment) = {
    /**
     * protected StringValue _uniqueStaticName;
     *
     * protected VarExpr _var;
     * protected Expr _initValue;
     */
    val uniqueStaticName = Interpreter.accessField(s, "_uniqueStaticName").asInstanceOf[com.caucho.quercus.env.StringValue].toString()
    val variable = Interpreter.accessField(s, "_var").asInstanceOf[VarExpr]
    val initValue = evaluate(Interpreter.accessField(s, "_initValue").asInstanceOf[Expr], env)._1

    try {
      Environment.addToGlobal(variable.toString, evaluate(variable, env)._1)
    } catch {
      case vnfe: VariableNotFoundException => {
        Environment.addToGlobal(variable.toString)
      }
    }
    env.update(variable.toString, initValue)
    ("OK", env)
  }

  /**
   * There are two syntaxes for foreach statements,
   * A)
   * foreach (<array_expression> as $value)
   * <statement>
   *
   * or B)
   * foreach (<array_expression> as $key => $value)
   * <statement>
   */
  def execute(s: ForeachStatement, env: Environment): (String, Environment) = {
    currentLineNr = ASTVisitor.getStatementLineNr(s)

    println("-04")
    val objExpr = (evaluate(Interpreter.accessField(s, "_objExpr").asInstanceOf[Expr], env)._1)

    val key = Interpreter.accessField(s, "_key").asInstanceOf[AbstractVarExpr]
    val value = Interpreter.accessField(s, "_value").asInstanceOf[AbstractVarExpr]

    /** Loop body */
    val block = Interpreter.accessField(s, "_block").asInstanceOf[Statement]

    // TODO use real constraints
    val envs = Environment.fork(env, "count(" + objExpr + ") > 0")
    envs._1.update(value.toString(), SymbolValue("foreach::", OakHeap.index))
    val res1 = execute(block, envs._1)._2.asInstanceOf[BranchEnv]
    val res = res1 join envs._2
    res.prependOutput(env.getOutput)
    res.prependOutputToModel(env.getOutput)

    ("OK", res)
  }
  
  
  def execute(s: DoStatement, env: Environment): (String, Environment) = {

    currentLineNr = ASTVisitor.getStatementLineNr(s)

    /* Retrieve the condition and both statements from the WhileStatement AST node.
     * TODO Refactor variable Interpreter.access by reflection! */
    val test = Interpreter.accessField(s, "_test").asInstanceOf[Expr]

    val env_ = evaluate(test, env)._2

    val block = Interpreter.accessField(s, "_block").asInstanceOf[Statement]

    // TODO use real constraints
    val envs = Environment.fork(env_, test.toString)
    val res1 = execute(block, envs._1)._2.asInstanceOf[BranchEnv]
    val res = res1 join envs._2

    res.prependOutput(env.getOutput)
    res.prependOutputToModel(env.getOutput)

    return ("OK", res)
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
    case s: SwitchStatement => execute(s, env)
    case s: BreakStatement => execute(s, env)
    case s: ForStatement => execute(s, env)
    case s: ContinueStatement => execute(s, env)
    case s: TextStatement => execute(s, env)
    case s: GlobalStatement => execute(s, env)
    case s: StaticStatement => execute(s, env)
    case s: ForeachStatement => execute(s, env)
    case s: DoStatement => execute(s, env)
    case _ => throw new RuntimeException(stmt + " unimplemented.")
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

    val sv = StringValue(e.toString.slice(1, e.toString.length - 1))

    sv.setLocation(path.toString diff rootPath.toString, currentLineNr)
    return (sv, env)
  }

  def evaluate(e: VarExpr, env: Environment): (OakValue, Environment) = {
    val value = env.lookup(e.toString)
    return value match {
      case ref: OakVariable => (OakHeap.extract(ref), env)
      case _ => (value, env)
    }
  }

  def evaluate(e: UnaryNotExpr, env: Environment): (OakValue, Environment) = {
    val p = evaluate(e.getExpr, env)._1
    return p match {

      // default case: e evaluates to a BooleanValue
      case e: BooleanValue => {
        (e.not, env)
      }

      // exceptional case: Found symbolic value -> return symbolic value
      case v2: SymbolicValue => (SymbolValue(e.toString, OakHeap.getIndex), env)

      case a: ArrayValue => {
        val b = if (a.array.size == 0) {
          false
        } else {
          true
        }
        (BooleanValue(b), env)
      }

      case _ => throw new RuntimeException("" + this.currentLineNr)
    }
  }

  def evaluate(ae: AbstractBinaryExpr, env: Environment): (OakValue, Environment) = {

    // Retrieve the operands e1, e2 of the binary expression
    val e1 = ae.getLeft
    val e2 = ae.getRight

    /** null */
    if (e1.isInstanceOf[LiteralNullExpr] || e2.isInstanceOf[LiteralNullExpr]) {
      return (SymbolValue(ae.toString, OakHeap.index), env)
    }

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
            case s2: SymbolicValue => {
              if (ae.isInstanceOf[BinaryOrExpr] && v1.v) {
                return (BooleanValue(true), env)
              }
              if (ae.isInstanceOf[BinaryAndExpr] && !v1.v) {
                return (BooleanValue(false), env)
              }
              (SymbolValue(ae.toString, OakHeap.index), env)
            }

            // exceptional cases: return symbolic value and track unresolved expression
            case v2: SymbolicValue => (SymbolValue(ae.toString, OakHeap.getIndex), env)
            case v2: NumericValue => (SymbolValue(ae.toString, OakHeap.getIndex), env)
            case v2: StringValue => (SymbolValue(ae.toString, OakHeap.getIndex), env)

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
            case v2: SymbolicValue => (SymbolValue(ae.toString, OakHeap.getIndex), env)
            case v2: BooleanValue => (SymbolValue(ae.toString, OakHeap.getIndex), env)
            case v2: StringValue => (SymbolValue(ae.toString, OakHeap.getIndex), env)

            case v2: ArrayValue => throw new UnexpectedTypeException(e2, "Can't compare array with " + v1.getClass + ".")
            case _ => throw new UnexpectedTypeException(e2, evaluate(e2, env)._1 + "")
          }
        }

        case v1: StringValue => {
          //TODO CONCAT?
          return (new SymbolValue(ae.toString, OakHeap.getIndex), env)
        }

        case v1: VarExpr => {
          return (evaluate(v1, env)._1, env) // TODO more cases
        }

        // exceptional case: Any binary expression, where e1 is symbolic -> return a symbolic value
        case v1: SymbolicValue => (SymbolValue(ae.toString, OakHeap.getIndex), env)

        case v1: BinaryAssignExpr => {
          throw new RuntimeException("FAILURE")
        }
        
        case _ => throw new UnexpectedTypeException(e1, " any type1 " + e1.getClass)
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

    logger.info("Call " + e + " " + args)
    
    /* If the function has already been called, i.e., its name
       * is contained in the call stack, we just return a symbol value
       * in order to avoid recursive function calls. */
    if (env.getCalls.contains(name)) {
      return (new SymbolValue(e.toString, OakHeap.getIndex), env)
    }

    /* If the function called refers to one implemented library function, such as
       * count($x) for arrays or concat/. for string literals, that implementation
       * will be used (instead). 
       * Each plugin implements its own library method, this.plugins is a map from 
       * the library function names to the actual plugin.
       * */
    if (getPlugins.contains(name)) {

      /* The library function plugin visits and evaluates the expression. */
      return (this.accept(getPlugin(name), args.toList, (path, currentLineNr), env), env)
    }

    /* Retrieve the function definition from the environment. If we fail
       * loading the function, we return with a symbol value, i.e., the called function
       * is either undefined or unimplemented. */
    val function = try {
      env.getFunction(name)
    } catch {
      case ex: Exception => return (SymbolValue(e.toString, OakHeap.getIndex()), env)
    }

    // Assert that the number of arguments in the function call and declaration match
    //assert(function.getArgs.length == args.length)

    /* CALL-BY-VALUE only */

    /**
     * Create a new (function) environment with pre-assigned arguments
     * TODO call-by-reference
     */
    val functionEnv = Environment.createFunctionEnvironment(env, name)
    (function.getArgs.slice(0, args.length) zip args).foreach {
      t =>
        {
          val functionVal = if (t._1.startsWith("&")) {
            println("if")
            // is reference arg
            try {
              env.getVariables.get(t._2.toString).get
            } catch {
              case nsee: NoSuchElementException => {
                throw new RuntimeException("Only variables can be passed by reference.")
              }
            }
          } else {
            println("else " + t._2)
            evaluate(t._2, env)._1
          }
          println(functionVal)
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
                throw new RuntimeException()
              }
            }
            val defaultValue = evaluate(default, env)._1
            functionEnv.update("$" + a.replace("&", ""), defaultValue)
          }
      }
    }

    val result = execute(function.getStatement, functionEnv)
    val resultEnv = result._2

    //write output during the function call to the parent environment
    resultEnv.getParent().receiveOutput(resultEnv.getOutput())

    val returnValue = try {
      resultEnv.lookup("$return")
    } catch {
      case e: Exception => null
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

    if (exx.toString.equals("$_POST") || exx.toString.equals("$_GET") || exx.toString.equals("$_SESSION") || exx.toString.equals("$_SERVER")) {
      return (SymbolValue(exx.toString, OakHeap.index), env)
    }

    val expr = evaluate(exx, env)._1
    val index = evaluate(Interpreter.accessField(arrayGet, "_index").asInstanceOf[Expr], env)._1
    expr match {
      case av: ArrayValue => {
        val value = av.get(index)
        return (value, env)
      }
      case s: SymbolicValue => {
        (SymbolValue(exx.toString, OakHeap.index), env)
      }
      case _ => {
        throw new RuntimeException(expr + " is not an array.")
      }
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
    System.err.println(env.lookup(objExpr.toString()), evaluate(objExpr, env)._1)
    val objEnv = Environment.createObjectEnvironment(env, env.lookup(objExpr.toString()).asInstanceOf[ObjectValue]) // assuming this is sth like $obj

    val method = env.lookup(objExpr.toString()).asInstanceOf[ObjectValue].getClassdef().getMethods(methodName)

    assert(method.args.size == args.size)
    (method.args zip args).foreach {
      case (name, expr) => objEnv.update("$" + name, evaluate(expr, env)._1)
    }

    val objectValBefore = env.lookup(objExpr.toString()).asInstanceOf[ObjectValue]

    val envRes = execute(method.statement, objEnv)._2

    println("-05")
    val arrayVal = envRes.lookup("$this").asInstanceOf[ArrayValue]
    val returnVal = try {
      if (method.returnsRef()) {
        envRes.getRef("$return")
      } else {
        envRes.lookup("$return")
      }
    } catch {
      case e: Exception => throw new RuntimeException()
    }

    arrayVal.getKeys.foreach {
      key => objectValBefore.set(key.asInstanceOf[StringValue].value.toString, arrayVal.get(key))
    }
    env.update(objExpr.toString(), objectValBefore)
    (returnVal, env)

  }

  def evaluate(b: ObjectNewExpr, env: Environment): (OakValue, Environment) = {
    val name = Interpreter.accessField(b, "_name").asInstanceOf[String]
    val args = Interpreter.accessField(b, "_args").asInstanceOf[Array[Expr]]

    val objectValue = try {

      val constructor = Environment.getClass(name).getConstructor(args.size) // match by number of args

      /** Create a new object Value */
      val obj = ObjectValue("Object Doe", Environment.getClass(name))
      val objEnv = Environment.createObjectEnvironment(env, obj)

      /* Execute constructor in the object environment and keep its variable $this:
     *  
     *  - assign all evaluated arg expressions to the constructor args
     *   */
      assert(constructor.args.size == args.size)
      (constructor.args zip args).foreach {
        case (name, expr) => objEnv.update("$" + name, evaluate(expr, env)._1)
      }

      val envConstructed = execute(constructor.statement, objEnv)._2

      println("-05")
      val thisArray = envConstructed.lookup("$this").asInstanceOf[ArrayValue]
      thisArray.getKeys.foreach {
        key => obj.set(key.asInstanceOf[StringValue].value.toString, thisArray.get(key))
      }
      obj
    } catch {
      case nsee: NoSuchElementException => {
        Environment.getClass(name).getDefaultObject()
      }
    }
    (objectValue, env)
  }

  def evaluate(t: UnarySuppressErrorExpr, env: Environment): (OakValue, Environment) = {
    evaluate(Interpreter.accessField(t, "_expr").asInstanceOf[Expr], env)
  }

  def evaluate(t: ThisFieldExpr, env: Environment): (OakValue, Environment) = {
    val fieldName = Interpreter.accessField(t, "_name").asInstanceOf[com.caucho.quercus.env.StringValue].toString()

    println("-06")
    val ref = env.lookup("$this").asInstanceOf[ArrayValue].getRef(StringValue(fieldName))
    (ref, env)
  }

  def evaluate(t: LiteralNullExpr, env: Environment): (OakValue, Environment) = {
    (NullValue("LiteralNullExpr"), env)
  }

  def evaluate(e: BinaryAssignExpr, env: Environment): (OakValue, Environment) = {

    // TODO Refactor variable Interpreter.access by reflection!
    val name = Interpreter.accessField(e, "_var").asInstanceOf[AbstractVarExpr]
    val expr = Interpreter.accessField(e, "_value").asInstanceOf[Expr]

    return name match {

      /* name is a variable */
      case n: VarExpr => {

        if (expr.isInstanceOf[BinaryAssignExpr]) {
          var names = Set(name.toString)
          var assignExpr = expr
          while (assignExpr.isInstanceOf[BinaryAssignExpr]) {
            val namez = Interpreter.accessField(assignExpr, "_var").asInstanceOf[AbstractVarExpr].toString
            names += namez
            assignExpr = Interpreter.accessField(assignExpr, "_value").asInstanceOf[Expr]
          }
          val value = evaluate(assignExpr, env)._1
          names.foreach { n => env.update(n, value) }
          return (null, env)
        }

        val value = evaluate(expr, env)._1
        val valueX = value match {
          case sv: StringValue => {
            sv.setLocation((path.toString diff rootPath.toString, currentLineNr))
            sv
          }
          case _ => {
            value
          }
        }
        env.update(name.toString, valueX)
        (null, env)
      }

      /**
       * Assignment to array field:
       *
       * $<arrayName>..[<Expr>] = <Expr>
       */
      case a: ArrayGetExpr => {

        val indexRaw = evaluate(Interpreter.accessField(a, "_index").asInstanceOf[Expr], env)._1
        val index = indexRaw match {
          case s: StringValue => {
            s.setLocation((path.toString diff rootPath.toString, currentLineNr))
            s
          }
          case _ => indexRaw
        }

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

          if (av.isInstanceOf[SymbolicValue]) {
            env.update(arrayValueName, SymbolValue(a.toString, OakHeap.index))
            return (null, env)
          }

          /* Assert that av is a array value. */
          assert(av.isInstanceOf[ArrayValue])

          /* Return array value*/

          println("-07")
          av.asInstanceOf[ArrayValue]
        } catch {
          case ex: VariableNotFoundException => {
            val a = new ArrayValue()
            env.update(arrayValueName, a)
            a
          }
        }

        val value = evaluate(expr, env)._1
        val valueX = value match {
          case sv: StringValue =>
            sv.setLocation((path.toString diff rootPath.toString, currentLineNr)); sv
          case _ => value
        }

        arrayValue.set(index, valueX)

        env.update(arrayValueName, arrayValue)
        return (null, env)
      }

      /**
       * Assignment to object field (inside method declararion):
       *  $this -> <fieldName> = <Expr>
       */
      case dis: ThisFieldExpr => {
        val fieldName = Interpreter.accessField(dis, "_name").asInstanceOf[com.caucho.quercus.env.StringValue]

        /* $this points to an array, hence we perform an assignment to $this[name] */
        println("-08")
        val thisValue = env.lookup("$this").asInstanceOf[ArrayValue]

        val value = evaluate(expr, env)._1
        val valueX = value match {
          case sv: StringValue =>
            sv.setLocation((path.toString diff rootPath.toString, currentLineNr)); sv
          case _ => value
        }

        thisValue.set(StringValue(fieldName.toString), valueX)

        env.update("$this", thisValue)

        (null, env)
      }

      /**
       * Assignment to object field:
       * $<objName> -> <fieldName> = <Expr>
       */
      case of: ObjectFieldExpr => {
        val objName = Interpreter.accessField(of, "_objExpr").asInstanceOf[Expr].toString()
        val obj = env.lookup(objName).asInstanceOf[ObjectValue]
        val fieldName = Interpreter.accessField(of, "_name").asInstanceOf[com.caucho.quercus.env.StringValue].toString()
        val ref = obj.fields.getRef(StringValue(fieldName))

        val value = evaluate(expr, env)._1
        val valueX = value match {
          case sv: StringValue =>
            sv.setLocation((path.toString diff rootPath.toString, currentLineNr)); sv
          case _ => value
        }

        OakHeap.insert(ref, valueX)

        (null, env)
      }

      case at: ArrayTailExpr => {
        val name = Interpreter.accessField(at, "_expr").asInstanceOf[Expr].toString

        val array = try {
          println("-09")
          env.lookup(name).asInstanceOf[ArrayValue]
        } catch {
          case t: Throwable => new ArrayValue()
        }

        // $name[] = <expr>
        expr match {
          case a: ArrayGetExpr => {
            val value = evaluate(expr, env)._1
            val index = evaluate(Interpreter.accessField(expr, "_index").asInstanceOf[Expr], env)._1

            array.set(index, value)
            env.update(name, array)
          }
          case _ => {
            env.update(name, evaluate(expr, env)._1)
          }
        }

        (null, env)
      }
      case _ => throw new RuntimeException(name.getClass + " unexpected " + expr.toString())
    }
  }

  def evaluate(e: LiteralLongExpr, env: Environment): (OakValue, Environment) = {
    val value = Interpreter.accessField(e, "_value").asInstanceOf[Long]
    (IntValue(value), env)
  }

  def evaluate(e: ConditionalExpr, env: Environment): (OakValue, Environment) = {
    val test = Interpreter.accessField(e, "_test").asInstanceOf[Expr]
    val testV = evaluate(test, env)._1
    val trueExpr = Interpreter.accessField(e, "_trueExpr").asInstanceOf[Expr]
    val falseExpr = Interpreter.accessField(e, "_falseExpr").asInstanceOf[Expr]

    if (testV.isInstanceOf[SymbolicValue]) {
      (Choice(test.toString, evaluate(trueExpr, env)._1, evaluate(falseExpr, env)._1), env)
    } else {
      testV match {
        case b: BooleanValue => {
          if (b.value) {
            (evaluate(trueExpr, env)._1, env)
          } else {
            (evaluate(falseExpr, env)._1, env)
          }
        }
        case _ => throw new RuntimeException("Expression did not evaluate to BooleanExpression.")
      }
    }

  }

  def evaluate(e: UnaryMinusExpr, env: Environment): (OakValue, Environment) = {
    val value = evaluate(Interpreter.accessField(e, "_expr").asInstanceOf[Expr], env)._1
    value match {
      case n: NumericValue => {
        (IntValue(-1) * n, env)
      }
      case _ => (SymbolValue(e.toString, OakHeap.index), env)
    }
  }

  def evaluate(e: FunIssetExpr, env: Environment): (OakValue, Environment) = {
    return try {
      val x = env.lookup(e.toString)
      (BooleanValue(true), env)
    } catch {
      case e: VariableNotFoundException => (BooleanValue(false), env)
    }
  }

  def evaluate(e: ConstExpr, env: Environment): (OakValue, Environment) = {
    if (constants.keySet contains e.toString) {
      val opt = constants.get(e.toString)
      return (opt.get, env)
    } else {
      logger.warn("Constant " + e.toString + " is not defined! " + currentLineNr)
      (StringValue(""), env)
    }
  }

  def evaluate(e: FunEmptyExpr, env: Environment): (OakValue, Environment) = {
    val value = Interpreter.accessField(e, "_value").asInstanceOf[Expr]
    val re = try {
      val empty = env.lookup(e.toString)
      if (empty == BooleanValue(false)) {
        (BooleanValue(true), env)
      } else {
        (BooleanValue(false), env)
      }
    } catch {
      case v: VariableNotFoundException => (BooleanValue(true), env)
    }
    return re
  }
  
  def evaluate(e: ToArrayExpr, env: Environment): (OakValue, Environment) = {
    val expr = Interpreter.accessField(e, "_expr").asInstanceOf[Expr]
    println(expr)
    (new ArrayValue(), env)
  }

  override def evaluate(e: Expr, env: Environment): (OakValue, Environment) = e match {
    case e: LiteralExpr => evaluate(e, env)
    case e: LiteralUnicodeExpr => evaluate(e, env)
    case e: VarExpr => evaluate(e, env)
    case e: UnaryNotExpr => evaluate(e, env)
    case e: UnaryMinusExpr => evaluate(e, env)
    case ae: AbstractBinaryExpr => evaluate(ae, env)
    case e: CallExpr => evaluate(e, env)
    case arrayExpr: FunArrayExpr => evaluate(arrayExpr, env)
    case arrayGet: ArrayGetExpr => evaluate(arrayGet, env)
    case b: BinaryAppendExpr => evaluate(b, env)
    case o: ObjectNewExpr => evaluate(o, env)
    case o: ObjectMethodExpr => evaluate(o, env)
    case t: ThisFieldExpr => evaluate(t, env)
    case n: LiteralNullExpr => evaluate(n, env)
    case a: BinaryAssignExpr => evaluate(a, env)
    case u: UnarySuppressErrorExpr => evaluate(u, env)
    case e: LiteralLongExpr => evaluate(e, env)
    case e: ConditionalExpr => evaluate(e, env)
    case e: FunIssetExpr => evaluate(e, env)
    case e: ConstExpr => evaluate(e, env)
    case e: FunEmptyExpr => evaluate(e, env)
    case e: ToArrayExpr => evaluate(e, env)
    case _ => throw new RuntimeException(e + " (" + e.getClass + ") not implemented.")

  }
}