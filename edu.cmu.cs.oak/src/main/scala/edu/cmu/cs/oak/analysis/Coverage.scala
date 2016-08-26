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

  val DRUPAL = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/drupal")
  val JOOMLA = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/joomla")
  val MEDIAWIKI = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/mediawiki")
  val MOODLE = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/moodle")
  val PHPBB = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/phpbb")
  val PHPMYADMIN = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/phpmyadmin")
  val WORDPRESS = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/wordpress")

  val isRelevant = (lit: StringValue) => {
    var contain_s = Set[String]() 
    
    //#ifdef ContainsLessThan
    contain_s = contain_s + "<"
    //#endif
    
    //#ifdef ContainsGreaterThan
    contain_s = contain_s + ">"
    //#endif
    
    //#ifdef ContainsWhitespaceThan
//@    contain_s = contain_s + " "
    //#endif
    
    //#ifdef ConjunctiveContains
    contain_s.map(s => lit.value contains s).fold(true)(_ && _)
    //#endif
    
    //#ifdef DisjunctiveContains
//@    contain_s.map(s => lit.value contains s).fold(false)(_ || _)
    //#endif
  }
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

  def countProjectLiterals(projects: Seq[File]) {
    var i = 0
    projects.foreach {
      p =>
        {
          val d = OakUtility.getProjectLiterals(p).size
          println(p, d)
          i += d
        }
    }
    println(i)
  }

  /**
   *
   */
  def findProjectLiterals(entryPoints: Seq[Path]): Set[StringValue] = {
    var literalSet = Set[StringValue]()

    var i = 1
    entryPoints.foreach {
      ep =>
        {
          //#ifdef AbstractLogging
//@          println(s"Now analyzing (${i}/${entryPoints.size}) ${ep.toAbsolutePath().toString()}")
          //#endif
          
          interpreter = new OakInterpreter()
          literalSet = literalSet union DNode.extractStringLiterals(loadAndExecute(ep)._2.output)
          i = i + 1
          
          //#ifdef AbstractLogging
          //@          logger.info(s" Found ${literalSet.size} literals so far")
          //#endif
        }
    }
    literalSet
  }

  def getCoverage(projectPath: File, entryPoints: Seq[Path], relevant: StringValue => Boolean): (Double, (Int, Int)) = {
    val projectLiterals = OakUtility.getProjectLiterals(projectPath) //.map(s => s.value)
    val relevant_projectLiterals = projectLiterals.filter { lit => relevant(lit) }
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
    var entrypoints = OakUtility.getPHPFiles(SCHOOLMATE).toList.map(f => f.toPath())
    println(s"SchoolMate: ${getCoverage(SCHOOLMATE, entrypoints, isRelevant)}")
  }

  def getAddressbookCoverage() {
    var entrypoints = OakUtility.getPHPFiles(ADDRESSBOOK).toList.map(f => f.toPath())
    println(s"AddressBook: ${getCoverage(ADDRESSBOOK, entrypoints, isRelevant)}")
  }

  def getTimeclockCoverage() {
    var entrypoints = OakUtility.getPHPFiles(TIMECLOCK).toList.map(f => f.toPath())
    println(s"TimeClock: ${getCoverage(TIMECLOCK, entrypoints, isRelevant)}")
  }

  def getUPBCoverage() {
    var entrypoints = OakUtility.getPHPFiles(UPB).toList.map(f => f.toPath())
    println(s"UPB: ${getCoverage(UPB, entrypoints, isRelevant)}")
  }

  def getWebchessCoverage() {
    var entrypoints = OakUtility.getPHPFiles(WEBCHESS).toList.map(f => f.toPath())
    println(s"WebChess: ${getCoverage(WEBCHESS, entrypoints, isRelevant)}")
  }

  def getWordpressCoverage() {
    var entrypoints = OakUtility.getPHPFiles(WORDPRESS).toList.map(f => f.toPath())
    println(s"WordPress: ${getCoverage(WORDPRESS, entrypoints, isRelevant)}")
  }

  def getPHPBBCoverage() {
    var entrypoints = OakUtility.getPHPFiles(PHPBB).toList.map(f => f.toPath())
    println(s"PHPBB: ${getCoverage(PHPBB, entrypoints, isRelevant)}")
  }

  def getDrupalCoverage() {
    var entrypoints = OakUtility.getPHPFiles(DRUPAL).toList.map(f => f.toPath())
    println(s"Drupal: ${getCoverage(DRUPAL, entrypoints, isRelevant)}")
  }

  def getMediaWikiCoverage() {
    var entrypoints = OakUtility.getPHPFiles(MEDIAWIKI).toList.map(f => f.toPath())
    println(s"Drupal: ${getCoverage(MEDIAWIKI, entrypoints, isRelevant)}")
  }

  def getJoomlaCoverage() {
    var entrypoints = OakUtility.getPHPFiles(JOOMLA).toList.map(f => f.toPath())
    println(s"Joomla: ${getCoverage(JOOMLA, entrypoints, isRelevant)}")
  }

  def getMoodleCoverage() {
    var entrypoints = OakUtility.getPHPFiles(MOODLE).toList.map(f => f.toPath())
    println(s"Moodle: ${getCoverage(MOODLE, entrypoints, isRelevant)}")
  }

  def getPhpMyAdminCoverage() {
    var entrypoints = OakUtility.getPHPFiles(PHPMYADMIN).toList.map(f => f.toPath())
    println(s"phpMyAdmin: ${getCoverage(PHPMYADMIN, entrypoints, isRelevant)}")
  }

  def coverages() {
        getAddressbookCoverage() // x
        getSchoolmateCoverage() // x
        getTimeclockCoverage() // x
        getUPBCoverage() // x
        getWebchessCoverage() // x
    //    getWordpressCoverage() // (x)
//    getPHPBBCoverage() // x
        getDrupalCoverage()
//    getMediaWikiCoverage() // immer noch zeitkritisch
    //    getMoodleCoverage()
        getPhpMyAdminCoverage()  // x
    //    getJoomlaCoverage()
  }

  //  val all = List(ADDRESSBOOK, SCHOOLMATE, TIMECLOCK, WEBCHESS)
  //  getSample(100, all, Paths.get("/home/stefan/git/oak/edu.cmu.cs.oak/out/sample100.csv"))

  coverages()
}
