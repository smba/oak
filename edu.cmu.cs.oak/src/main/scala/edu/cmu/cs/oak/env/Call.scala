package edu.cmu.cs.oak.env

import java.nio.file.Path

case class Call(name: String, location: (Path, Int)) { 
  override def toString() = name + " (" + location._1 + ":" + location._2 + ")"  
}
