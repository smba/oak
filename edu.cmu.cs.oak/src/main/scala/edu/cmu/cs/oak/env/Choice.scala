package edu.cmu.cs.oak.env

case class Choice(p:AnyRef, v1:OakValue, v2:OakValue) extends OakValue {
  
}