package edu.cmu.cs.oak.analysis

import java.io.{ File, FileOutputStream, PrintWriter }
import java.nio.file.{ Path, Paths }
import java.time.Instant

import edu.cmu.cs.oak.core.{ ControlCode, OakEngine, OakInterpreter }
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.value.{ StringLiteralContext, StringValue }
import org.slf4j.LoggerFactory

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{ future, Future }

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

  val MODEL_PROJECT = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/model_project")

  def isRelevant(lit: StringValue) = {
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
    //@    contain_s.map(s => lit.value contains s).fold(true)(_ && _)
    //#endif

    //#ifdef DisjunctiveContains
    contain_s.map(s => lit.value contains s).fold(false)(_ || _)
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
          val d = OakUtility.getProjectLiterals(p)._1.size
          i += d
        }
    }
  }

  /**
   *
   */
  def findProjectLiterals(entryPoints: Seq[Path]): (Set[StringValue], Set[Path], Map[String, Boolean], Set[StringValue], Map[(String, Int), Boolean]) = {
    var literalSet = new scala.collection.mutable.HashSet[StringValue]() with scala.collection.mutable.SynchronizedSet[StringValue]
    var includesSet = Set[Path]()

    //------------------

    val tasks = for (ep <- entryPoints.zipWithIndex) yield future {
      //      println(s"Now analyzing (${ep._2}/${entryPoints.size}) ${ep._1.toAbsolutePath().toString()}")
      val before = Instant.now()
      interpreter = new OakInterpreter()

      val executed = interpreter.execute(ep._1)
      val found = DNode.extractStringLiterals(executed._2.output)
      found.foreach { s => literalSet += s }
      val after = Instant.now()
      //      println(s"Finished (${ep._2}/${entryPoints.size}) (${Duration.between(before, after)})")

      //#ifdef AbstractLogging
      //@          logger.info(s" Found ${literalSet.size} literals so far")
      //#endif

      print(".")
      (found, interpreter.included_files, interpreter.defined_functions, executed._2.touched, executed._2.include_history)
    }

    val aggregated = Future.sequence(tasks)

    var found_ = Await.result(aggregated, 45.minutes)
    val found = found_.map(f => f._1).fold(Set[StringValue]())(_ union _)
    val includes = found_.map(f => f._2).fold(Set[Path]())(_ union _)
    val include_history = {
      val histroy_seq = found_.map(f => f._5)
      val keys = histroy_seq.map(hs => hs.keySet).fold(Set[(String, Int)]())(_ union _)
      keys.map(k => (k, histroy_seq.map(h => h.getOrElse(k, false)).fold(false)(_ || _))).toMap
    }

    val defined_functions = found_.map(f => f._3)
    val keys = defined_functions.map(x => x.keySet).fold(Set[String]())(_ union _)
    val resolved = keys.map {
      name =>
        {
          (name -> defined_functions.map(m => m.getOrElse(name, false)).fold(false)(_ || _))
        }
    }.toMap
    val touched = found_.map(f => f._4).fold(Set[StringValue]())(_ union _).toSet

    print("\n")

    return (found, includes.toSet, resolved, touched, include_history)
    //------------------

  }

  def getCoverage(projectPath: File, entryPoints: Seq[Path], relevant: StringValue => Boolean): (Double, (Int, Int)) = {

    val parsed = OakUtility.getProjectLiterals(projectPath)

    val projectLiterals = parsed._1 //.map(s => s.value)
    val relevant_projectLiterals = projectLiterals.filter { lit => relevant(lit) }
    val foundLiterals = findProjectLiterals(entryPoints) //.map(s => s.value)
    val relevant_foundLiterals = foundLiterals._1.filter { lit => relevant(lit) }

    val all_include_expressions = parsed._2
    val include_expressions_with_result = foundLiterals._5

    val included = foundLiterals._2.map { x => x.toAbsolutePath() }
    val allFiles = OakUtility.getPHPFiles(projectPath).map { x => x.toPath().toAbsolutePath() }.toSet
    val notIncluded = allFiles -- included

    //#ifdef AbstractLogging
    //@    println(s"Projekt hat ${projectLiterals.size} Literale")
    //@    println(s"Project hat ${relevant_projectLiterals.size} relevante Literale")
    //@    println(s"Analyse fand ${foundLiterals.size}  Literale")
    //@    println(s"Analyse fand ${relevant_foundLiterals.size} relevante Literale")
    //#endif

    val relative_coverage = ((relevant_foundLiterals intersect relevant_projectLiterals).size * 1.0 / relevant_projectLiterals.size * 100)


    // Check if the file does not exist yet. If so, create file, otherwise overwrite it.
    val path = Paths.get(OakUtility.url("").toString + "/" + s"logs/${projectPath.toString.split("/").last}_not_found.csv")
    new FileOutputStream(path.toAbsolutePath().toString(), false).close();
    val pw = new PrintWriter(path.toFile())
    val p = (relevant_projectLiterals -- relevant_foundLiterals).zipWithIndex.map {
      case (sv, i) => i + "|\"" + sv.value.replaceAll("\"", "'") + "\"|" + sv.file + "|" + sv.lineNr
    }.mkString("\n")
    pw.write("#|String Value|Location|Line Number|\n" + p)
    pw.close

    // some logging
    val notfound = (relevant_projectLiterals -- relevant_foundLiterals)
    val not_touched = relevant_projectLiterals -- foundLiterals._4

    val filesOfNotFoundLiterals = notfound.toList.map { s => Paths.get(s.file) }

    val funcitonsOfNotFoundLiterals = notfound.toList.filter(s => s.context == StringLiteralContext.FDEFINITION).filter(sv => !("" equals sv.file) && included.contains(Paths.get(sv.file))).map {
      s => s.fdef
    }.map(s => s._1)

    val x = notfound.groupBy { s => s.context }.map {
      case (k, v) => (k, 100 * (v.size / (notfound.size * 1.0)))
    }
    val y = relevant_foundLiterals.groupBy { s => s.context }.map {
      case (k, v) => (k, 100 * (v.size / (1.0 * relevant_foundLiterals.size)))
    }

    val definedButNotCalledFunctions = foundLiterals._3.groupBy(x => x._2).get(false).get.keySet

    // Dead imports: How many not-touched literals can be explained by files not imported?
    val dead_imports = not_touched.toList.filter(sv => !("" equals sv.file)).map(sv => Paths.get(sv.file)).map(path => if (notIncluded.contains(path)) 1 else 0).fold(0)(_ + _)

    // How many not-touched string literals reside in functions that are defined (thus known) but never called?
    val dead_functions = funcitonsOfNotFoundLiterals.map(fname => if (definedButNotCalledFunctions.contains(fname)) 1 else 0).fold(0)(_ + _)

    /*
     * Dynamic resolution of include statements (require, require_once or include_once). 
     * - How many include statements exist in the entire system? [Parser]
     * - How many include statements have been reached? [Environment]
     * 		- How many include expressions could not be resolved?
     */

    // Distribution of not-touched string literals
    val dstro = not_touched.groupBy { s => s.context }.map {
      case (k, v) => (k, v.size)
    }

    val relevantTouchedLiterals = (foundLiterals._4 intersect relevant_projectLiterals)

    val touch_coverage = (foundLiterals._4 intersect relevant_projectLiterals).size * 1.0 / relevant_projectLiterals.size
    val coverage_loss = (touch_coverage / relative_coverage)

    //     val include_touch_coverage = (include_expressions_with_result.keySet.size*1.0) / all_include_expressions.size
    println(s"-----------------------------")
    println(s"Touch Coverage: ${touch_coverage * 100} % or (${(foundLiterals._4 intersect relevant_projectLiterals).size}/${relevant_projectLiterals.size})")
    println(s"Not-touched explained by dead imports: ${dead_imports}")
    println(s"Not-touched explained by dead functions: ${dead_functions}")
    println(s"Distribution of not covered literals: ${dstro}")
    println(s"Touched includes: ${include_expressions_with_result.keySet.size}, total includes: ${all_include_expressions.size}")
    println(s"Include resolution success rate: ${include_expressions_with_result.groupBy(f => f._2).map(f => (f._1, f._2.size))}")


    return (relative_coverage, (relevant_foundLiterals.size, relevant_projectLiterals.size))
  }

  private def getSchoolmateCoverage() {
    var entrypoints = OakUtility.getPHPFiles(SCHOOLMATE).toList.map(f => f.toPath())
    println(s"SchoolMate: ${getCoverage(SCHOOLMATE, entrypoints, isRelevant)}")
  }

  private def getAddressbookCoverage() {
    var entrypoints = OakUtility.getPHPFiles(ADDRESSBOOK).toList.map(f => f.toPath())
    println(s"AddressBook: ${getCoverage(ADDRESSBOOK, entrypoints, isRelevant)}")
  }

  private def getTimeclockCoverage() {
    var entrypoints = OakUtility.getPHPFiles(TIMECLOCK).toList.map(f => f.toPath())
    println(s"TimeClock: ${getCoverage(TIMECLOCK, entrypoints, isRelevant)}")
  }

  private def getUPBCoverage() {
    var entrypoints = OakUtility.getPHPFiles(UPB).toList.map(f => f.toPath())
    println(s"UPB: ${getCoverage(UPB, entrypoints, isRelevant)}")
  }

  private def getWebchessCoverage() {
    var entrypoints = OakUtility.getPHPFiles(WEBCHESS).toList.map(f => f.toPath())
    println(s"WebChess: ${getCoverage(WEBCHESS, entrypoints, isRelevant)}")
  }

  private def getWordpressCoverage() {
    var entrypoints = OakUtility.getPHPFiles(WORDPRESS).toList.map(f => f.toPath())
    println(s"WordPress: ${getCoverage(WORDPRESS, entrypoints, isRelevant)}")
  }

  private def getPHPBBCoverage() {
    var entrypoints = OakUtility.getPHPFiles(PHPBB).toList.map(f => f.toPath())
    println(s"PHPBB: ${getCoverage(PHPBB, entrypoints, isRelevant)}")
  }

  private def getDrupalCoverage() {
    var entrypoints = OakUtility.getPHPFiles(DRUPAL).toList.map(f => f.toPath())
    println(s"Drupal: ${getCoverage(DRUPAL, entrypoints, isRelevant)}")
  }

  private def getMediaWikiCoverage() {
    var entrypoints = OakUtility.getPHPFiles(MEDIAWIKI).toList.map(f => f.toPath())
    println(s"Drupal: ${getCoverage(MEDIAWIKI, entrypoints, isRelevant)}")
  }

  private def getJoomlaCoverage() {
    var entrypoints = OakUtility.getPHPFiles(JOOMLA).toList.map(f => f.toPath())
    println(s"Joomla: ${getCoverage(JOOMLA, entrypoints, isRelevant)}")
  }

  private def getMoodleCoverage() {
    var entrypoints = OakUtility.getPHPFiles(MOODLE).toList.map(f => f.toPath())
    println(s"Moodle: ${getCoverage(MOODLE, entrypoints, isRelevant)}")
  }

  private def getPhpMyAdminCoverage() {
    var entrypoints = OakUtility.getPHPFiles(PHPMYADMIN).toList.map(f => f.toPath())
    println(s"phpMyAdmin: ${getCoverage(PHPMYADMIN, entrypoints, isRelevant)}")
  }

  private def getModelProjectCoverage() {
    var entrypoints = OakUtility.getPHPFiles(MODEL_PROJECT).toList.map(f => f.toPath())
    println(s"Model project: ${getCoverage(MODEL_PROJECT, entrypoints, isRelevant)}")
  }

  def coverages() {
    //#ifdef Addressbook
//@    getAddressbookCoverage
    //#endif
    //#ifdef Schoolmate
//@    getSchoolmateCoverage
    //#endif
    //#ifdef Timeclock
//@    getTimeclockCoverage
    //#endif
    //#ifdef UPB
//@    getUPBCoverage
    //#endif
    //#ifdef Webchess
//@    getWebchessCoverage
    //#endif
    //#ifdef Wordpress
//@    getWordpressCoverage
    //#endif
    //#ifdef PHPBB
//@    getPHPBBCoverage
    //#endif
    //#ifdef Drupal
    getDrupalCoverage
    //#endif
    //#ifdef Mediawiki
//@    getMediaWikiCoverage
    //#endif
    //#ifdef Moodle
//@    getMoodleCoverage
    //#endif
    //#ifdef PhpMyAdmin 
//@    getPhpMyAdminCoverage
    //#endif
  }
  
  //#ifdef CoverageAnalysis
  coverages()
  System.exit(0)
  //#endif
}
