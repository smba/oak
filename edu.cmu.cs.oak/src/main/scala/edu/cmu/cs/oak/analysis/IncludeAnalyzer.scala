package edu.cmu.cs.oak.analysis

import java.io.File

object IncludeAnalyzer extends App {
  
  // system paths
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
    
  
  //analyze Nibbleblog
//  println("ADDRESSBOOK", OakUtility.analyzeIncludeExpressions(ADDRESSBOOK))
//  println("SCHOOLMATE", OakUtility.analyzeIncludeExpressions(SCHOOLMATE))
//  println("TIMECLOCK", OakUtility.analyzeIncludeExpressions(TIMECLOCK))
//  println("WEBCHESS", OakUtility.analyzeIncludeExpressions(WEBCHESS))
//  
//  println("DRUPAL", OakUtility.analyzeIncludeExpressions(DRUPAL))
  println("PHPBB", OakUtility.analyzeIncludeExpressions(PHPBB))
//  println("PHPMYADMIN", OakUtility.analyzeIncludeExpressions(PHPMYADMIN))
  
//  println("ANCHOR", OakUtility.analyzeIncludeExpressions(ANCHOR))
//  println("KIRBY", OakUtility.analyzeIncludeExpressions(KIRBY))
//  println("AUTOMAD", OakUtility.analyzeIncludeExpressions(AUTOMAD))
//  println("MONSTRA", OakUtility.analyzeIncludeExpressions(MONSTRA))
//  println("NIBBLEBLOG", OakUtility.analyzeIncludeExpressions(NIBBLEBLOG))
//  
}