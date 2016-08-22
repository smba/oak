package edu.cmu.cs.oak.value

import edu.cmu.cs.oak.env.Constraint
import edu.cmu.cs.oak.env.Environment
import de.fosd.typechef.featureexpr.bdd.BDDFeatureExprFactory
import edu.cmu.cs.oak.env.OakHeap
import edu.cmu.cs.oak.core.SymbolFlag

abstract class IChoice extends SymbolicValue {
  
  def map(f: OakValue => OakValue): IChoice
  
}

object Choice {

  def optimized(p: Constraint, v1: OakValue, v2: OakValue): IChoice = {
    val a = v1 match {
      case mc: MapChoice => mc.addConstraint(p)
      case _ => new MapChoice(Map(v1 -> p))
    }
    val b = v2 match {
      case mc: MapChoice => mc.addConstraint(p.NOT())
      case _ => new MapChoice(Map(v2 -> p.NOT()))
    }
    return a ++ b
  }
  
  def arrayLookup(c: OakValue, indices: List[OakValue], env: Environment): OakValue = {
    c match {
      case mc: MapChoice => {
        new MapChoice(mc.toMap().map {
          case (v, ps) => (Choice.arrayLookup(v, indices, env), ps)
        })
      }
      case av: ArrayValue => {
        if (indices.size == 1) {
          av.get(indices.head, env)
        } else {
          arrayLookup(av.get(indices.head, env), indices.tail, env)
        }
      }
      case _ => NullValue
    }
  }
}



class MapChoice(private val entries: Map[OakValue, Constraint]) extends IChoice {

  def addConstraint(p: Constraint): MapChoice = {
    new MapChoice(entries.map {
      case (v, c) => (v, c.AND(p)) 
    })
  }
  
  override def map(f: OakValue => OakValue): MapChoice = {
    
    //#ifndef FIRST_CHOICE_ELEMENT
    val newEntries = entries.map {
      case (v, ps) => (f(v), ps) 
    }
    //#else
//@    var newEntries = {
//@      val objects = entries.filterKeys { v => v.isInstanceOf[ObjectValue] }
//@      if (objects.isEmpty) {
//@        Map[OakValue, Constraint]()
//@      } else {
//@        Map( f(objects.head._1) -> objects.head._2)
//@      }
//@    }
//@    
//@    
    //#ifdef SYMBOLIC_CHOICE_ELEMENT
//@    newEntries = newEntries + ( f(SymbolValue("ja", OakHeap.getIndex, SymbolFlag.DUMMY)) -> new Constraint(BDDFeatureExprFactory.createDefinedExternal("isEmpty()")))
    //#endif
    //#endif
    new MapChoice(newEntries)
  }

  def contains(v: OakValue): Boolean = {
    entries.keysIterator.contains(v)
  }
  
  def getConstraint(v: OakValue): Constraint = entries.get(v).get

  // Merges two map choices
  def ++(that: MapChoice): MapChoice = {
    var r = entries
    for ((v, c) <- that.toMap()) {
      if (r contains v)
        r = r + (v -> (c.OR(r.get(v).get))) 
      else
        r = r + (v -> c)
    }
    new MapChoice(r)
  }

  // get simple map
  def toMap() = entries

  def getSize() = entries.size
  
  override def isEmpty() = entries.isEmpty
  override def hashCode(): Int = entries.hashCode()
  override def toString(): String = entries.toString()
}
