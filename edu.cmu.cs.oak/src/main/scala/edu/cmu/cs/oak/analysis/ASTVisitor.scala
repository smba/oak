package edu.cmu.cs.oak.analysis

import java.nio.file.Path

import com.caucho.quercus.Location
import com.caucho.quercus.expr._
import com.caucho.quercus.program.Function
import com.caucho.quercus.statement._
import edu.cmu.cs.oak.core.OakEngine
import edu.cmu.cs.oak.value.StringValue
import org.slf4j.LoggerFactory

import scala.collection.JavaConversions._
import scala.collection.mutable.HashSet
import edu.cmu.cs.oak.value.StringLiteralContext

/**
 * Traverses the PHP AST provided by Quercus and retrieves all
 * string literals, regardless of reachability. Besides, additoinal
 * information about all string literals, such as its location
 * and its context (surrounding expression) are collected.
 *
 * @author Stefan Muehlbauer <smuhlbau@andrew.cmu.edu>
 */
class ASTVisitor(path: Path) {

  /** Set of found string literals including contextual information. */
  lazy val stringLiterals = new HashSet[StringValue]

  /** Engine used to access PHP scripts. */
  lazy val engine = new OakEngine()

  /** Logger instance. */
  lazy val logger = LoggerFactory.getLogger(classOf[ASTVisitor])

  var context = StringLiteralContext.MISC
  var current_fdef: (String, Location)  = ("", null) // (name, location)
  val functions = scala.collection.mutable.HashMap[(String, Location), Boolean]()
  
  val include_expressions = scala.collection.mutable.HashSet[(String, Int)]() // locations
  
  var location: Location = null
  
  /**
   * Set the global parent path for the ASTVisitor
   */
  def init() {
    ASTVisitor.rootPath = path.getParent()
  }

  /**
   * Loads a program from file and retrieves string literals.
   *
   * @param path URL to the PHP source file to parse
   */
  def retrieveStringLiterals(): (Set[StringValue], Set[(String, Int)]) = {
    
    val program = engine.loadFromFile(path)
    
    // Traverse function string literals
    if (program != null) {
      List(program.getFunctionList.toArray: _*).foreach {
        f => {
          val pre = context
          context = StringLiteralContext.FDEFINITION
          val r = visit(f.asInstanceOf[Function]._statement)
          current_fdef = (f.asInstanceOf[Function]._name.toString, f.asInstanceOf[Function]._statement._location)
          functions.put(current_fdef, r)
          context = pre
        }
      }
      visit(program.getStatement)
      (stringLiterals.toSet, include_expressions.toSet)
    } else {
      (Set[StringValue](), include_expressions.toSet)
    }
  }

  def visit(stmt: Statement): Boolean = {

    location = stmt._location

    stmt match {

      /**
       * Case for AST node class BlockStatement.
       */
      case s: BlockStatement => {

        s._statements.map {
          st => {
            visit(st)
          }
        }.fold(false)(_ || _)

      }

      /**
       * Case for AST node class BreakStatement.
       */
      case s: BreakStatement => {false}

      /**
       * Case for AST node class ClassDefStatement.
       */
      case s: ClassDefStatement => {
        val ic = s._cl
        val functionMap = ic._functionMap //Interpreter.accessField(ic, "_functionMap").asInstanceOf[LinkedHashMap[com.caucho.quercus.env.StringValue, AbstractFunction]]

        val functions = functionMap.map { case (k, v) => (k.toString(), v) }
        
        functions.values.map {
          f => {
            val pre = context
            context = StringLiteralContext.FDEFINITION
            val r = visit(f.asInstanceOf[Function]._statement)
            context = pre
            r
          }
        }.fold(false)(_ || _)
      }

      /**
       * Case for AST node class ClassStaticStatement.
       */
      case s: ClassStaticStatement => {false}

      /**
       * Case for AST node class ClosureStaticStatement.
       */
      case s: ClosureStaticStatement => {false}

      /**
       * Case for AST node class ContinueStatement.
       */
      case s: ContinueStatement => {false}

      /**
       * Case for AST node class DoStatement.
       */
      case s: DoStatement => {
        visit(s._test)
        visit(s._block)
      }

      /**
       * Case for AST node class EchoStatement.
       */
      case s: EchoStatement => {

        val expr = s._expr
        visit(expr)
      }

      /**
       * Case for AST node class ExprStatement.
       */
      case s: ExprStatement => {
        
        val expr = s._expr
        
        if (expr.isInstanceOf[FunIncludeExpr] || expr.isInstanceOf[FunIncludeOnceExpr]) {
          include_expressions.add(s._location.getFileName, s._location.getLineNumber)
        }
        
        visit(expr)

      }

      /**
       * Case for AST node class ForeachStatement.
       */
      case s: ForeachStatement => {
        visit(s._objExpr)
        visit(s._block)
      }

      /**
       * Case for AST node class ForStatement.
       */
      case s: ForStatement => {
        val block = s._block
        visit(block)
      }

      /**
       * Case for AST node class FunctionDefStatement.
       */
      case s: FunctionDefStatement => {
        
        val prev = context
        context = StringLiteralContext.FDEFINITION
        
        current_fdef = (s._fun._name.toString(), s._fun._statement._location)
        
        val r = visit(s._fun._statement)
        
        functions.+=( (current_fdef -> r) )
        context = prev
        r
      }

      /**
       * Case for AST node class GlobalStatement.
       */
      case s: GlobalStatement => {false}

      /**
       * Case for AST node class IfStatement.
       */
      case s: IfStatement => {
        //val expr = Interpreter.accessField(s, "_test").asInstanceOf[Expr]
        //visit(expr, ASTVisitor.getStatementLineNr(s))

        visit(s._trueBlock)
        try {
          visit(s._falseBlock)
        } catch {
          case e: Exception => {false}
        }
      }

      /**
       * Case for AST node class NullStatement.
       */
      case s: NullStatement => {false}

      /**
       * Case for AST node class ReturnRefStatement.
       */
      case s: ReturnRefStatement => { false }

      /**
       * Case for AST node class ReturnStatement.
       */
      case s: ReturnStatement => {
        val expr = s._expr
        visit(expr)
      }

      /**
       * Case for AST node class StaticStatement.
       */
      case s: StaticStatement => {
        visit(s._initValue)
      }

      /**
       * Case for AST node class SwitchStatement.
       */
      case s: SwitchStatement => {
        val blocks = s._blocks
        val default = s._defaultBlock
        blocks.foreach { b => visit(b) }
        if (default != null) {
          visit(default)
        } else {
          false
        }
      }

      /**
       * Case for AST node class TextStatement.
       */
      case s: TextStatement => {
        
        val prev = context
        context = StringLiteralContext.TEMPLATE
        
        val value = s._value.toString()
        val string = StringValue(value, s._location.getFileName(), s._location.getLineNumber())
        string.context = context
        
        if (string.context == StringLiteralContext.FDEFINITION) {
          string.fdef = current_fdef
        }
        
        if (string.lineNr == 0) throw new RuntimeException()
        stringLiterals += string
        
        context = prev
        return Coverage.isRelevant(string)
      }

      /**
       * Case for AST node class ThrowStatement.
       */
      case s: ThrowStatement => {false}

      /**
       * Case for AST node class TryStatement.
       */
      case s: TryStatement => {
        visit(s._block)
      }

      /**
       * Case for AST node class VarGlobalStatement.
       */
      case s: VarGlobalStatement => {false}

      /**
       * Case for AST node class WhileStatement.
       */
      case s: WhileStatement => {
        val expr = s._test
        visit(expr)
        visit(s._block)
      }
    }

  }
  /**
   *
   */
  def visit(expr: Expr): Boolean = expr match {

    /**
     * Case for AST node class AbstractBinaryExpr.
     */
    case e: AbstractBinaryExpr => {
      visit(e._left)
      visit(e._right)
    }

    /**
     * Case for AST node class AbstractLongValuedExpr.
     */
    case e: AbstractLongValuedExpr => {false}

    /**
     * Case for AST node class AbstractMethodExpr.
     */
    case e: AbstractMethodExpr => {false}

    /**
     * Case for AST node class AbstractUnaryExpr.
     */
    case e: AbstractUnaryExpr => {false}

    /**
     * Case for AST node class AbstractVarExpr.
     */
    case e: AbstractVarExpr => {false}

    /**
     * Case for AST node class ArrayIsSetExpr.
     */
    case e: ArrayIsSetExpr => {false}

    /**
     * Case for AST node class ArrayTailExpr.
     */
    case e: ArrayTailExpr => {false}

    /**
     * Case for AST node class ArrayUnsetExpr.
     */
    case e: ArrayUnsetExpr => {false}

    /**
     * Case for AST node class BinaryAddExpr.
     */
    case e: BinaryAddExpr => {false}

    /**
     * Case for AST node class BinaryAndExpr.
     */
    case e: BinaryAndExpr => {false}

    /**
     * Case for AST node class BinaryAppendExpr.
     */
    case e: BinaryAppendExpr => {
      visit(e._value);
      val next = e._next
      if (next != null) { 
        visit(next) 
      } else {
        false
      }
    }

    /**
     * Case for AST node class BinaryAssignExpr.
     */
    case e: BinaryAssignExpr => {
      val _var = e._var
      val value = e._value
      visit(_var)
      visit(value)
    }

    /**
     * Case for AST node class BinaryAssignListEachExpr.
     */
    case e: BinaryAssignListEachExpr => {false}

    /**
     * Case for AST node class BinaryAssignListExpr.
     */
    case e: BinaryAssignListExpr => {
      val value = e._value
      visit(value)
    }

    /**
     * Case for AST node class BinaryAssignRefExpr.
     */
    case e: BinaryAssignRefExpr => {false}

    /**
     * Case for AST node class BinaryBitAndExpr.
     */
    case e: BinaryBitAndExpr => {false}

    /**
     * Case for AST node class BinaryBitOrExpr.
     */
    case e: BinaryBitOrExpr => {false}

    /**
     * Case for AST node class BinaryBitXorExpr.
     */
    case e: BinaryBitXorExpr => {false}

    /**
     * Case for AST node class BinaryCharAtExpr.
     */
    case e: BinaryCharAtExpr => {false}

    /**
     * Case for AST node class BinaryCommaExpr.
     */
    case e: BinaryCommaExpr => {false}

    /**
     * Case for AST node class BinaryDivExpr.
     */
    case e: BinaryDivExpr => {false}

    /**
     * Case for AST node class BinaryEqExpr.
     */
    case e: BinaryEqExpr => {false}

    /**
     * Case for AST node class BinaryEqualsExpr.
     */
    case e: BinaryEqualsExpr => {false}

    /**
     * Case for AST node class BinaryGeqExpr.
     */
    case e: BinaryGeqExpr => {false}

    /**
     * Case for AST node class BinaryGtExpr.
     */
    case e: BinaryGtExpr => {false}

    /**
     * Case for AST node class BinaryInstanceOfExpr.
     */
    case e: BinaryInstanceOfExpr => {false}

    /**
     * Case for AST node class BinaryInstanceOfVarExpr.
     */
    case e: BinaryInstanceOfVarExpr => {false}

    /**
     * Case for AST node class BinaryLeftShiftExpr.
     */
    case e: BinaryLeftShiftExpr => {false}

    /**
     * Case for AST node class BinaryLeqExpr.
     */
    case e: BinaryLeqExpr => {false}

    /**
     * Case for AST node class BinaryLtExpr.
     */
    case e: BinaryLtExpr => {false}

    /**
     * Case for AST node class BinaryModExpr.
     */
    case e: BinaryModExpr => {false}

    /**
     * Case for AST node class BinaryMulExpr.
     */
    case e: BinaryMulExpr => {false}

    /**
     * Case for AST node class BinaryNeqExpr.
     */
    case e: BinaryNeqExpr => {false}

    /**
     * Case for AST node class BinaryOrExpr.
     */
    case e: BinaryOrExpr => {false}

    /**
     * Case for AST node class BinaryRightShiftExpr.
     */
    case e: BinaryRightShiftExpr => {false}

    /**
     * Case for AST node class BinarySubExpr.
     */
    case e: BinarySubExpr => {false}

    /**
     * Case for AST node class BinaryXorExpr.
     */
    case e: BinaryXorExpr => {false}

    /**
     * Case for AST node class CallExpr.
     */
    case e: CallExpr => {
      val args = e._args
      args.map { a => visit(a) }.fold(false)(_ || _)
    }

    /**
     * zeit
     *
     */
    case e: CallVarExpr => {false}

    /**
     * Case for AST node class ClassConstExpr.
     */
    case e: ClassConstExpr => {false}

    /**
     * Case for AST node class ClassConstructExpr.
     */
    case e: ClassConstructExpr => {false}

    /**
     * Case for AST node class ClassConstructorExpr.
     */
    case e: ClassConstructorExpr => {false}

    /**
     * Case for AST node class ClassFieldExpr.
     */
    case e: ClassFieldExpr => {false}

    /**
     * Case for AST node class ClassFieldVarExpr.
     */
    case e: ClassFieldVarExpr => {false}

    /**
     * Case for AST node class ClassMethodExpr.
     */
    case e: ClassMethodExpr => {false}

    /**
     * Case for AST node class ClassMethodVarExpr.
     */
    case e: ClassMethodVarExpr => {false}

    /**
     * Case for AST node class ClassVarConstExpr.
     */
    case e: ClassVarConstExpr => {false}

    /**
     * Case for AST node class ClassVarFieldExpr.
     */
    case e: ClassVarFieldExpr => {false}

    /**
     * Case for AST node class ClassVarFieldVarExpr.
     */
    case e: ClassVarFieldVarExpr => {false}

    /**
     * Case for AST node class ClassVarMethodExpr.
     */
    case e: ClassVarMethodExpr => {false}

    /**
     * Case for AST node class ClassVarMethodVarExpr.
     */
    case e: ClassVarMethodVarExpr => {false}

    /**
     * Case for AST node class ClassVarNameConstExpr.
     */
    case e: ClassVarNameConstExpr => {false}

    /**
     * Case for AST node class ClassVarNameVirtualConstExpr.
     */
    case e: ClassVarNameVirtualConstExpr => {false}

    /**
     * Case for AST node class ClassVarVarConstExpr.
     */
    case e: ClassVarVarConstExpr => {false}

    /**
     * Case for AST node class ClassVirtualConstExpr.
     */
    case e: ClassVirtualConstExpr => {false}

    /**
     * Case for AST node class ClassVirtualFieldExpr.
     */
    case e: ClassVirtualFieldExpr => {false}

    /**
     * Case for AST node class ClassVirtualFieldVarExpr.
     */
    case e: ClassVirtualFieldVarExpr => {false}

    /**
     * Case for AST node class ClassVirtualMethodExpr.
     */
    case e: ClassVirtualMethodExpr => {false}

    /**
     * Case for AST node class ClassVirtualMethodVarExpr.
     */
    case e: ClassVirtualMethodVarExpr => {false}

    /**
     * Case for AST node class ClosureExpr.
     */
    case e: ClosureExpr => {false}

    /**
     * Case for AST node class ConditionalExpr.
     */
    case e: ConditionalExpr => {
      visit(e._test)
      visit(e._trueExpr)
      visit(e._falseExpr)
    }

    /**
     * Case for AST node class ConditionalShortExpr.
     */
    case e: ConditionalShortExpr => {false}

    /**
     * Case for AST node class ConstClassExpr.
     */
    case e: ConstClassExpr => {false}

    /**
     * Case for AST node class ConstDirExpr.
     */
    case e: ConstDirExpr => {false}

    /**
     * Case for AST node class ConstExpr.
     */
    case e: ConstExpr => {false}

    /**
     * Case for AST node class ConstFileExpr.
     */
    case e: ConstFileExpr => {false}

    /**
     * Case for AST node class DieExpr.
     */
    case e: DieExpr => {
      val value = StringValue(e._value.toString(), e._location.getFileName(), e._location.getLineNumber())
      if (value.lineNr == 0) throw new RuntimeException()
      value.context = context
      
      if (value.context == StringLiteralContext.FDEFINITION) {
          value.fdef = current_fdef
        }
      
      stringLiterals += value
      return Coverage.isRelevant(value)
    }

    /**
     * Case for AST node class FunArrayExpr.
     */
    case e: FunArrayExpr => {
      val values = e._values
      values.map { v => visit(v) }.fold(false)(_ || _)
    }

    /**
     * Case for AST node class FunCloneExpr.
     */
    case e: FunCloneExpr => {false}

    /**
     * Case for AST node class FunDieExpr.
     */
    case e: FunDieExpr => {
      visit(e._value)
    }

    /**
     * Case for AST node class FunEachExpr.
     */
    case e: FunEachExpr => {false}

    /**
     * Case for AST node class FunEmptyExpr.
     */
    case e: FunEmptyExpr => {false}

    /**
     * Case for AST node class FunExitExpr.
     */
    case e: FunExitExpr => {false}

    /**
     * Case for AST node class FunGetCalledClassExpr.
     */
    case e: FunGetCalledClassExpr => {false}

    /**
     * Case for AST node class FunGetClassExpr.
     */
    case e: FunGetClassExpr => {false}

    /**
     * Case for AST node class FunIncludeExpr.
     */
    case e: FunIncludeExpr => {
      
      // Record the include/requice expression
      
      false
    }

    /**
     * Case for AST node class FunIncludeOnceExpr.
     */
    case e: FunIncludeOnceExpr => {
      
       // Record the include/requice expression
      
      false
    }

    /**
     * Case for AST node class FunIssetExpr.
     */
    case e: FunIssetExpr => {false}

    /**
     * Case for AST node class ImportExpr.
     */
    case e: ImportExpr => {false}

    /**
     * Case for AST node class ListHeadExpr.
     */
    case e: ListHeadExpr => {false}

    /**
     * Case for AST node class LiteralBinaryStringExpr.
     */
    case e: LiteralBinaryStringExpr => {false}

    /**
     * Case for AST node class LiteralExpr.
     */
    case e: LiteralExpr => {false}

    /**
     * Case for AST node class LiteralLongExpr.
     */
    case e: LiteralLongExpr => {false}

    /**
     * Case for AST node class LiteralNullExpr.
     */
    case e: LiteralNullExpr => {false}

    /**
     * Case for AST node class LiteralStringExpr.
     */
    case e: LiteralStringExpr => {
      val string = StringValue(e._value.toString(), e._location.getFileName(), e._location.getLineNumber())
      if (string.lineNr == 0) throw new RuntimeException()
      string.context = context
      
      if (string.context == StringLiteralContext.FDEFINITION) {
          string.fdef = current_fdef
        }
      
      stringLiterals += string
      return Coverage.isRelevant(string)
    }

    /**
     * Case for AST node class LiteralUnicodeExpr.
     */
    case e: LiteralUnicodeExpr => {
      val string = StringValue(e._value.toString(), e._location.getFileName(), e._location.getLineNumber())
      if (string.lineNr == 0) {
        string.setLocation(location)
      }
      string.context = context
      
      if (string.context == StringLiteralContext.FDEFINITION) {
          string.fdef = current_fdef
        }
      
      stringLiterals += string
      return Coverage.isRelevant(string)
    }

    /**
     * Case for AST node class ObjectFieldExpr.
     */
    case e: ObjectFieldExpr => {false}

    /**
     * Case for AST node class ObjectFieldVarExpr.
     */
    case e: ObjectFieldVarExpr => {false}

    /**
     * Case for AST node class ObjectMethodExpr.
     */
    case e: ObjectMethodExpr => {false}

    /**
     * Case for AST node class ObjectMethodVarExpr.
     */
    case e: ObjectMethodVarExpr => {false}

    /**
     * Case for AST node class ObjectNewExpr.
     */
    case e: ObjectNewExpr => {false}

    /**
     * Case for AST node class ObjectNewStaticExpr.
     */
    case e: ObjectNewStaticExpr => {false}

    /**
     * Case for AST node class ObjectNewVarExpr.
     */
    case e: ObjectNewVarExpr => {false}

    /**
     * Case for AST node class ParamDefaultExpr.
     */
    case e: ParamDefaultExpr => {false}

    /**
     * Case for AST node class ParamRequiredExpr.
     */
    case e: ParamRequiredExpr => {false}

    /**
     * Case for AST node class ThisExpr.
     */
    case e: ThisExpr => {false}

    /**
     * Case for AST node class ThisFieldExpr.
     */
    case e: ThisFieldExpr => {false}

    /**
     * Case for AST node class ThisFieldVarExpr.
     */
    case e: ThisFieldVarExpr => {false}

    /**
     * Case for AST node class ThisMethodExpr.
     */
    case e: ThisMethodExpr => {false}

    /**
     * Case for AST node class ThisMethodVarExpr.
     */
    case e: ThisMethodVarExpr => {false}

    /**
     * Case for AST node class ToArrayExpr.
     */
    case e: ToArrayExpr => {false}

    /**
     * Case for AST node class ToBinaryExpr.
     */
    case e: ToBinaryExpr => {false}

    /**
     * Case for AST node class ToBooleanExpr.
     */
    case e: ToBooleanExpr => {false}

    /**
     * Case for AST node class ToDoubleExpr.
     */
    case e: ToDoubleExpr => {false}

    /**
     * Case for AST node class ToLongExpr.
     */
    case e: ToLongExpr => {false}

    /**
     * Case for AST node class ToObjectExpr.
     */
    case e: ToObjectExpr => {false}

    /**
     * Case for AST node class ToStringExpr.
     */
    case e: ToStringExpr => {false}

    /**
     * Case for AST node class ToUnicodeExpr.
     */
    case e: ToUnicodeExpr => {false}

    /**
     * Case for AST node class TraitParentClassConstExpr.
     */
    case e: TraitParentClassConstExpr => {false}

    /**
     * Case for AST node class TraitParentClassMethodExpr.
     */
    case e: TraitParentClassMethodExpr => {false}

    /**
     * Case for AST node class UnaryBitNotExpr.
     */
    case e: UnaryBitNotExpr => {false}

    /**
     * Case for AST node class UnaryCopyExpr.
     */
    case e: UnaryCopyExpr => {false}

    /**
     * Case for AST node class UnaryMinusExpr.
     */
    case e: UnaryMinusExpr => {false}

    /**
     * Case for AST node class UnaryNotExpr.
     */
    case e: UnaryNotExpr => {false}

    /**
     * Case for AST node class UnaryPlusExpr.
     */
    case e: UnaryPlusExpr => {false}

    /**
     * Case for AST node class UnaryPostIncrementExpr.
     */
    case e: UnaryPostIncrementExpr => {false}

    /**
     * Case for AST node class UnaryPreIncrementExpr.
     */
    case e: UnaryPreIncrementExpr => {false}

    /**
     * Case for AST node class UnaryRefExpr.
     */
    case e: UnaryRefExpr => {false}

    /**
     * Case for AST node class UnarySuppressErrorExpr.
     */
    case e: UnarySuppressErrorExpr => {false}

    /**
     * Case for AST node class UnaryUnsetExpr.
     */
    case e: UnaryUnsetExpr => {false}

    /**
     * Case for AST node class VarExpr.
     */
    case e: VarExpr => {false}

    /**
     * Case for AST node class VarState.
     */
    //case e: VarState => {false}

    /**
     * Case for AST node class VarTempExpr.
     */
    case e: VarTempExpr => {false}

    /**
     * Case for AST node class VarUnsetExpr.
     */
    case e: VarUnsetExpr => {false}

    /**
     * Case for AST node class VarVarExpr.
     */
    case e: VarVarExpr => {false}

    case _ => {false}
  }

}

object ASTVisitor {

  var rootPath: Path = null

}
