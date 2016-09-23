package edu.cmu.cs.oak.analysis

import java.io.File
import java.io.PrintWriter
import java.nio.file.Path
import java.nio.file.Paths

import edu.cmu.cs.oak.core.ControlCode
import edu.cmu.cs.oak.core.OakEngine
import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.env.Environment

/**
 * To run this file, specify the input file and an output file.
 */
object RunOakForFile extends App {
  
  // engine for loading and parsing files
  val engine = new OakEngine()
  
  // interpreter instance
  val interpreter = new OakInterpreter()

  val INPUT_FILE = "drupal/index.php";
  
  val OUTPUT_FILE = "/home/stefan/git/oak/edu.cmu.cs.oak/out/output.xml";
  
  /**
   * Read a PHP source code from file, parses & executes it.
   * 
   * @param script PHP source code file
   * @return (ControlCode, Environment)
   */
  def loadAndExecute(path: Path): (ControlCode.Value, Environment) = {
    return interpreter.execute(path)
  }

  /**
   * Utility method to resolve paths.
   * 
   * @param  relative path to the input file / entry point
   * @return absolute path to the input file
   */
  def url(fileName: String): Path = {
    Paths.get(getClass.getResource("/" + fileName).getPath)
  }

  /* --------------------------- MAIN ------------------------------ */
  
  // Execute the system / entry point
  val environment = loadAndExecute(url(OUTPUT_FILE))
  
  new PrintWriter(new File(OUTPUT_FILE)) {
    
    // Option A: XML output
    write(environment._2.getOutputAsPrettyXML())
    
    // Option B: if/else-like output
    write(environment._2.ifdefy().mkString("\n"))
    
    close()
  }
  
}