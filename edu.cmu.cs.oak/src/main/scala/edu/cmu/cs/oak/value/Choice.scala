package edu.cmu.cs.oak.value

import edu.cmu.cs.oak.env.{Constraint, Environment}


case class Choice(p: Constraint, var v1: OakValue, var v2: OakValue) extends SymbolicValue {

  var depth: Int = Math.max( (if (v1.isInstanceOf[Choice]) v1.asInstanceOf[Choice].depth + 1 else 1), (if (v2.isInstanceOf[Choice]) v2.asInstanceOf[Choice].depth + 1 else 1))

  def getConstraint(): Constraint = p

  def getV1(): OakValue = v1
  def getV2(): OakValue = v2

  def setV1(v1: OakValue) { this.v1 = v1 }
  def setV2(v2: OakValue) { this.v2 = v2 }

  override def toString(): String = {
    s"Choice ${depth}"
  }

  override def hashCode(): Int = {
    42 + (if (v1 != null) v1.hashCode() else 0) + (if (v2 != null) v2.hashCode() else 0)
  }
  
  override def equals(that: Any): Boolean = {
    if (!that.isInstanceOf[Choice]) {
      return false
    }
    val thatChoice = that.asInstanceOf[Choice]
    return ((p equals thatChoice.getConstraint()) && (v1 equals thatChoice.v1) && (v2 equals thatChoice.v2))
  }
  //
  def getElements(): Set[OakValue] = {
    (v1 match {
      case c: Choice => c.getElements
      case v: OakValue => Set(v)
      case _ => Set[OakValue]()
    }) union (v2 match {
      case c: Choice => c.getElements
      case v: OakValue => Set(v)
      case _ => Set[OakValue]()
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

  def getSize(): Int = {
    (v1 match {
      case c: Choice => c.getSize()
      case _ => 1
    }) + (v2 match {
      case c: Choice => c.getSize()
      case _ => 1
    })
  }

}

object Choice {

  def optimized(p: Constraint, v1: OakValue, v2: OakValue): OakValue = {
    if (((v1 == null || (v1 equals NullValue)) && (v2 == null || (v2 equals NullValue)))) {
      NullValue
    } else if ((v1 != null) && (v1.hashCode() equals v2.hashCode())){ // TODO safe?
      v1
    } else {
      Choice(p, v1, v2)
    }
  }

  def arrayLookup(c: OakValue, indices: List[OakValue], env: Environment): OakValue = {
    c match {
      case c: Choice => {
        val al1 = arrayLookup(c.v1, indices, env)
        val al2 = arrayLookup(c.v2, indices, env)
        if (al1 equals al2) {
          al1
        } else {
          Choice(c.p, al1, al2)
        }
      }
      case av: ArrayValue => {
        if (indices.size == 1) {
          av.get(indices.head, env)
        } else {
          arrayLookup(av.get(indices.head, env), indices.tail, env)
        }
      }
      case v: OakValue => v
    }
  }

}