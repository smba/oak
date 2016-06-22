package edu.cmu.cs.oak.value

import com.caucho.quercus.Location
import edu.cmu.cs.oak.core.Interpreter
import java.net.URL
import java.nio.file.Path

case class StringValue(value:String, var location: Location) extends OakValue {

  override def toString() = value
  
  def setLocation(location: Location) {
    this.location = location 
  }
  def getLocation(location: Location): Location = location
  
  override def toXml = {
    <string>

    </string>
  }
}