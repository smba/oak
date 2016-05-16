package edu.cmu.cs.oak.value

import com.caucho.quercus.Location
import edu.cmu.cs.oak.core.Interpreter
import java.net.URL

case class StringValue(value:String) extends OakValue {
  
  var loc: (URL, Int) = null
  
  override def toString() = value
  
  def setLocation(location: (URL, Int)) {loc = location }
  def getLocation(): (URL, Int) = loc
}