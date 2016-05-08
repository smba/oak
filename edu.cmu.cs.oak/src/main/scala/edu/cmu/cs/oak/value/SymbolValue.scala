package edu.cmu.cs.oak.value

import com.caucho.quercus.expr.Expr

/**
  *
  */
case class SymbolValue(e: Expr, id: Long) extends SymbolicValue {
  //System.err.println(">>> Symbol Σ["+id+"] introduced for expression " + e +".")
  override def toString = "Σ["+id+"]"
}
