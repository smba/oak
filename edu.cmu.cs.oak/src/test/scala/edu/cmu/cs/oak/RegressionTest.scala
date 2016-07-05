package edu.cmu.cs.oak

import java.net.URL

import scala.io.Source

import edu.cmu.cs.oak.core.OakEngine
import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.nodes.DNodeParser
import java.nio.file.Path
import java.io.PrintWriter
import java.io.File
import java.nio.file.Paths
import edu.cmu.cs.oak.core.ControlCode

/**
 * TODO Comment
 */
object RegressionTest {

  /**
   * @param URL to test
   * @return (passed?, (found, available))
   */
  def test(fileName: String) {
    val engine = new OakEngine
    val interpreter = new OakInterpreter

    val res = interpreter.execute(url(fileName))._2
    val parsed = DNodeParser.parseNode(scala.xml.XML.load(url(fileName + "t").toString))
    assert(parsed compare res.output)
  }
  
  def loadAndExecute(fileName: String): (ControlCode.Value, Environment) = {
    val engine = new OakEngine
    val interpreter = new OakInterpreter
    return interpreter.execute(url(fileName))
  }

  def url(fileName: String): Path = {
    Paths.get(getClass.getResource("/" + fileName).getPath)
  }

}