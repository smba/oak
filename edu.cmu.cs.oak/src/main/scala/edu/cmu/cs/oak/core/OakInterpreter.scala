package edu.cmu.cs.oak.core

import java.nio.file.Path
import java.nio.file.Paths

import scala.annotation.elidable
import scala.annotation.elidable.ASSERTION
import scala.collection.immutable.Stack
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks.break
import scala.util.control.Breaks.breakable

import org.slf4j.LoggerFactory

import com.caucho.quercus.expr.AbstractBinaryExpr
import com.caucho.quercus.expr.ArrayGetExpr
import com.caucho.quercus.expr.ArrayTailExpr
import com.caucho.quercus.expr.BinaryAppendExpr
import com.caucho.quercus.expr.BinaryAssignExpr
import com.caucho.quercus.expr.BinaryAssignListExpr
import com.caucho.quercus.expr.BinaryAssignRefExpr
import com.caucho.quercus.expr.BinaryInstanceOfExpr
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
import com.caucho.quercus.expr.ThisExpr
import com.caucho.quercus.expr.ThisFieldExpr
import com.caucho.quercus.expr.ThisFieldVarExpr
import com.caucho.quercus.expr.ThisMethodExpr
import com.caucho.quercus.expr.ToArrayExpr
import com.caucho.quercus.expr.ToBooleanExpr
import com.caucho.quercus.expr.ToLongExpr
import com.caucho.quercus.expr.ToStringExpr
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

import edu.cmu.cs.oak.env.BranchEnv
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.env.ObjectEnv
import edu.cmu.cs.oak.env.heap.OakHeap
import edu.cmu.cs.oak.exceptions.VariableNotFoundException
import edu.cmu.cs.oak.lib.array.Count
import edu.cmu.cs.oak.lib.array.IsArray
import edu.cmu.cs.oak.lib.builtin.Define
import edu.cmu.cs.oak.lib.builtin.Defined
import edu.cmu.cs.oak.lib.builtin.DirName
import edu.cmu.cs.oak.lib.builtin.IsSet
import edu.cmu.cs.oak.lib.builtin.Print
import edu.cmu.cs.oak.nodes.DNode
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
import edu.cmu.cs.oak.value.OakValueSequence
import edu.cmu.cs.oak.value.OakVariable
import edu.cmu.cs.oak.value.ObjectValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.value.SymbolicValue
import edu.cmu.cs.oak.exceptions.VariableNotFoundException
import java.io.FileNotFoundException
import com.caucho.quercus.expr.AbstractUnaryExpr
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.NullValue
import edu.cmu.cs.oak.value.DoubleValue

class OakInterpreter extends Interpreter with InterpreterPluginProvider {

  this.loadPlugin(new Count)
  this.loadPlugin(new Print)
  this.loadPlugin(new DirName)
  this.loadPlugin(new Define)
  this.loadPlugin(new Defined)
  this.loadPlugin(new IsArray)
  this.loadPlugin(new IsSet)

  def execute(path: Path): (ControlCode.Value, Environment) = {

    // The current script is located at this.path
    this.path = path

    // The root directory is path.getParent()
    this.rootPath = path.getParent()

    val engine = new OakEngine()
    val program = engine.loadFromFile(path)
    var env: Environment = new Environment(null, Stack[String](), "true")
    
    OakHeap.clear()

    env.update("$_POST", SymbolValue("$_POST", OakHeap.index, SymbolFlag.BUILTIN_VALUE))
    env.update("$_GET", SymbolValue("$_GET", OakHeap.index, SymbolFlag.BUILTIN_VALUE))

    env.addToGlobal("$_SERVER")
    env.update("$_SERVER", SymbolValue("$_SERVER", OakHeap.index, SymbolFlag.BUILTIN_VALUE))

    execute(engine.loadFromFile(Paths.get(getClass.getResource("/Exception.php").toURI())), env)
    execute(engine.loadFromFile(Paths.get(getClass.getResource("/COM.php").toURI())), env)
    execute(program, env)
    
    (ControlCode.OK, env)
  }

  private def execute(program: QuercusProgram, env: Environment): ControlCode.Value = {

    /** Write all function definitions to the heap */
    val funcIterator = program.getFunctionList.iterator
    while (funcIterator.hasNext) {
      val func = env.defineFunction(funcIterator.next())

      // Add function to the global environment
      env.addFunction(func)
    }
    execute(program.getStatement, env)
    return ControlCode.OK
  }

  /*
   * Statement of the form
   * <Statement>; <Statement>; ...
   */
  private def executeBlockStatement(stmt: BlockStatement, env: Environment): ControlCode.Value = {

    //TODO remove imperative hack
    // stmt.getStatements.takeWhile(!_.isInstanceOf[BreakStatement]).fold(env)(execute(_, _)._2) 
    breakable {
      stmt.getStatements.foreach {
        s =>
          {
            if (s.isInstanceOf[BreakStatement]) {
              break
            } else {
              execute(s, env)
            }
          }
      }
    }
    return ControlCode.OK
  }

  /*
   * Statement of the form
   * echo <Expr>;
   */
  private def executeEchoStatement(stmt: EchoStatement, env: Environment): ControlCode.Value = {
    val expr = stmt._expr
    val value = evaluate(expr, env)
    val valueX = value match {
      case sv: StringValue => {
        sv
      }
      case a: ArrayValue => {
        throw new RuntimeException("Can't echo an entire array.")
      }
      case _ => value
    }
    env.addOutput(DNode.createDNode(valueX))
    return ControlCode.OK
  }

  /*
   * Statement of the form
   * <Var> = <Expr>;
   */
  private def executeExprStatement(s: ExprStatement, env: Environment): ControlCode.Value = {
    val e = s._expr
    e match {
      case exit: FunExitExpr => {
        ControlCode.OK
      }
      case u: VarUnsetExpr => {
        val varToUnset = u._var
        varToUnset match {
          case varex: VarExpr => {
            env.unset(varToUnset.toString)
          }
          case ag: ArrayGetExpr => {
            val expr = ag._expr
            /*if (!env.lookup(expr.toString).isInstanceOf[SymbolValue]) {
              val index = evaluate(ag._index, env)._1
              //env.unsetArrayElement(expr.toString, index)
            }*/
          }
          case ofe: ObjectFieldExpr => {
            //TODO simplement?
          }
        }
        ControlCode.OK
      }

      /**
       * Assignment $a = <Expr>;
       */
      case b: BinaryAssignExpr => {
        val value = evaluate(b, env)
        if (value.isInstanceOf[StringValue]) {
          val svalue = value.asInstanceOf[StringValue]
          svalue.setLocation(s._location)
        }
        ControlCode.OK
      }

      /**
       * TODO Seiteneffekte des Ausdruckes!
       */
      case c: CallExpr => {
        val result = evaluate(c, env)
        //assert(!(result._2.getOutput() eq env.output))
        //env.addOutput( result._2.getOutput() )
        return ControlCode.OK
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
            val expr = evaluate(exx, env)
            val index = evaluate(aget._index, env)

            if (!expr.isInstanceOf[ArrayValue]) {
              return ControlCode.ERROR
            }
            val ref = expr.asInstanceOf[ArrayValue].getRef(index)
            ref
          }

          /*
           * Since we are inside a BinaryAssignRefExpr, we assume that the returned value 
           * actually is a OakVariable/reference
           */
          case ome: ObjectMethodExpr => {
            val envi = evaluate(ome, env) // return value
            val ref = envi.asInstanceOf[OakVariable]
            ref
          }
          case _ => throw new RuntimeException(value + " (" + value.getClass + ") is unimplemented.")
        }

        /* Update */
        env.setRef(name.toString, oakVariable)
        return ControlCode.OK
      }

      case o: ObjectMethodExpr => {
        evaluate(o, env)
        ControlCode.OK
      }

      case d: FunDieExpr => {
        ControlCode.OK
      }

      case include: FunIncludeExpr => {
        val oldURL = this.path
        val expr = include._expr
        if (!(evaluate(expr, env).isInstanceOf[SymbolicValue])) {
          val includePath = getInlcudePath(expr, env)
          val program = try {
            (new OakEngine).loadFromFile(includePath)
          } catch {
            case fnfe: FileNotFoundException => {
              this.logger.error(includePath + " could not be found!")
              return ControlCode.ERROR
            }
          }
          this.includes.push(includePath)
          //graphListener.addEdge(path.toString.substring(path.toString.lastIndexOf('/') + 1, path.toString.length()), include.toString)
          this.path = includePath
          //logger.info("Including " + includePath)
          execute(program, env)

          this.includes.pop
          this.path = oldURL
          //logger.info("Resuming " + this.path)
          ControlCode.OK
        } else {
          ControlCode.ERROR
        }
      }

      case includeOnce: FunIncludeOnceExpr => {
        val oldURL = this.path
        val expr = includeOnce._expr
        if (!(evaluate(expr, env).isInstanceOf[SymbolicValue])) {
          val includePath = getInlcudePath(expr, env)
          val program = (new OakEngine).loadFromFile(includePath)
          if (this.includes contains includePath) {
            return ControlCode.ERROR
          }
          this.includes.push(includePath)
          //graphListener.addEdge(path.toString.substring(path.toString.lastIndexOf('/') + 1, path.toString.length()), includeOnce.toString)
          this.path = includePath
          //logger.info("Including " + includePath)
          execute(program, env)
          this.path = oldURL
          this.includes.pop
          //logger.info("Resuming " + this.path)
          ControlCode.OK
        } else {
          logger.error("ERROR")
          ControlCode.ERROR
        }
      }

      case e: AbstractBinaryExpr => {
        ControlCode.OK
      }

      case p: UnaryPostIncrementExpr => {
        val e = p._expr
        val i = p._incr
        val result = evaluate(e, env) match {
          case a: SymbolicValue => SymbolValue(p.toString, OakHeap.index, SymbolFlag.AMBIGUOUS_VALUE)
          case b: NumericValue => {
            b + IntValue(i)
          }
          case _ => throw new RuntimeException("D")
        }
        env.update(e.toString, result)
        ControlCode.OK
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
        evaluate(value, env) match {
          case a: ArrayValue => {
            assert(a.array.size == vars.size)
            (vars zipWithIndex).foreach {
              case (v, av) => env.update(v.toString, env.heap.extract(a.array.get(IntValue(av)).get))
            }
          }
          case s: SymbolicValue => {
            vars.foreach {
              v => env.update(v.toString, SymbolValue(v.toString, OakHeap.index, SymbolFlag.AMBIGUOUS_VALUE))
            }
          }
        }
        ControlCode.OK
      }

      case cme: ClassMethodExpr => {

        // ClassName::ClassName(<args>) called, but not a 
        if (cme._className equals cme._methodName.toString()) {
          val classDef = env.getClass(cme._className)
          val constructor = classDef.getConstructor(cme._args.size)
          execute(constructor.statement, env)
          null
        }
        null
      }
      case e: UnarySuppressErrorExpr => {
        evaluate(e, env)
        ControlCode.OK
      }
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
  private def executeIfStatement(s: IfStatement, env: Environment): ControlCode.Value = {

    /* Retrieve the condition and both statements 
     * from the IfStatement AST node via reflection. */
    val condition = s._test.toString()
    val test = evaluate(s._test, env)
    val trueBlock = s._trueBlock
    val falseBlock = s._falseBlock

    /* Execute the statements symbolically by executing both branches and merging them
     * together afterwards. */
    val branches = Environment.fork(env, List(condition))

    /* Execute both branches with the corresponding branch environments. */
    if (test.isInstanceOf[BooleanValue]) {
      val testB = test.asInstanceOf[BooleanValue]
      if (testB.v) {
        execute(trueBlock, branches(0))
      } else {
        try {
          execute(falseBlock, branches(1))
        } catch {
          case e: Exception => branches(1)
        }
      }
    } else {
      execute(trueBlock, branches(0))
      try {
        execute(falseBlock, branches(1))
      } catch {
        case e: Exception => branches(1)
      }
    }
    env.weaveDelta(BranchEnv.join(List(branches(0), branches(1)), List(condition)))
    return ControlCode.OK
  }

  private def executeWhileStatement(s: WhileStatement, env: Environment): ControlCode.Value = {
    val block = s._block
    val test = s._test
    test match {
      // TODO Koennte man das nicht ausführen?
      case bae: BinaryAssignExpr => {
        env.update(bae._var.toString(), SymbolValue("", OakHeap.index, SymbolFlag.EXPR_UNEVALUATED))
      }
      case _ =>
    }
    val loopEnv = Environment.createLoopEnvironment(env)
    execute(block, loopEnv)
    env.weaveDelta(loopEnv.getDelta())
    return ControlCode.OK
  }

  /**
   * Statement of the form
   * return <-Expr>;
   */
  private def executeReturnStatement(s: ReturnStatement, env: Environment): ControlCode.Value = {
    val expr = s._expr
    val v = try {
      evaluate(expr, env)
    } catch {
      case vnfe: VariableNotFoundException => SymbolValue("return", OakHeap.index, SymbolFlag.EXPR_UNEVALUATED)
    }
    env.update("$return", v)
    return ControlCode.OK
  }

  /**
   * Adds a class definition to the environment.
   */
  private def executeClassDefStatement(s: ClassDefStatement, env: Environment): ControlCode.Value = {

    val interpreted = s._cl
    val classDefCasted = interpreted.asInstanceOf[com.caucho.quercus.program.ClassDef]

    // TODO class declaration 
    // TODO kompakter schreiben
    val name = interpreted.getName
    val fields = interpreted._fieldMap
    val methods = interpreted._functionMap

    /* Define all methods as functions */
    val classMethodNames = List(methods.keySet().toArray: _*)

    val classMethodDefs = ListBuffer[FunctionDef]()
    classMethodNames.foreach {
      name => classMethodDefs.append(env.defineFunction(methods.get(name)))
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
    if ((classDefCasted._parentName != null) && (!(classDefCasted._parentName equals "WP_HTTP_Streams"))) {
      val parentClassDef = env.getClass(classDefCasted._parentName)

      classFieldNames ++= parentClassDef.getFields()
      parentClassDef.methods.foreach {
        case (name, fdef) => classMethodDefMap += (name -> fdef)
      }
    } else { }

    val parent = if (interpreted._parentName != null) interpreted._parentName else ""
    val classDef = ClassDef(name, classFieldNames, classMethodDefMap, parent)

    /* Add construcotrs to the class definition. */
    classMethodDefs.takeWhile {
      fd => fd.name.equals("__construct")
    }.foreach {
      fd => classDef.addConstructor(fd.args.size, fd)
    }
    env.addClass(classDef)
    return ControlCode.OK
  }

  private def executeReturnRefStatement(s: ReturnRefStatement, env: Environment): ControlCode.Value = {
    val u = s._expr
    val returnRef = u match {
      case t: ThisFieldExpr => {
        val fieldName = t._name.toString()
        val thisArray = env.lookup("$this").asInstanceOf[ArrayValue]
        thisArray.getRef(StringValue(fieldName, t._location.getFileName(), t._location.getLineNumber()))
      }
      case v: VarExpr => {
        env.getVariables().get(v.toString).get
      }
      case a: ArrayGetExpr => {
        val array = env.lookup(a._expr.toString()).asInstanceOf[ArrayValue]
        val index = evaluate(a._index, env)
        array.getRef(index)
      }
    }
    env.setRef("$return", returnRef)
    return ControlCode.OK
  }

  /**
   * Statement of the form:
   * 
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
  private def executeSwitchStatement(s: SwitchStatement, env: Environment): ControlCode.Value = {

    val v = s._value
    val cases = s._cases.toList.map { m => m(0) }
    val blocks = s._blocks.toList
    val default = if (s._defaultBlock != null) s._defaultBlock else new BlockStatement(null, Array[Statement]())
    val conditions = cases.map { c => v + " == " + c }

    // FIXME what if no default case has been specified?
    val results = (Environment.fork(env, conditions) zip (default :: blocks.reverse).reverse)
    results.foreach { case (e, s) => execute(s, e) }
    val bResults = results.map { e => e._1 }

    val delta = BranchEnv.join(bResults, conditions)
    env.weaveDelta(delta)
    return ControlCode.OK
  }

  private def executeBreakStatement(s: BreakStatement, env: Environment): ControlCode.Value = {
    ControlCode.OK
  }

  private def executeContinueStatement(s: ContinueStatement, env: Environment): ControlCode.Value = {
    ControlCode.OK
  }

  private def executeForStatement(s: ForStatement, env: Environment): ControlCode.Value = {
    val init = s._init
    val block = s._block

    // Initialize the loop environment
    val loopEnv = Environment.createLoopEnvironment(env)
    evaluate(init, loopEnv)
    execute(block, loopEnv)
    env.weaveDelta(loopEnv.getDelta())
    return ControlCode.OK
  }

  private def executeTextStatement(s: TextStatement, env: Environment): ControlCode.Value = {
    val value = s._value
    ControlCode.OK
  }

  private def executeGlobalStatement(s: GlobalStatement, env: Environment): ControlCode.Value = {
    val value = s._var
    try {
      env.addToGlobal(value.toString)
    } catch {
      case vnfe: VariableNotFoundException => {
        throw new RuntimeException(vnfe)
      }
    }
    ControlCode.OK
  }

  private def executeStaticStatement(s: StaticStatement, env: Environment): ControlCode.Value = {
    /**
     * protected StringValue _uniqueStaticName;
     *
     * protected VarExpr _var;
     * protected Expr _initValue;
     */
    val uniqueStaticName = s._uniqueStaticName.toString()
    val variable = s._var
    val initValue = evaluate(s._initValue, env)

    try {
      env.addToGlobal(variable.toString)
    } catch {
      case vnfe: VariableNotFoundException => {
        throw new RuntimeException(vnfe)
      }
    }
    env.update(variable.toString, initValue)
    ControlCode.OK
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
  private def executeForeachStatement(s: ForeachStatement, env: Environment): ControlCode.Value = {

    val objExpr = s._objExpr
    val key = s._key
    val value = s._value
    val block = s._block
    val loopEnv = Environment.createLoopEnvironment(env)

    // Initialize the loop environment
    loopEnv.update(value.toString(), SymbolValue("foreach::", OakHeap.index, SymbolFlag.EXPR_UNEVALUATED))
    execute(block, loopEnv)
    env.weaveDelta(loopEnv.getDelta())
    ControlCode.OK

  }

  private def executeDoStatement(s: DoStatement, env: Environment): ControlCode.Value = {
    val loopEnv = Environment.createLoopEnvironment(env)
    execute(s._block, loopEnv)
    env.weaveDelta(loopEnv.getDelta())
    return ControlCode.OK
  }

  private def executeFunctionDefStatement(s: FunctionDefStatement, env: Environment): ControlCode.Value = {
    val function = s._fun
    env.addFunction(env.defineFunction(function))
    ControlCode.OK
  }

  private def executeTryStatement(s: TryStatement, env: Environment): ControlCode.Value = {
    execute(s._block, env)
    ControlCode.OK
  }

  override def execute(stmt: Statement, env: Environment): ControlCode.Value = {
    stmt match {
      case s: ClassDefStatement => {
        executeClassDefStatement(s, env)
      }
      case s: BlockStatement => {
        executeBlockStatement(s, env)
      }
      case s: EchoStatement => {
        executeEchoStatement(s, env)
      }
      case s: ExprStatement => {
        executeExprStatement(s, env)
      }
      case s: IfStatement => {
        executeIfStatement(s, env)
      }
      case s: WhileStatement => {
        executeWhileStatement(s, env)
      }
      case s: ReturnStatement => {
        executeReturnStatement(s, env)
      }
      case s: ReturnRefStatement => {
        executeReturnRefStatement(s, env)
      }
      case s: SwitchStatement => {
        executeSwitchStatement(s, env)
      }
      case s: BreakStatement => {
        executeBreakStatement(s, env)
      }
      case s: ForStatement => {
        executeForStatement(s, env)
      }
      case s: ContinueStatement => {
        executeContinueStatement(s, env)
      }
      case s: TextStatement => {
        executeTextStatement(s, env)
      }
      case s: GlobalStatement => {
        executeGlobalStatement(s, env)
      }
      case s: StaticStatement => {
        executeStaticStatement(s, env)
      }
      case s: ForeachStatement => {
        executeForeachStatement(s, env)
      }
      case s: DoStatement => {
        executeDoStatement(s, env)
      }
      case s: FunctionDefStatement => {
        executeFunctionDefStatement(s, env)
      }
      case s: TryStatement => {
        executeTryStatement(s, env)
      }
      case _ => throw new RuntimeException(stmt + " unimplemented.")
    }
  }

  def evaluate(e: LiteralExpr, env: Environment): OakValue = {
    val value = e._value

    /*
     * Boolean Value
     */
    if (e.isBoolean) {
      if (e.isTrue()) {
        return BooleanValue(true)
      } else if (e.isFalse()) {
        return BooleanValue(false)
      } else {
        throw new RuntimeException("E")
      }
    } else if (e.isNumber) { //numeric
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
    } else {
      throw new RuntimeException("C")
    }
  }

  def evaluate(e: LiteralUnicodeExpr, env: Environment): OakValue = {
    val sv = StringValue(e.toString.slice(1, e.toString.length - 1), e._location.getFileName(), e._location.getLineNumber())
    return sv
  }

  def evaluate(e: VarExpr, env: Environment): OakValue = {

    val value = try {
      env.lookup(e.toString)
    } catch {
      case vnfe: VariableNotFoundException => return SymbolValue(e.toString, OakHeap.index, SymbolFlag.DUMMY)
    }
    return value match {
      case ref: OakVariable => env.heap.extract(ref)
      case _ => value
    }
  }

  def evaluate(e: UnaryNotExpr, env: Environment): OakValue = {
    val p = evaluate(e.getExpr, env)
    return p match {

      // default case: e evaluates to a BooleanValue
      case e: BooleanValue => {
        e.not
      }

      // exceptional case: Found symbolic value -> return symbolic value
      case v2: SymbolicValue => SymbolValue(e.toString, OakHeap.getIndex, SymbolFlag.AMBIGUOUS_VALUE)

      case a: ArrayValue => {
        val b = if (a.array.size == 0) {
          false
        } else {
          true
        }
        BooleanValue(b)
      }

      case _ => SymbolValue(e.toString, OakHeap.index, SymbolFlag.AMBIGUOUS_VALUE)
    }
  }

  def evaluate(ae: AbstractBinaryExpr, env: Environment): OakValue = {
    SymbolValue(ae.toString, OakHeap.getIndex, SymbolFlag.EXPR_UNEVALUATED)
  }

  def evaluate(e: CallExpr, env: Environment): OakValue = {
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
      return SymbolValue(e.toString, OakHeap.getIndex, SymbolFlag.RECURSION)
    }
    /* If the function called refers to one implemented library function, such as
       * count($x) for arrays or concat/. for string literals, that implementation
       * will be used (instead). 
       * Each plugin implements its own library method, this.plugins is a map from 
       * the library function names to the actual plugin.
       * */
    if (getPlugins.contains(name)) {

      /* The library function plugin visits and evaluates the expression. */
      return this.accept(getPlugin(name), args.toList, path, env)
    }

    /* Retrieve the function definition from the environment. If we fail
       * loading the function, we return with a symbol value, i.e., the called function
       * is either undefined or unimplemented. */
    val function = try {
      env.getFunction(name)
    } catch {
      case ex: Exception => return SymbolValue(e.toString, OakHeap.getIndex(), SymbolFlag.EXPR_UNEVALUATED)
    }
    // Assert that the number of arguments in the function call and declaration match
    //assert(function.getArgs.length == args.length)

    /* CALL-BY-VALUE only */

    /**
     * Create a new (function) environment with pre-assigned arguments
     */
    try {
      var functionEnv = Environment.createFunctionEnvironment(env, name)
      prepareFunctionOrMethod(function, env, functionEnv, args.toList)
      execute(function.getStatement, functionEnv)

      //write output during the function call to the parent environment
      functionEnv.getParent().receiveOutput(functionEnv.getOutput())

      val returnValue = try {
        functionEnv.lookup("$return")
      } catch {
        case e: Exception => null
      }
      returnValue
    } catch {
      case nsee: NoSuchElementException => SymbolValue(e.toString, OakHeap.index, SymbolFlag.DUMMY)
    }
  }

  def evaluate(arrayExpr: FunArrayExpr, env: Environment): OakValue = {
    val valueExprList = arrayExpr._values
    val array = new ArrayValue()
    (valueExprList zipWithIndex).foreach {
      case (v: Expr, i: Int) => array.set(IntValue(i), evaluate(v, env), env)
    }
    array
  }

  def evaluate(arrayGet: ArrayGetExpr, env: Environment): OakValue = {

    val exx = arrayGet._expr
    if (exx.toString.equals("$_POST") || exx.toString.equals("$_GET") || exx.toString.equals("$_SESSION") || exx.toString.equals("$_SERVER")) {
      return SymbolValue(exx.toString, OakHeap.index, SymbolFlag.BUILTIN_VALUE)
    }

    val expr = evaluate(exx, env)
    val index = evaluate(arrayGet._index, env)
    expr match {
      case av: ArrayValue => {
        val value = try {
          av.get(index, env)
        } catch {
          case _: Throwable => SymbolValue("", OakHeap.index, SymbolFlag.EXPR_UNEVALUATED)
        }
        value
      }
      case _ => {
        SymbolValue(exx.toString, OakHeap.index, SymbolFlag.EXPR_UNEVALUATED)
      }
    }
    SymbolValue(arrayGet.toString, OakHeap.index, SymbolFlag.EXPR_UNEVALUATED)
  }

  def evaluate(b: BinaryAppendExpr, env: Environment): OakValue = {
    /* Get all values that are being concatenated*/
    val expressions = ListBuffer[Expr]()
    var expr = b

    expressions.append(expr.getValue)
    do {
      expr = expr.getNext
      expressions.append(expr.getValue)
    } while (expr.getNext != null)

    val value = new OakValueSequence(expressions.toList.map { e => try { evaluate(e, env) } catch { case aioob: ArrayIndexOutOfBoundsException => SymbolValue(e.toString(), OakHeap.index, SymbolFlag.EXPR_UNEVALUATED) } })
    value
  }

  /*
  * $objExpr->methodName(args...)
  * 
  */
  def evaluate(e: ObjectMethodExpr, env: Environment): OakValue = {

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
        execute(method.statement, objEnv)

        val arrayVal = objEnv.lookup("$this").asInstanceOf[ArrayValue]
        val returnVal = try {
          if (method.returnsRef()) {
            objEnv.getRef("$return")
          } else {
            objEnv.lookup("$return")
          }
        } catch {
          case e: Exception => throw new RuntimeException("B")
        }

        arrayVal.getKeys.foreach {
          key => objectValBefore.set(key.asInstanceOf[StringValue].value.toString, arrayVal.get(key, env), env)
        }
        env.update(objExpr.toString(), objectValBefore.fields)
        returnVal

      } else {

        /*
       	* Create new Environment
       	*/
        var objEnv = Environment.createObjectEnvironment(env, env.lookup(objExpr.toString()).asInstanceOf[ObjectValue]) // assuming this is sth like $obj

        val method = env.lookup(objExpr.toString()).asInstanceOf[ObjectValue].getClassdef().getMethods(methodName)

        objEnv = prepareFunctionOrMethod(method, env, objEnv, args.toList).asInstanceOf[ObjectEnv]
        //assert(method.args.size == args.size)
        /*(method.args zip args).foreach {
          case (name, expr) => objEnv.update("$" + name, evaluate(expr, env)._1)
        }*/

        val objectValBefore = env.lookup(objExpr.toString()).asInstanceOf[ObjectValue]

        execute(method.statement, objEnv)

        val arrayVal = objEnv.lookup("$this").asInstanceOf[ArrayValue]
        val returnVal = try {
          if (method.returnsRef()) {
            objEnv.getRef("$return")
          } else {
            objEnv.lookup("$return")
          }
        } catch {
          case e: Exception => null
        }

        arrayVal.getKeys.foreach {
          key => objectValBefore.set(key.asInstanceOf[StringValue].value.toString, arrayVal.get(key, env), env)
        }
        env.update(objExpr.toString(), objectValBefore)
        returnVal
      }
    } catch {
      case _ => SymbolValue(e.toString, OakHeap.index, SymbolFlag.AMBIGUOUS_VALUE)
    }
  }

  def evaluate(b: ObjectNewExpr, env: Environment): OakValue = {
    val name = b._name
    val args = b._args

    val objectValue = try {
      val constructor = env.getClass(name).getConstructor(args.size) // match by number of args

      /** Create a new object Value */
      val obj = ObjectValue("Object Doe", env.getClass(name))
      val objEnv = Environment.createObjectEnvironment(env, obj)

      /* Execute constructor in the object environment and keep its variable $this:
     *  
     *  - assign all evaluated arg expressions to the constructor args
     *   */
      assert(constructor.args.size == args.size)
      (constructor.args zip args).foreach {
        case (name, expr) => objEnv.update("$" + name, evaluate(expr, env))
      }

      execute(constructor.statement, objEnv)

      objEnv.lookup("$this") match {
        case obj: ObjectValue => {
          val thisArray = obj.fields
          thisArray.getKeys.foreach {
            key => obj.set(key.asInstanceOf[StringValue].value.toString, thisArray.get(key, env), env)
          }
          obj
        }
        case sym: SymbolicValue => {
          SymbolValue(sym.toString(), OakHeap.index, SymbolFlag.AMBIGUOUS_VALUE)
        }
      }
    } catch {
      case nsee: NoSuchElementException => {
        env.getClass(name).getDefaultObject(env)
      }
    }
    objectValue
  }

  def evaluate(t: UnarySuppressErrorExpr, env: Environment): OakValue = {
    evaluate(t._expr, env)
  }

  def evaluate(t: ThisFieldExpr, env: Environment): OakValue = {
    val fieldName = t._name.toString()
    val ref = env.lookup("$this").asInstanceOf[ArrayValue].getRef(StringValue(fieldName, t._location.getFileName(), t._location.getLineNumber()))
    val value = env.heap.extract(ref)
    value
  }

  def evaluate(t: LiteralNullExpr, env: Environment): OakValue = {
    NullValue("LiteralNullExpr")
  }

  def evaluate(e: BinaryAssignExpr, env: Environment): OakValue = {
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
          val value = evaluate(assignExpr, env)
          names.foreach { n => env.update(n, value) }
          return null
        }

        try {
          val value = evaluate(expr, env)
          val valueX = value match {
            case sv: StringValue => {
              sv
            }
            case _ => {
              value
            }
          }
          env.update(name.toString, valueX)
        } catch {
          case vnfe: VariableNotFoundException => {

          }
        }
        null
      }

      /**
       * Assignment to array field:
       *
       * $<arrayName>..[<Expr>] = <Expr>
       */
      case a: ArrayGetExpr => {
        val indexRaw = evaluate(a._index, env)
        val index = indexRaw match {
          case s: StringValue => {
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
            env.update(arrayValueName, SymbolValue(a.toString, OakHeap.index, SymbolFlag.AMBIGUOUS_VALUE))
            return null
          }

          if (av.isInstanceOf[NullValue]) {
            logger.error(a + " is null")
            return null
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
        val value = evaluate(expr, env)
        val valueX = value match {
          case sv: StringValue => sv
          case _ => value
        }
        arrayValue.set(index, valueX, env)
        env.update(arrayValueName, arrayValue)
        return null
      }

      /**
       * Assignment to object field (inside method declaration):
       *  $this -> <fieldName> = <Expr>
       */
      case dis: ThisFieldExpr => {
        val fieldName = dis._name

        /* $this points to an array, hence we perform an assignment to $this[name] */
        env.lookup("$this") match {
          case thisValue: ObjectValue => {
            val value = evaluate(expr, env)
            val valueX = value match {
              case sv: StringValue => sv
              case _ => value
            }
            thisValue.set(fieldName.toString(), valueX, env)
            env.update("$this", thisValue)
          }
          case s: SymbolicValue => {
            //logger.info("Object is symbolic")
          }
        }

        null
      }

      /**
       * Assignment to object field:
       * $<objName> -> <fieldName> = <Expr>
       */
      case of: ObjectFieldExpr => {
        val objName = of._objExpr.toString()
        try {
          val obj = env.lookup(objName).asInstanceOf[ObjectValue]
          val fieldName = of._name.toString()
          val ref = if (obj.fields.getKeys contains fieldName) {
            obj.fields.getRef(StringValue(fieldName, of._location.getFileName, of._location.getLineNumber))
          } else {
            OakVariable(fieldName + OakHeap.index, objName + "." + fieldName)
          }
          val value = evaluate(expr, env)
          val valueX = value match {
            case sv: StringValue =>
              sv
            case _ => value
          }
          env.heap.insert(ref, valueX)
        } catch {
          case vnfe: VariableNotFoundException => {}
          case cce: ClassCastException => {}
        }
        null
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
            val value = evaluate(expr, env)
            val index = evaluate(a._index, env)

            array.set(index, value, env)
            env.update(name, array)
          }
          case _ => {
            env.update(name, evaluate(expr, env))
          }
        }
        null
      }
      case tfve: ThisFieldVarExpr => {
        val fieldName = StringValue(tfve._nameExpr.toString, tfve._location.getFileName, tfve._location.getLineNumber)
        val thisArray = env.lookup("$this").asInstanceOf[ArrayValue]
        thisArray.set(fieldName, evaluate(expr, env), env)
        env.update("$this", thisArray)
        null
      }
      case _ => throw new RuntimeException(name.getClass + " unexpected " + expr.toString())
    }
  }

  def evaluate(e: LiteralLongExpr, env: Environment): OakValue = {
    val value = e._value
    IntValue(value)
  }

  def evaluate(e: ConditionalExpr, env: Environment): OakValue = {

    val test = e._test
    val testV = evaluate(test, env)
    val trueExpr = e._trueExpr
    val falseExpr = e._falseExpr

    if (testV.isInstanceOf[SymbolicValue]) {
      Choice(test.toString, evaluate(trueExpr, env), evaluate(falseExpr, env))
    } else {
      testV match {
        case b: BooleanValue => {
          if (b.value) {
            evaluate(trueExpr, env)
          } else {
            evaluate(falseExpr, env)
          }
        }
        case _ => throw new RuntimeException("Expression did not evaluate to BooleanExpression.")
      }
    }
  }

  def evaluate(e: UnaryMinusExpr, env: Environment): OakValue = {
    val value = evaluate(e._expr, env)
    value match {
      case n: NumericValue => {
        IntValue(-1) * n
      }
      case _ => SymbolValue(e.toString, OakHeap.index, SymbolFlag.AMBIGUOUS_VALUE)
    }
  }

  def evaluate(e: FunIssetExpr, env: Environment): OakValue = {
    return try {
      val x = env.lookup(e.toString)
      BooleanValue(true)
    } catch {
      case e: VariableNotFoundException => BooleanValue(false)
    }
  }

  def evaluate(e: ConstExpr, env: Environment): OakValue = {
    if (constants.keySet contains e.toString) {
      val opt = constants.get(e.toString)
      return opt.get
    } else {
      StringValue("", e._location.getFileName, e._location.getLineNumber)
    }
  }

  def evaluate(e: FunEmptyExpr, env: Environment): OakValue = {
    val value = e._value
    val re = try {
      val empty = env.lookup(e.toString)
      if (empty == BooleanValue(false)) {
        BooleanValue(true)
      } else {
        BooleanValue(false)
      }
    } catch {
      case v: VariableNotFoundException => BooleanValue(true)
    }
    return re
  }

  def evaluate(e: ToArrayExpr, env: Environment): OakValue = {
    val expr = e._expr
    new ArrayValue()
  }

  def evaluate(cme: ClassMethodExpr, env: Environment): OakValue = {
    // ClassName::ClassName(<args>) called, but not a 
    if (cme._className equals cme._methodName.toString()) {

      val classDef = env.getClass(cme._className)
      val constructor = classDef.getConstructor(cme._args.size)
      execute(constructor.statement, env)
      null
    } else {
      val classDef = env.getClass(cme._className)
      val method = classDef.getMethods(cme._methodName.toString)
      val cmEnv = Environment.createFunctionEnvironment(env, cme._className + "::" + cme._methodName.toString())
      val args = cme._args

      //default stuff
      (method.args zip args).foreach {
        case (name, expr) => {
          cmEnv.update(name, evaluate(expr, env))
        }
      }

      execute(method.statement, cmEnv)
      val returnValue = try {
        env.lookup("$return")
      } catch {
        case e: VariableNotFoundException => null
      }
      returnValue
    }
    null
  }

  // TODO Präziser nachimplementieren
  def evaluate(e: ThisMethodExpr, env: Environment): OakValue = {
    SymbolValue(e.toString(), OakHeap.index, SymbolFlag.EXPR_UNIMPLEMENTED)
  }

  def evaluate(e: ToLongExpr, env: Environment): OakValue = {
    val expr = e._expr
    SymbolValue(expr.toString, OakHeap.index, SymbolFlag.TYPE_CONVERSION)
  }

  def evaluate(e: ObjectFieldExpr, env: Environment): OakValue = {
    // $this->
    val obj = evaluate(e._objExpr, env)

    val value = if (obj.isInstanceOf[ObjectValue]) {
      obj.asInstanceOf[ObjectValue].get(e._name.toString(), env)
    } else {
      SymbolValue(e.toString, OakHeap.index, SymbolFlag.AMBIGUOUS_VALUE)
    }
    value
  }

  def evaluate(e: ThisFieldVarExpr, env: Environment): OakValue = {
    val thisV = env.lookup("$this").asInstanceOf[ArrayValue]
    val t = thisV.get(StringValue(e._nameExpr.toString, e._location.getFileName, e._location.getLineNumber), env)
    t
  }

  def evaluate(e: BinaryInstanceOfExpr, env: Environment): OakValue = {
    SymbolValue(e.toString, OakHeap.index, SymbolFlag.TYPE_CONVERSION)
  }

  def evaluate(e: ToStringExpr, env: Environment): OakValue = {
    SymbolValue(e._expr.toString, OakHeap.index, SymbolFlag.TYPE_CONVERSION)
  }

  def evaluate(e: ThisExpr, env: Environment): OakValue = {
    env.lookup("$this") match {
      case obj: ObjectValue => {
        obj
      }
      case sym: SymbolicValue => {
        sym
      }
    }
  }

  def evaluate(e: ToBooleanExpr, env: Environment): OakValue = {
    SymbolValue(e._expr.toString, OakHeap.index, SymbolFlag.TYPE_CONVERSION)
  }

  override def evaluate(e: Expr, env: Environment): OakValue = {
    e match {
      case e: LiteralExpr => {
        evaluate(e, env)
      }
      case e: LiteralUnicodeExpr => {
        evaluate(e, env)
      }
      case e: VarExpr => {
        evaluate(e, env)
      }
      case e: UnaryNotExpr => {
        evaluate(e, env)
      }
      case e: UnaryMinusExpr => {
        evaluate(e, env)
      }
      case ae: AbstractBinaryExpr => {
        evaluate(ae, env)
      }
      case e: CallExpr => {
        evaluate(e, env)
      }
      case arrayExpr: FunArrayExpr => {
        evaluate(arrayExpr, env)
      }
      case arrayGet: ArrayGetExpr => {
        evaluate(arrayGet, env)
      }
      case b: BinaryAppendExpr => {
        evaluate(b, env)
      }
      case o: ObjectNewExpr => {
        evaluate(o, env)
      }
      case e: ThisMethodExpr => {
        evaluate(e, env)
      }
      case o: ObjectMethodExpr => {
        evaluate(o, env)
      }
      case t: ThisFieldExpr => {
        evaluate(t, env)
      }
      case n: LiteralNullExpr => {
        evaluate(n, env)
      }
      case a: BinaryAssignExpr => {
        evaluate(a, env)
      }
      case u: UnarySuppressErrorExpr => {
        evaluate(u, env)
      }
      case e: LiteralLongExpr => {
        evaluate(e, env)
      }
      case ce: ConditionalExpr => {
        evaluate(ce, env)
      }
      case e: FunIssetExpr => {
        evaluate(e, env)
      }
      case e: ConstExpr => {
        evaluate(e, env)
      }
      case e: FunEmptyExpr => {
        evaluate(e, env)
      }
      case e: ToArrayExpr => {
        evaluate(e, env)
      }
      case e: ClassMethodExpr => {
        evaluate(e, env)
      }
      case e: ToLongExpr => {
        evaluate(e, env)
      }
      case e: ObjectFieldExpr => {
        evaluate(e, env)
      }
      case e: BinaryInstanceOfExpr => {
        evaluate(e, env)
      }
      case e: ToStringExpr => {
        evaluate(e, env)
      }
      case e: ThisExpr => {
        evaluate(e, env)
      }
      case e: ToBooleanExpr => {
        evaluate(e, env)
      }
      case _ => return SymbolValue(e.toString(), 0, SymbolFlag.EXPR_UNIMPLEMENTED)
    }
  }

  def getInlcudePath(expr: Expr, env: Environment): Path = {
    var path = evaluate(expr, env).toString.replace("\"", "")
    if (!(path startsWith ("/"))) {
      path = this.rootPath + "/" + path
    }
    Paths.get(path)
  }
}

object OakInterpreter {
  var symbolSet = Set[SymbolValue]()
}

object SymbolFlag extends Enumeration {
  val FUNCTION_CALL, EXPR_UNEVALUATED, AMBIGUOUS_VALUE, EXPR_UNIMPLEMENTED, BUILTIN_VALUE, TYPE_CONVERSION, DUMMY, RECURSION = Value
}
