package edu.cmu.cs.oak.analysis

import java.io.File

object OutputCandidateAnalyzer extends App {


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

//  val strings = OakUtility.getProjectLiterals(WORDPRESS)._1

//  val ocs = strings.count(Coverage.isRelevant(_))

//  println(s"${ocs} / ${strings.size}")


}