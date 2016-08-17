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
import org.slf4j.LoggerFactory
import scala.util.Random
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Coverage {

}

object Coverage extends App {

  // engine and interpreter instance for testing
  var engine = new OakEngine()
  var interpreter = new OakInterpreter()

  val logger = LoggerFactory.getLogger(classOf[Coverage])

  val ADDRESSBOOK = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/addressbook")
  val SCHOOLMATE = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate")
  val TIMECLOCK = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/timeclock")
  val UPB = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/upb")
  val WEBCHESS = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/webchess")

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
    val p = sample.zipWithIndex.map { case (sv, i) => i + "|\"" + sv.value.replaceAll("\"", "'") + "\"|" + sv.file + "|" + sv.lineNr }.mkString("\n")
    pw.write("#|String Value|Location|Line Number|\n" + p)
    pw.close
  }

  /**
   *
   */
  def findProjectLiterals(entryPoints: Seq[Path]): Set[StringValue] = {
    var literalSet = Set[StringValue]()
    entryPoints.foreach {
      ep =>
        {
          interpreter = new OakInterpreter()
          literalSet = literalSet union DNode.extractStringLiterals(loadAndExecute(ep)._2.output)
          
          //#ifdef AbstractLogging
//@          logger.info(s" Found ${literalSet.size} literals so far")
          //#endif
        }
    }
    literalSet
  }

  def getProjectLiterals(file: File): Set[StringValue] = {
    getPHPFiles(file).map { f => (new ASTVisitor(Paths.get(f.getAbsolutePath))).retrieveStringLiterals() }.foldLeft(Set[StringValue]())(_ union _)
  }

  def getPHPFiles(file: File): Stream[File] = {
    def getFileTree(f: File): Stream[File] =
      f #:: (if (f.isDirectory) f.listFiles().toStream.flatMap(getFileTree)
      else Stream.empty)
    val s = getFileTree(file).filter(_.getName.endsWith(".php")).toList.map(f => f.toPath())
    getFileTree(file).filter(_.getName.endsWith(".php"))
  }

  def getCoverage(projectPath: File, entryPoints: Seq[Path], relevant: StringValue => Boolean): (Double, (Int, Int)) = {
    val projectLiterals = getProjectLiterals(projectPath) //.map(s => s.value)
    val relevant_projectLiterals =  projectLiterals.filter { lit => relevant(lit) }
    val foundLiterals = findProjectLiterals(entryPoints) //.map(s => s.value)
    val relevant_foundLiterals = foundLiterals.filter { lit => relevant(lit) }
    //    val foundLiterals = AnalysisService.analyzeProject(projectPath).filter { lit => relevant(lit) }
    
    //#ifdef AbstractLogging
//@    println(s"Projekt hat ${projectLiterals.size} Literale")
//@    println(s"Project hat ${relevant_projectLiterals.size} relevante Literale")
//@    println(s"Analyse fand ${foundLiterals.size}  Literale")
//@    println(s"Analyse fand ${relevant_foundLiterals.size} relevante Literale")
    //#endif
    
    val relative_coverage = ((relevant_foundLiterals intersect relevant_projectLiterals).size * 1.0 / relevant_projectLiterals.size * 100)
    return (relative_coverage, (relevant_foundLiterals.size, relevant_projectLiterals.size))
  }

  def getSchoolmateCoverage() {
    var entrypoints = getPHPFiles(SCHOOLMATE).toList.map(f => f.toPath())
    println(s"SchoolMate: ${getCoverage(SCHOOLMATE, entrypoints, isRelevant)}")
  }

  def getAddressbookCoverage() {
    var entrypoints = getPHPFiles(ADDRESSBOOK).toList.map(f => f.toPath())
    println(s"AddressBook: ${getCoverage(ADDRESSBOOK, entrypoints, isRelevant)}")
  }

  def getTimeclockCoverage() {
    var entrypoints = getPHPFiles(TIMECLOCK).toList.map(f => f.toPath())
    println(s"TimeClock: ${getCoverage(TIMECLOCK, entrypoints, isRelevant)}")
  }

  def getUPBCoverage() {
    var entrypoints = getPHPFiles(UPB).toList.map(f => f.toPath())
    println(s"UPB: ${getCoverage(UPB, entrypoints, isRelevant)}")
  }

  def getWebchessCoverage() {
    var entrypoints = getPHPFiles(WEBCHESS).toList.map(f => f.toPath())
    println(s"WebChess: ${getCoverage(WEBCHESS, entrypoints, isRelevant)}")
  }

  def getWordpressCoverage() {
    var entrypoints = getPHPFiles(WORDPRESS).toList.map(f => f.toPath())
    println(s"WordPress: ${getCoverage(WORDPRESS, entrypoints, isRelevant)}")
  }

  def coverages() {
    getAddressbookCoverage()
    getSchoolmateCoverage()
    getTimeclockCoverage()
    getUPBCoverage()
    getWebchessCoverage()
    getWordpressCoverage()
  }

  coverages()
}
