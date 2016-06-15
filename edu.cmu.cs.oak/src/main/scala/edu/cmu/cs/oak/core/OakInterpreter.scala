package edu.cmu.cs.oak.core

import java.nio.file.Path
import java.nio.file.Paths

import scala.collection.immutable.Stack
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks.break
import scala.util.control.Breaks.breakable

import org.slf4j.LoggerFactory

import com.caucho.quercus.expr.AbstractBinaryExpr
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
import com.caucho.quercus.expr.BinaryInstanceOfExpr
import com.caucho.quercus.expr.BinaryLtExpr
import com.caucho.quercus.expr.BinaryModExpr
import com.caucho.quercus.expr.BinaryMulExpr
import com.caucho.quercus.expr.BinaryOrExpr
import com.caucho.quercus.expr.BinarySubExpr
import com.caucho.quercus.expr.CallExpr
import com.caucho.quercus.expr.ClassMethodExpr
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
import com.caucho.quercus.expr.LiteralExpr
import com.caucho.quercus.expr.LiteralLongExpr
import com.caucho.quercus.expr.LiteralNullExpr
import com.caucho.quercus.expr.LiteralUnicodeExpr
import com.caucho.quercus.expr.ObjectFieldExpr
import com.caucho.quercus.expr.ObjectMethodExpr
import com.caucho.quercus.expr.ObjectNewExpr
import com.caucho.quercus.expr.ThisFieldExpr
import com.caucho.quercus.expr.ThisFieldVarExpr
import com.caucho.quercus.expr.ThisMethodExpr
import com.caucho.quercus.expr.ToArrayExpr
import com.caucho.quercus.expr.ToLongExpr
import com.caucho.quercus.expr.UnaryMinusExpr
import com.caucho.quercus.expr.UnaryNotExpr
import com.caucho.quercus.expr.UnaryPostIncrementExpr
import com.caucho.quercus.expr.UnarySuppressErrorExpr
import com.caucho.quercus.expr.VarExpr
import com.caucho.quercus.expr.VarUnsetExpr
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
import com.caucho.quercus.statement.FunctionDefStatement
import com.caucho.quercus.statement.GlobalStatement
import com.caucho.quercus.statement.IfStatement
import com.caucho.quercus.statement.ReturnRefStatement
import com.caucho.quercus.statement.ReturnStatement
import com.caucho.quercus.statement.Statement
import com.caucho.quercus.statement.StaticStatement
import com.caucho.quercus.statement.SwitchStatement
import com.caucho.quercus.statement.TextStatement
import com.caucho.quercus.statement.TryStatement
import com.caucho.quercus.statement.WhileStatement

import edu.cmu.cs.oak.analysis.ASTVisitor
import edu.cmu.cs.oak.env.BranchEnv
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.env.FunctionEnv
import edu.cmu.cs.oak.env.ObjectEnv
import edu.cmu.cs.oak.env.SimpleEnv
import edu.cmu.cs.oak.env.heap.OakHeap
import edu.cmu.cs.oak.exceptions.UnexpectedTypeException
import edu.cmu.cs.oak.exceptions.VariableNotFoundException
import edu.cmu.cs.oak.lib.array.Count
import edu.cmu.cs.oak.lib.array.IsArray
import edu.cmu.cs.oak.lib.builtin.Define
import edu.cmu.cs.oak.lib.builtin.Defined
import edu.cmu.cs.oak.lib.builtin.DirName
import edu.cmu.cs.oak.lib.builtin.Print
import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.value.BooleanValue
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.ClassDef
import edu.cmu.cs.oak.value.DoubleValue
import edu.cmu.cs.oak.value.FunctionDef
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.NumericValue
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakValueRepeatSequence
import edu.cmu.cs.oak.value.OakValueSequence
import edu.cmu.cs.oak.value.OakVariable
import edu.cmu.cs.oak.value.ObjectValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.value.SymbolicValue

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
    var env: Environment = new SimpleEnv(null, Stack[String](), "true")
    OakHeap.clear()

    env.update("$_POST", SymbolValue("$_POST", OakHeap.index))
    env.update("$_GET", SymbolValue("$_GET", OakHeap.index))

    env.update("$_SERVER", SymbolValue("", OakHeap.index))
    Environment.addToGlobal("$_SERVER", env.lookup("$_SERVER"))

    // http://php.net/manual/en/language.exceptions.extending.php
    env = execute(engine.loadFromFile(Paths.get(getClass.getResource("/Exception.php").toURI())), env)._2
    env = execute(engine.loadFromFile(Paths.get(getClass.getResource("/COM.php").toURI())), env)._2
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
    val expr = stmt._expr

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

    return ("OK", env)
  }

  /*
   * Statement of the form
   * <Var> = <Expr>;
   */
  def execute(s: ExprStatement, env: Environment): (String, Environment) = {

    currentLineNr = ASTVisitor.getStatementLineNr(s)

    val e = s._expr

    e match {

      case exit: FunExitExpr => {
        ("OK", env)
      }

      case u: VarUnsetExpr => {
        val varToUnset = u._var
        varToUnset match {
          case varex: VarExpr => {
            env.unset(varToUnset.toString)
          }
          case ag: ArrayGetExpr => {
            val expr = ag._expr
            if (!env.lookup(expr.toString).isInstanceOf[SymbolValue]) {
              val index = evaluate(ag._index, env)._1
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
        val name = e._var
        val value = e._value

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
            val exx = aget._expr
            val expr = evaluate(exx, env)._1
            val index = evaluate(aget._index, env)._1

            assert(expr.isInstanceOf[ArrayValue])

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
        val expr = include._expr
        val includePath = Paths.get(this.rootPath + "/" + evaluate(expr, env)._1.toString.replace("\"", ""))
        val program = (new OakEngine).loadFromFile(includePath)

        this.includes.push(includePath)

        //graphListener.addEdge(path.toString.substring(path.toString.lastIndexOf('/') + 1, path.toString.length()), include.toString)
        this.path = includePath
        logger.info("Including script " + includePath)
        val res = this.execute(program, env)._2
        //logger.info("Resuming " + oldURL)

        this.includes.pop()
        ("OK", res)
      }

      case includeOnce: FunIncludeOnceExpr => {
        val oldURL = this.path
        val expr = includeOnce._expr
        val includePath = Paths.get(this.rootPath + "/" + evaluate(expr, env)._1.toString.replace("\"", ""))
        val program = (new OakEngine).loadFromFile(includePath)

        if (this.includes contains includePath) {
          return ("NOT OK", env)
        }
        this.includes.push(includePath)

        //graphListener.addEdge(path.toString.substring(path.toString.lastIndexOf('/') + 1, path.toString.length()), includeOnce.toString)
        this.path = includePath
        logger.info("Including script " + includePath)
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
        val e = p._expr
        val i = p._incr

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
        val value = e._value

        // list to assign array elements to
        val listHead = e._listHead
        val vars = listHead._varList

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

      case cme: ClassMethodExpr => {

        // ClassName::ClassName(<args>) called, but not a 
        if (cme._className equals cme._methodName.toString()) {

          val classDef = Environment.getClass(cme._className)
          val constructor = classDef.getConstructor(cme._args.size)

          val env_ = execute(constructor.statement, env)._2
          (null, env_)
        }

        //println(cme._args.size)
        //System.exit(2)
        (null, env)
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
    val test = s._test
    val trueBlock = s._trueBlock
    val falseBlock = s._falseBlock

    /* Execute the statement either concrete (-> either one branch)
     * or symbolic by executing both branches and merging them
     * together afterwards. */
    var testV = evaluate(test, env)._1
    return testV match {

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

      case n: NumericValue => {
        if (n.toString equals "1") {
          return execute(trueBlock, env)
        } else {
          return execute(falseBlock, env)
        }
      }

      /* Execute both branches (-> symbolically) and merge environments */
      case _ => {

        // TODO use real constraints
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
        return ("OK", res)
      }

      //case _ => throw new RuntimeException("Evaluation of branch condition " + test + " failed. It was " + testV + ".")
    }
  }

  def execute(s: WhileStatement, env: Environment): (String, Environment) = {

    currentLineNr = ASTVisitor.getStatementLineNr(s)

    /* Retrieve the condition and both statements from the WhileStatement AST node.
     * */
    val test = s._test

    val env_ = evaluate(test, env)._2

    val block = s._block

    // TODO use real constraints
    val envs = Environment.fork(env_, test.toString)
    val res1 = execute(block, envs._1)._2.asInstanceOf[BranchEnv]

    val res = res1 join envs._2

    res.prependOutput(List(OakValueRepeatSequence(env.getOutput)))
    return ("OK", res)
  }

  /**
   * Statement of the form
   * return <-Expr>;
   */
  def execute(s: ReturnStatement, env: Environment): (String, Environment) = {
    currentLineNr = ASTVisitor.getStatementLineNr(s)

    val expr = s._expr
    val v = evaluate(expr, env)

    env.update("$return", evaluate(expr, env)._1)
    return ("OK", env)
  }

  /**
   * Adds a class definition to the environment.
   */
  def execute(s: ClassDefStatement, env: Environment): (String, Environment) = {

    currentLineNr = ASTVisitor.getStatementLineNr(s)

    val interpreted = s._cl

    // TODO class declaration 
    val name = interpreted.getName
    val fields = interpreted._fieldMap

    val methods = interpreted._functionMap

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
    var classFieldNames = classFieldNamez.map { name => name.toString() }

    /* Retrieve all parent classes */
    if (interpreted._parentName != null) {
      val parentClassDef = Environment.getClass(interpreted._parentName)

      classFieldNames ++= parentClassDef.getFields()
      parentClassDef.methods.foreach {
        case (name, fdef) => classMethodDefMap += (name -> fdef)
      }
    } else {
    }

    val parent = if (interpreted._parentName != null) interpreted._parentName else ""
    val classDef = ClassDef(name, classFieldNames, classMethodDefMap, parent)

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

    val u = s._expr

    val returnRef = u match {

      case t: ThisFieldExpr => {
        val fieldName = t._name.toString()

        val thisArray = env.lookup("$this").asInstanceOf[ArrayValue]
        thisArray.getRef(StringValue(fieldName))
      }
      case v: VarExpr => {
        env.getVariables().get(v.toString).get
      }
      case a: ArrayGetExpr => {
        val array = env.lookup(a._expr.toString()).asInstanceOf[ArrayValue]
        val index = evaluate(a._index, env)._1
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

    val v = s._value
    val value = evaluate(v, env)._1
    val cases = s._cases
    val blocks = s._blocks
    val default = s._defaultBlock

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
      val res = Environment.joinN(env, envs_, defaultEnv)
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

    /* Retrieve the condition and both statements from the ForStatement AST node. */
    val test = s._test
    val init = s._init

    val block = s._block

    // TODO use real constraints
    val envs = Environment.fork(env, test.toString)

    val initEnv = evaluate(init, envs._1)._2
    val res1 = execute(block, initEnv)._2.asInstanceOf[BranchEnv]
    val res = res1 join envs._2

    res.prependOutput(List(OakValueRepeatSequence(env.getOutput)))
    return ("OK", res)

  }

  def execute(s: TextStatement, env: Environment): (String, Environment) = {
    val value = s._value
    ("OK", env)
  }

  def execute(s: GlobalStatement, env: Environment): (String, Environment) = {
    val value = s._var
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
    val uniqueStaticName = s._uniqueStaticName.toString()
    val variable = s._var
    val initValue = evaluate(s._initValue, env)._1

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

    val objExpr = s._objExpr

    val key = s._key
    val value = s._value

    /** Loop body */
    val block = s._block

    // TODO use real constraints
    val envs = Environment.fork(env, "count(" + objExpr + ") > 0")
    envs._1.update(value.toString(), SymbolValue("foreach::", OakHeap.index))
    val res1 = execute(block, envs._1)._2.asInstanceOf[BranchEnv]

    val res = res1 join envs._2
    res.prependOutput(List(OakValueRepeatSequence(env.getOutput)))

    ("OK", res)
  }

  def execute(s: DoStatement, env: Environment): (String, Environment) = {

    currentLineNr = ASTVisitor.getStatementLineNr(s)

    /* Retrieve the condition and both statements from the WhileStatement AST node.
     * TODO Refactor variable Interpreter.access by reflection! */
    val test = s._test

    val env_ = evaluate(test, env)._2

    val block = s._block

    // TODO use real constraints
    val envs = Environment.fork(env_, test.toString)
    val res1 = execute(block, envs._1)._2.asInstanceOf[BranchEnv]
    val res = res1 join envs._2

    res.prependOutput(List(OakValueRepeatSequence(env.getOutput)))

    return ("OK", res)
  }

  def execute(s: FunctionDefStatement, env: Environment): (String, Environment) = {
    val function = s._fun
    env.addFunction(Environment.defineFunction(function))
    ("OK", env)
  }

  def execute(s: TryStatement, env: Environment): (String, Environment) = {
    val block = s._block
    val env_ = execute(block, env)._2
    ("OK", env_)
  }

  override def execute(stmt: Statement, env: Environment): (String, Environment) = {
    println(stmt)
    stmt match {
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
      case s: FunctionDefStatement => execute(s, env)
      case s: TryStatement => execute(s, env)
      case _ => throw new RuntimeException(stmt + " unimplemented.")
    }
  }

  def evaluate(e: LiteralExpr, env: Environment): (OakValue, Environment) = {
    val value = e._value

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

      case _ => (SymbolValue(e.toString, OakHeap.index), env)
    }
  }

  def evaluate(ae: AbstractBinaryExpr, envi: Environment): (OakValue, Environment) = {

    var env = envi

    // Retrieve the operands e1, e2 of the binary expression
    val e1 = ae.getLeft
    val e2 = ae.getRight

    /** null */
    if (e1.isInstanceOf[LiteralNullExpr] || e2.isInstanceOf[LiteralNullExpr]) {
      return (SymbolValue(ae.toString, OakHeap.index), env)
    }

    return try {

      if (e1.isInstanceOf[BinaryAssignExpr]) {
        env = evaluate(e1, env)._2

        if (e2.isInstanceOf[BinaryAssignExpr]) {
          env = evaluate(e2, env)._2
        }
        (null, env)

      } else {
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
              case v2: NumericValue => (SymbolValue(ae.toString, OakHeap.getIndex), env)
              case v2: StringValue => (SymbolValue(ae.toString, OakHeap.getIndex), env)

              case _ => throw new RuntimeException()
            }
          }

          // expecting a arithmetic expression, where e1 AND e2 are NumericValues
          case v1: NumericValue => {

            val bener = evaluate(e2, env)._1
            bener match {

              // default case: e1 AND e2 are NumericValues
              case v2: NumericValue => {
                return (ae match {
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
              case v2: SymbolicValue => {
                (SymbolValue(ae.toString, OakHeap.getIndex), env)
              }
              case v2: BooleanValue => {
                (SymbolValue(ae.toString, OakHeap.getIndex), env)
              }
              case v2: StringValue => {
                (SymbolValue(ae.toString, OakHeap.getIndex), env)
              }

              case v2: ArrayValue => {
                throw new UnexpectedTypeException(e2, "Can't compare array with " + v1.getClass + ".")
              }
              case _ => {
                throw new UnexpectedTypeException(e2, evaluate(e2, env)._1 + "")
              }
            }
          }

          case v1: StringValue => {
            return (new SymbolValue(ae.toString, OakHeap.getIndex), env)
          }

          case v1: VarExpr => {
            return (evaluate(v1, env)._1, env) // TODO more cases
          }

          // exceptional case: Any binary expression, where e1 is symbolic -> return a symbolic value
          case v1: SymbolicValue => {
            (SymbolValue(ae.toString, OakHeap.getIndex), env)
          }

          case v1: BinaryAssignExpr => {
            throw new RuntimeException("FAILURE")
          }

          case _ => {
            throw new UnexpectedTypeException(e1, " any type1 " + e1.getClass)
          }
        }
      }
    } catch {
      case e: Exception => throw new RuntimeException(e)
    }
  }

  def evaluate(e: CallExpr, env: Environment): (OakValue, Environment) = {

    val name = e._name.toString()

    /* Retrieve the function call arguments. */
    var args = ListBuffer[Expr]()
    e._args.foreach {
      o => args += o
    }

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
     */
    var functionEnv = Environment.createFunctionEnvironment(env, name)
    functionEnv = prepareFunctionOrMethod(function, env, functionEnv, args.toList).asInstanceOf[FunctionEnv]

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
    val valueExprList = arrayExpr._values
    val array = new ArrayValue()
    (valueExprList zipWithIndex).foreach {
      case (v: Expr, i: Int) => array.set(IntValue(i), evaluate(v, env)._1)
    }
    return (array, env)
  }

  def evaluate(arrayGet: ArrayGetExpr, env: Environment): (OakValue, Environment) = {
    val exx = arrayGet._expr

    if (exx.toString.equals("$_POST") || exx.toString.equals("$_GET") || exx.toString.equals("$_SESSION") || exx.toString.equals("$_SERVER")) {
      return (SymbolValue(exx.toString, OakHeap.index), env)
    }

    val expr = evaluate(exx, env)._1
    val index = evaluate(arrayGet._index, env)._1
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

    val value = new OakValueSequence(expressions.toList.map { e => evaluate(e, env)._1 })

    return (value, env)
  }

  /*
  * $objExpr->methodName(args...)
  * 
  */
  def evaluate(e: ObjectMethodExpr, env: Environment): (OakValue, Environment) = {
    val objExpr = e._objExpr
    val methodName = e._methodName.toString()
    val args = e._args

    /*
     * If objExpr is "$this" [and, for sure, the current 
     * environment is an ObjectEnv], we keep the current
     * environment instead of creating a new one. 
     * 
     * OR
     * 
     * Two parent environments are 
     */
    val OBJ_IS_THIS = ((objExpr.toString equals "$this") && ((env.isInstanceOf[ObjectEnv]) || (env.getParents.filter { x => x.isInstanceOf[ObjectEnv] }.size > 0)))

    try {
      if (OBJ_IS_THIS) {
        var objEnv = if (env.isInstanceOf[ObjectEnv]) {
          env.asInstanceOf[ObjectEnv] // copy
        } else {
          val p = env.getParents.find(_.isInstanceOf[ObjectEnv]).get.asInstanceOf[ObjectEnv]
          var o = new ObjectEnv(env.getParent(), env.getCalls(), env.getConstraint(), p.obj)
          /*
         env.variables.keySet.foreach {
           key => o.update(key, env.lookup(key))
         }
         */
          //o = prepareFunctionOrMethod(, env, o, args)
          o
        }
        val method = objEnv.obj.getClassdef.getMethods(methodName)
        objEnv = prepareFunctionOrMethod(method, env, objEnv, args.toList).asInstanceOf[ObjectEnv]

        //assert(method.args.size == args.size)
        val objectValBefore = objEnv.obj
        val envRes = execute(method.statement, objEnv)._2

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
        env.update(objExpr.toString(), objectValBefore.fields)
        (returnVal, env)

      } else {

        /*
       * Create new Environment
       */

        println(objExpr.toString())
        var objEnv = Environment.createObjectEnvironment(env, env.lookup(objExpr.toString()).asInstanceOf[ObjectValue]) // assuming this is sth like $obj

        val method = env.lookup(objExpr.toString()).asInstanceOf[ObjectValue].getClassdef().getMethods(methodName)

        objEnv = prepareFunctionOrMethod(method, env, objEnv, args.toList).asInstanceOf[ObjectEnv]
        //assert(method.args.size == args.size)
        /*(method.args zip args).foreach {
          case (name, expr) => objEnv.update("$" + name, evaluate(expr, env)._1)
        }*/

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
          case e: Exception => null
        }

        arrayVal.getKeys.foreach {
          key => objectValBefore.set(key.asInstanceOf[StringValue].value.toString, arrayVal.get(key))
        }
        env.update(objExpr.toString(), objectValBefore)
        (returnVal, env)
      }
    } catch {
      case _ => (SymbolValue(e.toString, OakHeap.index), env)
    }
  }

  def evaluate(b: ObjectNewExpr, env: Environment): (OakValue, Environment) = {
    val name = b._name
    val args = b._args

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
    evaluate(t._expr, env)
  }

  def evaluate(t: ThisFieldExpr, env: Environment): (OakValue, Environment) = {
    val fieldName = t._name.toString()

    val ref = env.lookup("$this").asInstanceOf[ArrayValue].getRef(StringValue(fieldName))
    val value = OakHeap.extract(ref)
    (value, env)
  }

  def evaluate(t: LiteralNullExpr, env: Environment): (OakValue, Environment) = {
    (NullValue("LiteralNullExpr"), env)
  }

  def evaluate(e: BinaryAssignExpr, env: Environment): (OakValue, Environment) = {

    val name = e._var
    val expr = e._value

    return name match {

      /* name is a variable */
      case n: VarExpr => {

        if (expr.isInstanceOf[BinaryAssignExpr]) {
          var names = Set(name.toString)
          var assignExpr = expr
          while (assignExpr.isInstanceOf[BinaryAssignExpr]) {
            val o = assignExpr.asInstanceOf[BinaryAssignExpr]
            val namez = o._var.toString
            names += namez
            assignExpr = o._value
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

        val indexRaw = evaluate(a._index, env)._1
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
       * Assignment to object field (inside method declaration):
       *  $this -> <fieldName> = <Expr>
       */
      case dis: ThisFieldExpr => {
        val fieldName = dis._name

        /* $this points to an array, hence we perform an assignment to $this[name] */
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
        val objName = of._objExpr.toString()
        val obj = env.lookup(objName).asInstanceOf[ObjectValue]
        val fieldName = of._name.toString()

        // 
        val ref = if (obj.fields.getKeys contains fieldName) {
          obj.fields.getRef(StringValue(fieldName))
        } else {
          OakVariable(fieldName + OakHeap.index)
        }

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
        val name = at._expr.toString

        val array = try {
          env.lookup(name).asInstanceOf[ArrayValue]
        } catch {
          case t: Throwable => new ArrayValue()
        }

        // $name[] = <expr>
        expr match {
          case a: ArrayGetExpr => {
            val value = evaluate(expr, env)._1
            val index = evaluate(a._index, env)._1

            array.set(index, value)
            env.update(name, array)
          }
          case _ => {
            env.update(name, evaluate(expr, env)._1)
          }
        }
        (null, env)
      }
      case tfve: ThisFieldVarExpr => {
        val fieldName = StringValue(tfve._nameExpr.toString)
        val thisArray = env.lookup("$this").asInstanceOf[ArrayValue]
        thisArray.set(fieldName, evaluate(expr, env)._1)
        env.update("$this", thisArray)
        (null, env)
      }
      case _ => throw new RuntimeException(name.getClass + " unexpected " + expr.toString())
    }
  }

  def evaluate(e: LiteralLongExpr, env: Environment): (OakValue, Environment) = {
    val value = e._value
    (IntValue(value), env)
  }

  def evaluate(e: ConditionalExpr, env: Environment): (OakValue, Environment) = {
    val test = e._test
    val testV = evaluate(test, env)._1
    val trueExpr = e._trueExpr
    val falseExpr = e._falseExpr

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
    val value = evaluate(e._expr, env)._1
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
    val value = e._value
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
    val expr = e._expr
    (new ArrayValue(), env)
  }

  def evaluate(cme: ClassMethodExpr, env: Environment): (OakValue, Environment) = {
    // ClassName::ClassName(<args>) called, but not a 
    if (cme._className equals cme._methodName.toString()) {

      val classDef = Environment.getClass(cme._className)
      val constructor = classDef.getConstructor(cme._args.size)

      val env_ = execute(constructor.statement, env)._2
      (null, env_)
    } else {
      val classDef = Environment.getClass(cme._className)
      val method = classDef.getMethods(cme._methodName.toString)
      val cmEnv = Environment.createFunctionEnvironment(env, cme._className + "::" + cme._methodName.toString())
      val args = cme._args

      //default stuff
      (method.args zip args).foreach {
        case (name, expr) => {
          cmEnv.update(name, evaluate(expr, env)._1)
        }
      }

      val resEnv = execute(method.statement, cmEnv)._2
      val returnValue = try {
        resEnv.lookup("$return")
      } catch {
        case e: VariableNotFoundException => null
      }
      return (returnValue, env)
    }

    //println(cme._args.size)
    //System.exit(2)
    (null, env)
  }

  def evaluate(e: ThisMethodExpr, env: Environment): (OakValue, Environment) = {
    (SymbolValue("", OakHeap.index), env)
  }

  def evaluate(e: ToLongExpr, env: Environment): (OakValue, Environment) = {
    val expr = e._expr
    (SymbolValue(expr.toString, OakHeap.index), env)
  }

  def evaluate(e: ObjectFieldExpr, env: Environment): (OakValue, Environment) = {
    // $this->
    val obj = evaluate(e._objExpr, env)._1

    val value = if (obj.isInstanceOf[ObjectValue]) {
      obj.asInstanceOf[ObjectValue].get(e._name.toString)
    } else {
      SymbolValue(e.toString, OakHeap.index)
    }
    (value, env)
  }

  def evaluate(e: ThisFieldVarExpr, env: Environment): (OakValue, Environment) = {
    val thisV = env.lookup("$this").asInstanceOf[ArrayValue]
    val t = thisV.get(StringValue(e._nameExpr.toString))
    (t, env)
  }

  def evaluate(e: BinaryInstanceOfExpr, env: Environment): (OakValue, Environment) = {
    (SymbolValue(e.toString, OakHeap.index), env)
  }

  override def evaluate(e: Expr, env: Environment): (OakValue, Environment) = {
    println(e)
    e match {

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

      case e: ThisMethodExpr => evaluate(e, env)
      case o: ObjectMethodExpr => evaluate(o, env)

      case t: ThisFieldExpr => evaluate(t, env)
      case n: LiteralNullExpr => evaluate(n, env)
      case a: BinaryAssignExpr => evaluate(a, env)
      case u: UnarySuppressErrorExpr => evaluate(u, env)
      case e: LiteralLongExpr => evaluate(e, env)
      case ce: ConditionalExpr => evaluate(ce, env)
      case e: FunIssetExpr => evaluate(e, env)
      case e: ConstExpr => evaluate(e, env)
      case e: FunEmptyExpr => evaluate(e, env)
      case e: ToArrayExpr => evaluate(e, env)
      case e: ClassMethodExpr => evaluate(e, env)
      case e: ToLongExpr => evaluate(e, env)
      case e: ObjectFieldExpr => evaluate(e, env)
      case e: BinaryInstanceOfExpr => evaluate(e, env)

      case _ => throw new RuntimeException(e + " (" + e.getClass + ") not implemented.")

    }
  }
}