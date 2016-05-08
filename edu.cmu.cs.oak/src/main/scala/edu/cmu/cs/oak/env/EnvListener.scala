package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.OakValue

trait EnvListener {
  def receiveOutput(value: OakValue)
  def receiveOutput(value: Seq[OakValue])
}