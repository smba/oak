package edu.cmu.cs.oak.value

import com.caucho.quercus.Location
import edu.cmu.cs.oak.core.Interpreter
import java.net.URL

case class StringValue(value:String) extends OakValue {
  
  var loc: (URL, Int) = (null, 0)
  
  override def toString() = value
  
  def setLocation(location: (URL, Int)) {loc = location }
  def getLocation(): (URL, Int) = loc
  
  override def toXml = {
    <string>
			<content>{value}</content>
			<url>{loc._1}</url>
			<line>{loc._2}</line>
    </string>
  }
}