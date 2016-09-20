package edu.cmu.cs.oak.value

import com.caucho.quercus.expr.Expr
import com.caucho.quercus.Location
import edu.cmu.cs.oak.core.SymbolFlag
import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.env.OakHeap

// TODO remove default parameter in the near future
case class SymbolValue(e: String, id: Long = OakHeap.getIndex(), flag: SymbolFlag.Value= SymbolFlag.DUMMY) extends SymbolicValue {

  override def toString() = {
    s"Symbol[${e}]"  
  }
  override def isEmpty() = false
  
}
