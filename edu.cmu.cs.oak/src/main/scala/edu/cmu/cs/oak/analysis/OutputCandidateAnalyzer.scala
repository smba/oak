package edu.cmu.cs.oak.analysis

import java.io.File

object OutputCandidateAnalyzer extends App {


  val WORDPRESS = new File("/home/stefan/git/oak/edu.cmu.cs.oak/bin/wordpress")

  val strings = OakUtility.getProjectLiterals(WORDPRESS)._1

  val ocs = strings.count(Coverage.isRelevant(_))

  println(s"${ocs} / ${strings.size}")


}