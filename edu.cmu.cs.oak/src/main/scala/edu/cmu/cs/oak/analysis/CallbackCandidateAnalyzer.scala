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
  
  val MODELSYSTEM = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/modelsystem")
  
  println(analyzeSystem(MODELSYSTEM))
  
}