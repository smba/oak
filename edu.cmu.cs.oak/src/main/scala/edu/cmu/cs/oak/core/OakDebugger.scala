package edu.cmu.cs.oak.core

import scala.collection.mutable.ListBuffer
import org.slf4j.LoggerFactory
import edu.cmu.cs.oak.env.Environment

trait OakDebugger {
  
  val logger = LoggerFactory.getLogger(classOf[OakDebugger])
  
  val breakpoints = ListBuffer[(String, Int)]()
  
  // BREA K  P   O    I     N      T       S!
  // ----------------
  breakpoints.+=(("/home/stefan/git/oak/edu.cmu.cs.oak/bin/drupal/includes/menu.inc",525))
  // ----------------
  
  def stall(breakpoint: (String, Int), env: Environment) {
    env.info()
    logger.debug(s"Stopped for ${breakpoint._1} at line ${breakpoint._2}")
  }

}