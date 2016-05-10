package edu.cmu.cs.oak.exceptions

import com.caucho.quercus.expr.Expr

class UnimplementedException(a: AnyRef, context: String) extends OakException {
  override def toString(): String = "Execution/Evaluation for \"" + a + "\" is not implemented."
}