package edu.cmu.cs.oak.value

import com.caucho.quercus.expr.Expr
import com.caucho.quercus.Location

/**
  *
  */
case class SymbolValue(e: String, id: Long) extends SymbolicValue {
  
  
  override def toString = "σ"
  
  override def toXml = {
    <symbol>
      <id>{id}</id>
			<expression>{e}</expression>
    </symbol>
  }
}
