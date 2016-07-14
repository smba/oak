package edu.cmu.cs.oak

import scala.xml.PrettyPrinter

import org.junit.runner.RunWith
import org.scalatest.FunSpec
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.core.OakEngine
import java.nio.file.Paths
import edu.cmu.cs.oak.env.Environment
import java.nio.file.Path
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.BooleanValue
import edu.cmu.cs.oak.value.Choice
import scala.collection.immutable.Stack
import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.value.DoubleValue
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.BooleanValue
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.DoubleValue
import edu.cmu.cs.oak.value.SymbolValue
import edu.cmu.cs.oak.core.ControlCode
import java.io.PrintWriter
import java.io.File
import edu.cmu.cs.oak.nodes.DNodeParser

/**
 * This test class contains unit tests based on PHP snippets and its
 * corresponding expected behavior. We test expressions and statements
 * for basic code coverage.
 */

//@RunWith(classOf[JUnitRunner]) //optional
object OakUnitTest extends App {

  // engine and interpreter instance for testing
  val engine = new OakEngine()
  val interpreter = new OakInterpreter()

  //val pp = new PrettyPrinter(200, 0)

  /**
   * Read a PHP source code from file, parses & executes it.
   *
   * @param script PHP source code file
   * @return (ControlCode, Environment)
   */
  def loadAndExecute(path: Path): (ControlCode.Value, Environment) = {
    return interpreter.execute(path)
  }

  /* utility method */
  def url(fileName: String): Path = {
    Paths.get(getClass.getResource("/" + fileName).getPath)
  }

  //val env = loadAndExecute(url("testScripts/constants.php"))
  val env = loadAndExecute(url("wordpress/wp-admin/index.php"))
  //val after = Instant.now()
  //println("Symbolic execution successful, duration: " + Duration.between(before, after).toString())
  val groups = OakInterpreter.symbolSet.groupBy { s => s.flag }
  groups.map{case (k, v) => (k -> v.size)}.foreach {case (k, v) => println(k + ", " + v)}
  
  val pw = new PrintWriter(new File("/home/stefan/Desktop/output2.xml"))
  pw.write(env._2.getOutputAsPrettyXML())
  pw.close

}