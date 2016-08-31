package edu.cmu.cs.oak.value

import com.caucho.quercus.Location


case class StringValue(value: String, var file: String, var lineNr: Int) extends OakValue {

  var context = StringLiteralContext.MISC
  var fdef: (String, (String, Int)) = ("", ("", 0))
  
  override def toString() = value // + "@"+file+":"+lineNr
  
  override def isEmpty() = false
  
  def this(value: String, location: Location) {
    this(value, "", 0)
    this.setLocation(location)
  }

  def setLocation(location: Location) {
    if (location != null) {
      this.file = location.getFileName()
      this.lineNr = location.getLineNumber()
    } else {
      this.file = ""
      this.lineNr = 0
    }

  }

  def getFileName(): String = file

  def getLineNr(): Int = lineNr

}

// analysis only
object StringLiteralContext extends Enumeration {
  val TEMPLATE, FDEFINITION, MISC  = Value
}