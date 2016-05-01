package edu.cmu.cs.oak

import com.caucho.quercus.program.QuercusProgram
import com.caucho.quercus.statement.BlockStatement
import com.caucho.quercus.statement.Statement
import com.caucho.quercus.statement.EchoStatement
import com.caucho.el.Expr

object Interpreter extends App {

  def eval(element: AnyRef): String = element match {

    case p: QuercusProgram => {
      eval(p.getStatement)
    }
    
    case s: BlockStatement => {
      s.getStatements.foreach {
        stmt => eval(stmt)
      }
      ""
    }
    
    case s: EchoStatement => {
      val expr = Interpreter.accessField(s, "_expr").asInstanceOf[Expr]
      expr.toString
    }
  }
  
  /**
   * Utility method to access private fields.
   * 
   * TODO replace by aspects in the future
   */
  def accessField(obj:AnyRef, name:String): Object = {
    val field = try {
      obj.getClass.getDeclaredField(name)
    } catch {
      case e: NoSuchFieldException => throw new RuntimeException(e)
    }
    val value = try {
      field.get(obj)
    } catch {
      case e: IllegalAccessException => throw new RuntimeException(e)
    }
    value
  }
  
  val engine = new OakEngine()
  val p = engine.loadFromScript("<?php echo 'Bener';?>")
  eval(p)

}
