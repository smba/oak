package edu.cmu.cs.oak.value

import com.caucho.quercus.expr.Expr
import com.caucho.quercus.Location

/**
  *
  */
case class SymbolValue(e: Expr, id: Long) extends SymbolicValue {
  
  var loc: Location = null
  
  def setLocation(location: Location) {loc = location }
  def getLocation(): Location = loc
  
  System.err.println("Σ[" +id+"] for expression " + e)
  override def toString = "Σ["+id+"]"
}
