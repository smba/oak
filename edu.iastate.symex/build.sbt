name := "symex"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "org.scalacheck" % "scalacheck_2.11" % "1.13.1"
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.3.0-SNAP2"
libraryDependencies += "org.ow2.sat4j" % "org.ow2.sat4j.core" % "2.3.5"
libraryDependencies += "de.fosd.typechef" % "javabdd_repackaged" % "1.0b2"

// https://mvnrepository.com/artifact/de.fosd.typechef/featureexprlib_2.11
libraryDependencies += "de.fosd.typechef" % "featureexprlib_2.11" % "0.4.1"

