package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.nodes.DNode

trait EnvListener {
  //def receiveOutput(value: OakValue)
  def receiveOutput(value: DNode)
}