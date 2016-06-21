package edu.cmu.cs.oak

import java.net.URL

import scala.io.Source

import edu.cmu.cs.oak.analysis.PrecisionCalculator
import edu.cmu.cs.oak.core.OakEngine
import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.nodes.DNodeParser
import java.nio.file.Path
import java.io.PrintWriter
import java.io.File

/**
 * First, an URL is loaded. For this URL, we retrieve all available string
 * literals by traversing the AST (PrecisionCalculator, ASTVisitor).
 * Second, we load and symbolically execute the program specified by
 * the URL and store the resulting environment.
 * Third, we load the test oracle corresponding to the loaded URL
 * (named by convention) and parse the XML-based Dmodel and compare
 * it with the Dmodel retrieved from the result environment.
 * If these two Dmodels match, their output is equal; if they do not
 * match, either an error occured or the test oracle is outdated.
 */
object RegressionTest {

  
  lazy val engine = new OakEngine
  lazy val interpreter = new OakInterpreter

  /**
   * @param URL to test
   * @return (passed?, (found, available))
   */
  /*def test(url: Path): (Boolean, (Int, Int)) = {
  
    // get available literals
    val available = PrecisionCalculator.availableLiterals(url)

    // symbolically execute the program
    val env = interpreter.execute(url)

    // get found literals 
    val found = DNode.extractStringLiterals(env._2.getOutputModel)

    val path = url.toString + "t"

    DNodeParser.init(url.getParent.toString)

    val oracle = DNodeParser.createConcatNodeFromXml(scala.xml.XML.loadString(Source.fromFile(path).getLines.mkString("")))

    val outputModel = env._2.getOutputModel()

    val matches = oracle compare outputModel
    
    println(oracle.getChildren().size, outputModel.getChildren().size)
    val precision = PrecisionCalculator.computePrecision(found, available)

    return (matches, precision)
  }*/
	
}