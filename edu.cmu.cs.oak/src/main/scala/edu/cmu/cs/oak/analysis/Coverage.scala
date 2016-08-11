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
          println(s"${ep.toAbsolutePath()}")
          interpreter = new OakInterpreter()
          literalSet = literalSet union DNode.extractStringLiterals(loadAndExecute(ep)._2.output)
          logger.info(s" Found ${literalSet.size} literals so far")
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

  def getCoverage(projectPath: File, entryPoints: Seq[Path], relevant: StringValue => Boolean): (Int, Int) = {
    val projectLiterals = getProjectLiterals(projectPath) //.map(s => s.value)
    val relevant_projectLiterals =  projectLiterals.filter { lit => relevant(lit) }
    val foundLiterals = findProjectLiterals(entryPoints) //.map(s => s.value)
    val relevant_foundLiterals = foundLiterals.filter { lit => relevant(lit) }
    //    val foundLiterals = AnalysisService.analyzeProject(projectPath).filter { lit => relevant(lit) }
    
    println(s"Projekt hat ${projectLiterals.size} Literale")
    println(s"Project hat ${relevant_projectLiterals.size} relevante Literale")
    println(s"Analyse fand ${foundLiterals.size}  Literale")
    println(s"Analyse fand ${relevant_foundLiterals.size} relevante Literale")
    
    println((foundLiterals intersect projectLiterals).size * 1.0 / projectLiterals.size * 100)
    (foundLiterals.size, projectLiterals.size)
  }

  def getSchoolmateCoverage() {
    var entrypoints = getPHPFiles(SCHOOLMATE).toList.map(f => f.toPath())
    println(getCoverage(SCHOOLMATE, entrypoints, isRelevant))
  }

  def getAddressbookCoverage() {
    var entrypoints = getPHPFiles(ADDRESSBOOK).toList.map(f => f.toPath())
    println(getCoverage(ADDRESSBOOK, entrypoints, isRelevant))
  }

  def getTimeclockCoverage() {
    var entrypoints = getPHPFiles(TIMECLOCK).toList.map(f => f.toPath())
    println(getCoverage(TIMECLOCK, entrypoints, isRelevant))
  }

  def getUPBCoverage() {
    var entrypoints = getPHPFiles(UPB).toList.map(f => f.toPath())
    println(getCoverage(UPB, entrypoints, isRelevant))
  }

  def getWebchessCoverage() {
    var entrypoints = getPHPFiles(WEBCHESS).toList.map(f => f.toPath())
    println(getCoverage(WEBCHESS, entrypoints, isRelevant))
  }

  def getWordpressCoverage() {
    var entrypoints = getPHPFiles(WORDPRESS).toList.map(f => f.toPath())
    println(getCoverage(WORDPRESS, entrypoints, isRelevant))
  }

  getWordpressCoverage

}

object AnalysisService {

  def analyzeProject(root: File): Set[StringValue] = {

    val pool: ExecutorService = Executors.newFixedThreadPool(2)

    val entries = Coverage.getPHPFiles(root).toIterator
    val literal_set = scala.collection.mutable.Set[StringValue]()

    try {
      var i = 0
      while (entries.hasNext) {
        println(s"Script ${i} of ${entries.length}")
        val script_file = entries.next()
        pool.execute(new Analyzer(script_file, literal_set))
        i += 1
      }
    } finally {
      pool.shutdown()
    }

    return literal_set.toSet
  }
}

class Analyzer(script: File, literal_set: scala.collection.mutable.Set[StringValue]) extends Runnable {

  def run() {
    println(script.toString())
    val interpreter = new OakInterpreter()
    literal_set ++= DNode.extractStringLiterals(interpreter.execute(script.toPath())._2.output)
    println(s" Found ${literal_set.size} literals so far")
    //    System.gc();
  }
}