package edu.cmu.cs.oak.env

case class Constraint(text: String) {
  
  def AND(that: Constraint): Constraint = {
    Constraint(this.text + " && " + that.text)
  }
  
  def NOT(): Constraint = {
    Constraint("!("+this.text+")")
  }
}