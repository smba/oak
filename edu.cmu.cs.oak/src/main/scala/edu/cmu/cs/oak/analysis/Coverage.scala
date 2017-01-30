package edu.cmu.cs.oak.analysis

import java.io.{ File, FileOutputStream, PrintWriter }
import java.nio.file.{ Path, Paths }

import edu.cmu.cs.oak.core.{ ControlCode, OakEngine, OakInterpreter }
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.value.{ StringLiteralContext, StringValue }
import org.slf4j.LoggerFactory

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{ future, Future }
import scala.util.Random
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashSet
import scala.collection.mutable.SynchronizedSet
import scala.collection.mutable.HashMap
import scala.collection.mutable.SynchronizedMap

class Coverage {

}

object Coverage extends App {

  //val out = new PrintStream(new FileOutputStream("output.txt"));
  //System.setOut(out);

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

  val GARV = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/garv")
  val ANCHOR = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/anchor")
  val PAGEKIT = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/pagekit")
  val KIRBY = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/kirby")
  val FORK = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/fork")
  val AUTOMAD = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/automad")
  val WONDERCMS = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/wondercms")
  val MONSTRA = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/monstra")
  val NIBBLEBLOG = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/nibbleblog")

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
  def findProjectLiterals(entryPoints: Seq[Path]): (Set[StringValue], Set[Path], Map[String, Boolean], Set[StringValue], Map[(String, Int), Boolean], Map[String, Int]) = {
    var literalSet = new scala.collection.mutable.HashSet[StringValue]() with scala.collection.mutable.SynchronizedSet[StringValue]
    var includesSet = Set[Path]()

    var undefinedFunctions = Set[String]()
    //------------------

    //#ifdef Parallelization
        val tasks = for (ep <- entryPoints.zipWithIndex) yield future {
          //      println(s"Now analyzing (${ep._2}/${entryPoints.size}) ${ep._1.toAbsolutePath().toString()}")
          interpreter = new OakInterpreter()
    
          val executed = interpreter.execute(ep._1)
          val found = DNode.extractStringLiterals(executed._2.output)
          found.foreach { s => literalSet += s }
          
          val undefinedFunctions = executed._2.unknown_standard_functions.toSet
          //      println(s"Finished (${ep._2}/${entryPoints.size}) (${Duration.between(before, after)})")
    
    //#ifdef AbstractLogging
    //@          logger.info(s" Found ${literalSet.size} literals so far")
    //#endif
    
          //println(s"${ep.toString()} found ${found.size} literals")
          (found, interpreter.included_files, interpreter.defined_functions, executed._2.touched, executed._2.include_history, undefinedFunctions)
        }
    
        val aggregated = Future.sequence(tasks)
    
        var found_ = Await.result(aggregated, 45.minutes)
        
    //#else
//@    var found_ = new ListBuffer[(Set[StringValue], HashSet[Path] with SynchronizedSet[Path], HashMap[String, Boolean] with SynchronizedMap[String, Boolean], HashSet[StringValue], HashMap[(String, Int), Boolean], Set[(String, Int)])]()
//@
//@    entryPoints.zipWithIndex.foreach {
//@      ep =>
//@        {
//@          interpreter = new OakInterpreter()
//@
//@          val executed = interpreter.execute(ep._1)
//@          val found = DNode.extractStringLiterals(executed._2.output)
//@          found.foreach { s => literalSet += s }
//@
//@          val undefinedFunctions = executed._2.unknown_standard_functions.toSet
//@          //      println(s"Finished (${ep._2}/${entryPoints.size}) (${Duration.between(before, after)})")
//@
          //#ifdef AbstractLogging
          //@          logger.info(s" Found ${literalSet.size} literals so far")
          //#endif
//@
//@          //println(s"${ep.toString()} found ${found.size} literals")
//@          found_.+=( (found, interpreter.included_files, interpreter.defined_functions, executed._2.touched, executed._2.include_history, undefinedFunctions) )
//@        }
//@    }
//@
    //#endif
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

    val undefinedFunctionNames = found_.map(f => f._6.map(s => s._1)).fold(Set[String]())(_ union _)
    val seq = found_.map(f => f._6.toMap)
    val undefinedFunctionz = undefinedFunctionNames.map {
      k => (k, seq.map(m => m.getOrElse(k, 0)).fold(0)(_ + _))
    }.toMap
    print("\n")

    return (found, includes.toSet, resolved, touched, include_history, undefinedFunctionz)
    //------------------

  }

  def getCoverage(projectPath: File, entryPoints: Seq[Path], relevant: StringValue => Boolean): (Double, (Int, Int)) = {

    val parsed = OakUtility.getProjectLiterals(projectPath)

    val projectLiterals = parsed._1 //.map(s => s.value)
    val relevant_projectLiterals = projectLiterals.filter { lit => relevant(lit) }
//    println(relevant_projectLiterals)
    val foundLiterals = findProjectLiterals(entryPoints) //.map(s => s.value)
    val relevant_foundLiterals = foundLiterals._1.filter { lit => relevant(lit) }
    //val undefinedFunctionNames = foundLiterals._6.filter { case (k, v) => (OakUtility.is_php_function(k) && k.startsWith("str")) }
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
    //new FileOutputStream(path.toAbsolutePath().toString(), false).close();
    /*val pw = new PrintWriter(path.toFile())
    val p = (relevant_projectLiterals -- relevant_foundLiterals).zipWithIndex.map {
      case (sv, i) => i + "|\"" + sv.value.replaceAll("\"", "'") + "\"|" + sv.file + "|" + sv.lineNr
    }.mkString("\n")
    pw.write("#|String Value|Location|Line Number|\n" + p)
    pw.close
		*/
    // some logging
    val notfound = (relevant_projectLiterals -- relevant_foundLiterals)
    val not_touched = relevant_projectLiterals -- foundLiterals._4

    val filesOfNotFoundLiterals = notfound.toList.map { s => Paths.get(s.file) }

    //#ifdef CoverageAnalysis
    val funcitonsOfNotFoundLiterals = not_touched.filter(s => isRelevant(s)).toList.filter(s => s.context == StringLiteralContext.FDEFINITION).filter(sv => !("" equals sv.file)).map {
      s => s.fdef
    }.map(s => s._1)

    val x = notfound.groupBy { s => s.context }.map {
      case (k, v) => (k, 100 * (v.size / (notfound.size * 1.0)))
    }
    val y = relevant_foundLiterals.groupBy { s => s.context }.map {
      case (k, v) => (k, 100 * (v.size / (1.0 * relevant_foundLiterals.size)))
    }
    //#endif

    val definedButNotCalledFunctions = foundLiterals._3.groupBy(x => x._2).getOrElse(false, Map[String, Boolean]()).keySet

    // Dead imports: How many not-touched literals can be explained by files not imported?
    val dead_imports = not_touched.toList.filter(sv => !("" equals sv.file)).map(sv => Paths.get(sv.file)).map(path => if (notIncluded.contains(path)) 1 else 0).fold(0)(_ + _)

    //#ifdef CoverageAnalysis
    // How many not-touched string literals reside in functions that are defined (thus known) but never called?
    val dead_functions = funcitonsOfNotFoundLiterals.map(fname => if (definedButNotCalledFunctions.contains(fname)) 1 else 0).fold(0)(_ + _)
    val dead_function_sample = Random.shuffle(funcitonsOfNotFoundLiterals.toSet intersect definedButNotCalledFunctions).take(25)

    dead_function_sample.foreach {
      df => println(df)
    }
    
//    println(funcitonsOfNotFoundLiterals)
//    println(definedButNotCalledFunctions)
    
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
    //#endif

    val relevantTouchedLiterals = (foundLiterals._4 intersect relevant_projectLiterals)
    
    val ratio_relevantTouchedLiterals_touchedLiterals = (relevantTouchedLiterals.size, foundLiterals._4.size)
    val ratio_relevantFoundLiterals_touchedLiterals = (relevant_foundLiterals.size, foundLiterals._1.size)
    
    
    val touch_coverage = (foundLiterals._4 intersect relevant_projectLiterals).size * 1.0 / relevant_projectLiterals.size
    val coverage_loss = (touch_coverage / relative_coverage)

    val touched_literals = foundLiterals._4 intersect relevant_projectLiterals
    val output_literals = relevant_foundLiterals intersect relevant_projectLiterals
    val not_found_literals = touched_literals -- output_literals

    val not_found_sample = Random.shuffle(not_found_literals).take(10).toList

    //     val include_touch_coverage = (include_expressions_with_result.keySet.size*1.0) / all_include_expressions.size
//    println(s"-----------------------------")
//    println(s"Touch Coverage: ${touch_coverage * 100} % or (${(foundLiterals._4 intersect relevant_projectLiterals).size}/${relevant_projectLiterals.size})")
//    println(s"Not-touched explained by dead imports: ${dead_imports}")
//
//    //#ifdef CoverageAnalysis
//    println(s"Not-touched explained by dead functions: ${dead_functions}")
//    println(s"Distribution of not covered literals: ${dstro}")
//    //#endif
//
//    println(s"Touched includes: ${include_expressions_with_result.keySet.size}, total includes: ${all_include_expressions.size}")
//    println(s"Include resolution success rate: ${include_expressions_with_result.groupBy(f => f._2).map(f => (f._1, f._2.size))} ${include_expressions_with_result}")
//    val calls = undefinedFunctionNames.keySet.map { k => undefinedFunctionNames.getOrElse(k, 0) }.fold(0)(_ + _)
//    println(s"${undefinedFunctionNames.size} funtions were undefined, and were part of the standard lib, ${calls} calls in total")
//    
//    println(s"(Touched && relevant) / touched: ${ratio_relevantTouchedLiterals_touchedLiterals}")
//    println(s"(Found && relevant) / found: ${ratio_relevantFoundLiterals_touchedLiterals}")
//    
    //    println(s"${include_expressions_with_result.groupBy(f => f._2).getOrElse(false, "nix")}")
    //println(include_expressions_with_result.groupBy(f => f._2).getOrElse(true, "no include available"))

    val failed_includes = include_expressions_with_result.groupBy(f => f._2).getOrElse(false, List()).map(e => e._1)
    val succ_includes = include_expressions_with_result.groupBy(f => f._2).getOrElse(true, List()).size

    println("-------------------------------------------------------------------------------------")
    println("### Code Coverage ###")
    // Reach cooverage 
    println("Reach Coverage: " + bar((foundLiterals._4 intersect relevant_projectLiterals).size, relevant_projectLiterals.size))
    
    // Output coverage 
    println("Outpt Ouverage: " + bar(relevant_foundLiterals.size, relevant_projectLiterals.size))
    
    // (Sanity check)
    val alle = dstro.map(s => s._2).fold(0)(_ + _)
    println("\n### Not-reached string distribution ###")
    println(s"Functions     : ${bar(dstro.getOrElse(StringLiteralContext.FDEFINITION, 0), alle)}")
    println(s"Templates     : ${bar(dstro.getOrElse(StringLiteralContext.TEMPLATE, 0), alle)}")
    println(s"Misc..        : ${bar(dstro.getOrElse(StringLiteralContext.MISC, 0), alle)}")
    
    println("\n### Fault Analysis (not-reached strings) ###")
    println(s"Dead imports  : ${bar(dead_imports, dead_imports + dead_functions)}")
    println(s"Dead functions: ${bar(dead_functions, dead_imports + dead_functions)}")
    
    
    println("\n### Include Coverage ###")
    println(s"Reached       : ${bar(include_expressions_with_result.keySet.size,Math.max(include_expressions_with_result.keySet.size, all_include_expressions.size))}")
    println(s"Resolved      : ${bar(succ_includes, Math.max(include_expressions_with_result.keySet.size, all_include_expressions.size))}")
    println(s"Succes rate   : ${succ_includes / (if (include_expressions_with_result.keySet.size == 0) 1 else include_expressions_with_result.keySet.size) * 100}%")
    return (relative_coverage, (relevant_foundLiterals.size, relevant_projectLiterals.size))
  }

  def bar(x: Int, y: Int): String = {
    val ratio = x*1.0 / (if (y == 0) 1.0 else y * 1.0) * 100
    val roundd = Math.round(ratio*100.0)/100.0
    val n = 75
    val digits = Math.floor(n * (x/((if (y == 0) 1.0 else y * 1.0)))).toInt
    return s"|${"=" * digits}${" " * (n - digits)}| ${ratio}% (${x}/${y})"
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

  private def getGarvCoverage() {
    var entrypoints = OakUtility.getPHPFiles(GARV).toList.map(f => f.toPath())
    println(s"Garv: ${getCoverage(GARV, entrypoints, isRelevant)}")
  }

  private def getPagekitCoverage() {
    var entrypoints = OakUtility.getPHPFiles(PAGEKIT).toList.map(f => f.toPath())
    println(s"Pagekit: ${getCoverage(PAGEKIT, entrypoints, isRelevant)}")
  }

  private def getAnchorCoverage() {
    var entrypoints = OakUtility.getPHPFiles(ANCHOR).toList.map(f => f.toPath())
    println(s"Anchor: ${getCoverage(ANCHOR, entrypoints, isRelevant)}")
  }

  private def getKirbyCoverage() {
    var entrypoints = OakUtility.getPHPFiles(KIRBY).toList.map(f => f.toPath())
    println(s"Kirby: ${getCoverage(KIRBY, entrypoints, isRelevant)}")
  }
  private def getForkCoverage() {
    var entrypoints = OakUtility.getPHPFiles(FORK).toList.map(f => f.toPath())
    println(s"Fork: ${getCoverage(FORK, entrypoints, isRelevant)}")
  }
  private def getAutomadCoverage() {
    var entrypoints = OakUtility.getPHPFiles(AUTOMAD).toList.map(f => f.toPath())
    println(s"Automad: ${getCoverage(AUTOMAD, entrypoints, isRelevant)}")
  }
  private def getWonderCMSCoverage() {
    var entrypoints = OakUtility.getPHPFiles(WONDERCMS).toList.map(f => f.toPath())
    println(s"WonderCMS: ${getCoverage(WONDERCMS, entrypoints, isRelevant)}")
  }
  private def getMonstraCoverage() {
    var entrypoints = OakUtility.getPHPFiles(MONSTRA).toList.map(f => f.toPath())
    println(s"Monstra: ${getCoverage(MONSTRA, entrypoints, isRelevant)}")
  }
  private def getNibbleblogCoverage() {
    var entrypoints = OakUtility.getPHPFiles(NIBBLEBLOG).toList.map(f => f.toPath())
    println(s"Nibbleblog: ${getCoverage(NIBBLEBLOG, entrypoints, isRelevant)}")
  }

  def coverages() {
    //#ifdef Addressbook
//@        getAddressbookCoverage
    //#endif
    //#ifdef Schoolmate
        getSchoolmateCoverage
    //#endif
    //#ifdef Timeclock
//@        getTimeclockCoverage
    //#endif
    //#ifdef UPB
//@        getUPBCoverage
    //#endif
    //#ifdef Webchess
//@        getWebchessCoverage
    //#endif
    //#ifdef Wordpress
    //@    getWordpressCoverage
    //#endif
    //#ifdef PHPBB
//@        getPHPBBCoverage
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
//@        getPhpMyAdminCoverage
    //#endif
    //#ifdef Joomla
    //@    getJoomlaCoverage
    //#endif
    //#ifdef Garv
    //@    getGarvCoverage()
    //#endif
    //#ifdef Pagekit
    //@    getPagekitCoverage()
    //#endif
    //#ifdef Anchor
//@        getAnchorCoverage
    //#endif
    //#ifdef Kirby
//@        getKirbyCoverage()
    //#endif
    //#ifdef Fork
    //@    getForkCoverage()
    //#endif
    //#ifdef Automad
//@        getAutomadCoverage()
    //#endif
    //#ifdef WonderCMS
    //@    getWonderCMSCoverage()
    //#endif
    //#ifdef Monstra
//@        getMonstraCoverage()
    //#endif
    //#ifdef Nibbleblog
        getNibbleblogCoverage()
    //#endif
//        getModelProjectCoverage()
  }

  //#ifdef CoverageAnalysis
  coverages()
  System.exit(0)
  //#endif
}
