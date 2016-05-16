package edu.cmu.cs.oak.value

import com.caucho.quercus.expr.Expr
import com.caucho.quercus.Location

/**
  *
  */
case class SymbolValue(e: Expr, id: Long) extends SymbolicValue {
  
  System.err.println("> Introducing symbol σ[" + id + "] for expression " + e + ".")
  
  override def toString = "σ["+id+"]"
}
