package edu.cmu.cs.oak.value

import edu.cmu.cs.oak.env.Delta
import edu.cmu.cs.oak.env.Constraint
import edu.cmu.cs.oak.core.SymbolFlag
import edu.cmu.cs.oak.env.OakHeap

case class Choice(p: Constraint, var v1: OakValue, var v2: OakValue) extends SymbolicValue {

  assert(!((v1 == null || v1.isInstanceOf[NullValue]) && (v2 == null || v2.isInstanceOf[NullValue])))
  
  def getConstraint(): Constraint = p

  def getV1(): OakValue = v1
  def getV2(): OakValue = v2
  
  def setV1(v1: OakValue) { this.v1 = v1 }
  def setV2(v2: OakValue) { this.v2 = v2 }
  
  //
  def getElements(): Set[OakValue] = {
    (v1 match {
      case c: Choice => c.getElements
      case v: OakValue => Set(v)
    }) union (v2 match {
      case c: Choice => c.getElements
      case v: OakValue => Set(v)
    })
  }
  
  def applyToObjects(func: ObjectValue => Unit) {
    v1 match {
      case c: Choice => c.applyToObjects(func)
      case o: ObjectValue => func(o)
      case _ => {}
    }
  }
  
  override def isEmpty() = (v1.isEmpty() && v2.isEmpty())

}

object Choice {
  
  def optimized(p: Constraint, v1: OakValue, v2: OakValue): OakValue = {

    if ((v1 == null || v1.isInstanceOf[NullValue]) && (v2 == null || v2.isInstanceOf[NullValue])) {
      NullValue("optimized choice")
    }
    else {
      Choice(p, v1, v2)
    }
  }
  
  /**
   * Utility method that discards unnecessary choice elements.
   */
  def optimize(value: OakValue): OakValue = {
    value match {
      case p: Choice => {
        /*
         *  		p						 p'' with (c && c')
 				 *  	 / \					/ \
				 *  	p'  ⊥  --> 	 X	 ⊥
				 *   / \
				 *  X   ⊥
         */
        if (p.v1.isInstanceOf[Choice] && p.v2.isEmpty() && p.v1.asInstanceOf[Choice].v2.isEmpty() && !p.v1.asInstanceOf[Choice].v1.isEmpty()) {
          val p1 = p.v1.asInstanceOf[Choice]
          Choice(p.getConstraint() AND p1.getConstraint(), optimize(p1.v1), NullValue("c"))
        } 
        
        /*
         *  		p						 p'' with (c && !c')
 				 *  	 / \					/ \
				 *  	p'  ⊥  --> 	 X	 ⊥
				 *   / \
				 *  ⊥   X
         */ 
        else if (p.v1.isInstanceOf[Choice] && p.v2.isEmpty() && p.v1.asInstanceOf[Choice].v1.isEmpty() && !p.v1.asInstanceOf[Choice].v2.isEmpty()) {
          val p1 = p.v1.asInstanceOf[Choice]
          Choice(p.getConstraint() AND p1.getConstraint().NOT, optimize(p1.v2), NullValue("c"))
        } 
        
        /*
         *  		p						 p'' with (!c && c')
 				 *  	 / \					/ \
				 *  	⊥   p' --> 	 X	 ⊥
				 *   		 / \
				 *      X   ⊥
         */ 
        else if (p.v2.isInstanceOf[Choice] && p.v1.isEmpty() && p.v2.asInstanceOf[Choice].v2.isEmpty() && !p.v2.asInstanceOf[Choice].v1.isEmpty()) {
          val p1 = p.v2.asInstanceOf[Choice]
          Choice(p.getConstraint().NOT AND p1.getConstraint(), optimize(p1.v1), NullValue("c"))
        } 
        
        /*
         *  		p						 p'' with (!c && !c')
 				 *  	 / \					/ \
				 *  	⊥   p' --> 	 X	 ⊥
				 *   		 / \
				 *      ⊥   X
         */ 
        else if (p.v2.isInstanceOf[Choice] && p.v1.isEmpty() && p.v2.asInstanceOf[Choice].v1.isEmpty() && !p.v2.asInstanceOf[Choice].v2.isEmpty()) {
          val p1 = p.v2.asInstanceOf[Choice]
          Choice(p.getConstraint().NOT AND p1.getConstraint().NOT, optimize(p1.v2), NullValue("c"))
        } 
        
        /*
         *  		p						 
 				 *  	 / \  -->   ⊥
				 *  	⊥   ⊥
         */ 
        else if (p.v1.isEmpty() && p.v2.isEmpty()) {
          NullValue("c")
        } 
        
        /*
         *  		p						 
 				 *  	 / \  -->   s
				 *  	s   s
         */
        else if (p.v1.isInstanceOf[SymbolValue] && p.v2.isInstanceOf[SymbolValue]) {
          SymbolValue("", OakHeap.getIndex, SymbolFlag.DUMMY)
        } 
        
        else {
          Choice(p.getConstraint(), optimize(p.v1), optimize(p.v2))
        }
      } 
      case _ => value
    }
  }
  
}