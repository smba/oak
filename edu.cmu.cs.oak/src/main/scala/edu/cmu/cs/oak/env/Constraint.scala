package edu.cmu.cs.oak.env

import de.fosd.typechef.featureexpr.FeatureExpr
import de.fosd.typechef.featureexpr.bdd.BDDFeatureExpr

class Constraint(expr: FeatureExpr) {
  
  def get(): FeatureExpr = expr
  
  def AND(that: Constraint): Constraint = {
    new Constraint(expr.and(that.get))  
  }
  
  def OR(that: Constraint): Constraint = {
   new Constraint(expr.or(that.get))
  }
  
  def NOT(): Constraint = {
    new Constraint(expr.not())
  }
  
}
