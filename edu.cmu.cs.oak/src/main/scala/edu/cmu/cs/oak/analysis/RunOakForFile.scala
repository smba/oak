package edu.cmu.cs.oak.analysis

import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.core.ControlCode
import java.io.PrintWriter
import edu.cmu.cs.oak.core.OakEngine
import java.nio.file.Paths
import edu.cmu.cs.oak.env.Environment
import java.nio.file.Path
import java.io.File

object RunOakForFile extends App {
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

  val env = loadAndExecute(url("schoolmate/index.php"))
  val pw = new PrintWriter(new File("/home/stefan/git/oak/edu.cmu.cs.oak/out/output.xml"))
  pw.write(env._2.getOutputAsPrettyXML())
  
//  println(env._2.getOutputAsPrettyXML())
  pw.close
}