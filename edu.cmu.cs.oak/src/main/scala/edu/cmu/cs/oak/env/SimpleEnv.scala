package edu.cmu.cs.oak.env

import scala.collection.immutable.Stack

class SimpleEnv(parent: EnvListener, calls: Stack[String], constraint: String) extends Environment(parent: EnvListener, calls: Stack[String], constraint: String) {
  
}