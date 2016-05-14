package edu.cmu.cs.oak.value

import com.caucho.quercus.Location

case class StringValue(value:String) extends OakValue {
  var loc: Location = null
  override def toString() = value
  
  def setLocation(location: Location) {loc = location }
  def getLocation(): Location = loc
}