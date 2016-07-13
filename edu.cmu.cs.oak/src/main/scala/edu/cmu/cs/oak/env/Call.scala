package edu.cmu.cs.oak.env

import java.nio.file.Path
import edu.cmu.cs.oak.value.OakValue

case class Call(name: String, location: (Path, Int), args: List[OakValue]) { 
  override def toString() = name + " (" + location._1 + ":" + location._2 + ")"  
}
