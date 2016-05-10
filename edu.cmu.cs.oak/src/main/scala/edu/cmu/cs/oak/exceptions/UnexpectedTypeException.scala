package edu.cmu.cs.oak.exceptions

import com.caucho.quercus.expr.Expr

class UnexpectedTypeException(e: Expr, context: String) extends TypeException {
  override def toString(): String = "Expression \"" + e + "\" of type \"" + e.getClass + "\" is unexpected."
}