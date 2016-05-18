package edu.cmu.cs.oak.value

import com.caucho.quercus.Location
import edu.cmu.cs.oak.core.Interpreter
import java.net.URL

case class StringValue(value:String) extends OakValue {
  
  var loc: (URL, Int) = (null, 0)
  
  override def toString() = value+"@"+loc._1+":"+loc._2
  
  def setLocation(location: (URL, Int)) {loc = location }
  def getLocation(): (URL, Int) = loc
  
  override def toXml = {
    <string>
			<content>{value}</content>
			<url>{if (loc._1 != null) loc._1.toString else "404"}</url>
			<line>{loc._2}</line>
    </string>
  }
}