package edu.cmu.cs.oak.analysis

import java.io.File

/**
 * Collects
 * - all function [and method] definitions for each system
 * - all static callsites for each system
 *
 * For all function definitions that are not matched with any static callsite
 * we assume the functions are either dead code or callback candidates
 */
object CallbackCandidateAnalyzer extends App {

  /**
   * @param File root for system to analyze
   *
   * @return (#callback candidates, #definitions) per system
   */
  def analyzeSystem(file: File): (Int, Int) = {

    // get entry points
    val files = OakUtility.getPHPFiles(file)

    var i = 0
    var definitions = Set[String]()
    var callsites = Set[String]()

    files.foreach {
      f => {
        i += 1
        val fpath = f.toPath()

        /*
         * Collect all function defnitions and call sites
         */
        val definitions_and_callsites = (new ASTVisitor(fpath)).analyzeCalls()

        // Merge results
        definitions |= definitions_and_callsites._1
        callsites |=  definitions_and_callsites._2
      }
    }

    val nr_of_functions = definitions.size
    val nr_of_ccs = ( definitions -- (definitions & callsites) ).size

    println(definitions)
    return (nr_of_ccs, nr_of_functions)
  }

  def url(fileName: String): File = {
    new File(getClass.getResource("/" + fileName).getPath)
  }

  val ADDRESSBOOK = url("addressbook")
  val SCHOOLMATE = url("schoolmate")
  val TIMECLOCK = url("timeclock")
  val UPB = url("upb")
  val WEBCHESS = url("webchess")

  val DRUPAL = url("drupal")
  val PHPBB = url("phpbb")
  val PHPMYADMIN = url("phpmyadmin")
  val WORDPRESS = url("wordpress")

  val ANCHOR = url("anchor")
  val KIRBY = url("kirby")
  val AUTOMAD = url("automad")
  val MONSTRA = url("monstra")
  val NIBBLEBLOG = url("nibbleblog")

  val MODELSYSTEM = url("modelsystem")

  println(analyzeSystem(MODELSYSTEM))

}