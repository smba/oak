package edu.cmu.cs.oak.analysis

import java.io.File
import java.nio.file.Paths

import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.core.ControlCode
import edu.cmu.cs.oak.core.OakEngine
import edu.cmu.cs.oak.env.Environment
import java.nio.file.Path
import edu.cmu.cs.oak.nodes.DNode
import scala.util.Random
import java.io.PrintWriter
import scala.collection.mutable.ListBuffer

class Coverage {

}

object Coverage extends App {
  
  // engine and interpreter instance for testing
  var engine = new OakEngine()
  var interpreter = new OakInterpreter()

  val SCHOOLMATE = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate")
    val WORDPRESS = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/wordpress")
  val isRelevant = (lit: StringValue) => ((lit.value contains '<') || (lit.value contains '.'))
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
  
  def getSample(n: Int, projects: Seq[File], path: Path) {
    val allLiterals = projects.map(p => getProjectLiterals(p)).fold(Set[StringValue]())(_ union _).toIterator
    val sample = Random.shuffle(allLiterals).take(n).toList
    
    val pw = new PrintWriter(path.toFile())
    val p = sample.zipWithIndex.map{case (sv, i) => i + "|\"" + sv.value.replaceAll("\"", "'") + "\"|" + sv.file + "|" + sv.lineNr}.mkString("\n")
    pw.write("#|String Value|Location|Line Number|\n" + p)
    pw.close
  }
  
  /**
   *
   */
  def findProjectLiterals(entryPoints: Seq[Path]): Set[StringValue] = {
    var literalSet = Set[StringValue]()
    entryPoints.foreach {
      ep => {
        interpreter = new OakInterpreter()
        literalSet = literalSet union DNode.extractStringLiterals(loadAndExecute(ep)._2.output)
      }
    }
    literalSet
  }
  
  def getProjectLiterals(file: File): Set[StringValue] = {
    getPHPFiles(file).map{f=>(new ASTVisitor(Paths.get(f.getAbsolutePath))).retrieveStringLiterals()}.foldLeft(Set[StringValue]())(_ union _)
  }
  
  def getPHPFiles(file: File): Stream[File] = {
    def getFileTree(f: File): Stream[File] = {
      f #:: (if (f.isDirectory) f.listFiles().toStream.flatMap(getFileTree)
      else Stream.empty)
    }
    getFileTree(file).filter(_.getName.endsWith(".php"))
  }
  
  def getCoverage(projectPath: File, entryPoints: Seq[Path], relevant: StringValue => Boolean): (Int, Int) = {
    val projectLiterals = getProjectLiterals(projectPath).filter { lit => relevant(lit) }//.map(s => s.value)
    val foundLiterals = findProjectLiterals(entryPoints).filter { lit => relevant(lit) }//.map(s => s.value)
    println((foundLiterals intersect projectLiterals).size *1.0 / projectLiterals.size * 100)
    (foundLiterals.size, projectLiterals.size)
  }
  
  def getSchoolmateCoverage() {
    var entrypoints = List(url("schoolmate/index.php"))
    println( getCoverage(SCHOOLMATE, entrypoints, isRelevant) )
  }
  
  def getWordpressCoverage() {
    var entrypoints = new ListBuffer[Path]()
    entrypoints += url("wordpress/index.php")
    entrypoints += url("wordpress/wp-admin/index.php")
    entrypoints += url("wordpress/wp-admin/install.php")
    println( getCoverage(WORDPRESS, entrypoints, isRelevant) )
  }
  
  getSchoolmateCoverage()
  getWordpressCoverage()
  
}