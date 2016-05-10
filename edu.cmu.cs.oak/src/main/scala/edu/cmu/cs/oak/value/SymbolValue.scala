package edu.cmu.cs.oak.value

import com.caucho.quercus.expr.Expr

/**
  *
  */
case class SymbolValue(e: Expr, id: Long) extends SymbolicValue {
  override def toString = "Σ["+id+"]"
}
