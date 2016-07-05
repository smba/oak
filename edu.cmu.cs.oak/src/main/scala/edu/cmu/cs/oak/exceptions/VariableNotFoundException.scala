package edu.cmu.cs.oak.exceptions

class VariableNotFoundException(name: String) extends NotFoundException {
  override def toString() = name
}