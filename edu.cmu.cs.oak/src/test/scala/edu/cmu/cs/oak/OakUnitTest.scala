package edu.cmu.cs.oak

import java.io.{ File, PrintWriter }
import java.nio.file.{ Path, Paths }

import edu.cmu.cs.oak.core.{ ControlCode, OakEngine, OakInterpreter }
import edu.cmu.cs.oak.env.Environment
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import edu.cmu.cs.oak.nodes.DNode

/**
 * This test class contains unit tests based on PHP snippets and its
 * corresponding expected behavior. We test expressions and statements
 * for basic code coverage.
 */

//RunWith(classOf[JUnitRunner]) //optional
object OakUnitTest extends App {
  // engine and interpreter instance for testing
  val engine = new OakEngine()
  val interpreter = new OakInterpreter()

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

  val env = loadAndExecute(url("model_project/index.php"))
  val pw = new PrintWriter(new File("/home/stefan/git/oak/edu.cmu.cs.oak/out/output.xml"))
//  pw.write(env._2.getOutputAsPrettyXML())
  
  println(env._2.ifdefy.mkString("\n"))
  pw.close
}
