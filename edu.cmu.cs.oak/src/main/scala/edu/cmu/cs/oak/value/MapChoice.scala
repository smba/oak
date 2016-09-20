package edu.cmu.cs.oak.value

import de.fosd.typechef.featureexpr.bdd.BDDFeatureExprFactory
import edu.cmu.cs.oak.core.SymbolFlag
import edu.cmu.cs.oak.env.Constraint
import edu.cmu.cs.oak.env.Environment

abstract class IChoice extends SymbolicValue {
  
  /**
   * Applies a functtion f fo the choice elements.
   * @param f Function with argument 
   */
  def map(f: OakValue => OakValue): IChoice
  
}

class MapChoice(private val entries: Map[OakValue, Constraint]) extends IChoice {
  
  def deepCopy(env: Environment): MapChoice = {
    val nentries = entries.map {
      case (v, c) => (v match {
        case av: ArrayValue => av.deepCopy(env)
        case ov: OakValue => v
      }, c)
    }
    new MapChoice(nentries)
  }

  def addConstraint(p: Constraint): MapChoice = {
    new MapChoice(entries.map {
      case (v, c) => (v, c.AND(p)) 
    })
  }
  
  override def map(f: OakValue => OakValue): MapChoice = {
    
    //#ifndef FIRST_CHOICE_ELEMENT
//@    val newEntries = entries.map {
//@      case (v, ps) => (f(v), ps) 
//@    }
    //#else
    var newEntries = {
      val objects = entries.filterKeys { v => v.isInstanceOf[ObjectValue] }
      if (objects.isEmpty) {
        Map[OakValue, Constraint]()
      } else {
        Map( f(objects.head._1) -> objects.head._2)
      }
    }
    
    
    //#ifdef SYMBOLIC_CHOICE_ELEMENT
    newEntries = newEntries + ( f(SymbolValue("ja")) -> new Constraint(BDDFeatureExprFactory.createDefinedExternal("isEmpty()")))
    //#endif
    //#endif
    new MapChoice(newEntries)
  }

  def contains(v: OakValue): Boolean = {
    entries.keysIterator.contains(v)
  }
  
  def getConstraint(v: OakValue): Constraint = entries.get(v).get

  // Merges this w/ another MapChoice
  def ++(that: MapChoice): MapChoice = {
    var r = entries
    for ((v, c) <- that.toMap()) {
      if (r contains v) {
        r = r + (v -> (c.OR(r.get(v).get))) 
      } else {
        r = r + (v -> c)
      }
    }
    new MapChoice(r)
  }

  // get simple map
  def toMap() = entries

  def getSize() = entries.size
  
  override def isEmpty() = entries.isEmpty
  override def hashCode(): Int = entries.hashCode()
  override def toString(): String = entries.toString
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
  
  // TODO Move this to the interpreter maybe?
  def arrayLookup(c: OakValue, indices: List[OakValue], env: Environment): OakValue = {
    c match {
      case mc: MapChoice => {
        
        var mentriez = Map[OakValue, Constraint]()
        mc.toMap().foreach {
          case (v, c) => {
            val value = Choice.arrayLookup(v, indices, env)
            if (mentriez.keySet.contains(value)) {
              mentriez += (value -> mentriez.get(value).get.OR(c))
            } else {
              mentriez += (value -> c)
            }
          }
        }
        new MapChoice(mentriez)
      }
      case av: ArrayValue => {
        if (indices.size == 1) {
          av.get(indices.head, env)
        } else {
          arrayLookup(av.get(indices.head, env), indices.tail, env)
        }
      }
      case s: SymbolValue => {
        SymbolValue(s"Symbol.lookup{${indices mkString ","}}")
      }
      case _ => SymbolValue(s"OakValue.lookup{${indices mkString ","}}")
    }
  }
}
