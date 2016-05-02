name := "oak" // with Quercus

version := "1.0"

scalaVersion := "2.10.3"

// Add sources to eclipse project
EclipseKeys.withSource := true

libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "org.scalacheck" % "scalacheck_2.10" % "1.13.1"
libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.3.0-SNAP2"
