package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakVariable

trait VarHeap {
  
  def insert(variable: OakVariable, value: OakValue)
  def extract(variable: OakVariable): OakValue
  def clear()
  def getVariables(): Set[OakVariable]
  
}