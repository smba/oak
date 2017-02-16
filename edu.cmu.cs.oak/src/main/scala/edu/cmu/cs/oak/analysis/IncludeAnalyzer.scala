package edu.cmu.cs.oak.analysis

import java.io.File

object IncludeAnalyzer extends App {

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