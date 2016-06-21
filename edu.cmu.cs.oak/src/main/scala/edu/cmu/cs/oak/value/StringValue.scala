package edu.cmu.cs.oak.value

import com.caucho.quercus.Location
import edu.cmu.cs.oak.core.Interpreter
import java.net.URL
import java.nio.file.Path

case class StringValue(value:String, location: Location) extends OakValue {

  //assert(location != null)
  
  var loc: (String, Int) = (null, 0)
  
  override def toString() = value
  
  def setLocation(location: (String, Int)) {
    //println(location)
    loc = location 
  }
  def getLocation(): (String, Int) = loc
  
  override def toXml = {
    <string>
			<content>{value}</content>
			<url>{if (loc._1 != null) loc._1.toString else "404"}</url>
			<line>{loc._2}</line>
    </string>
  }
}