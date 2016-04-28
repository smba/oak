package edu.cmu.cs.oak

import com.caucho.quercus.program.QuercusProgram
import com.caucho.quercus.statement.BlockStatement

object Interpreter {

  def eval(element: AnyRef): String = element match {

    /**
     * Execution for BlockStatements 
     */
    case s: BlockStatement => {
      var res = ""
      s.getStatements.foreach {
        stmt => res += eval(stmt)
      }
      res
    }
  }

}