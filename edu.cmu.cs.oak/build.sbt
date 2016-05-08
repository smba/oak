name := "oak" // with Quercus

version := "1.0"

scalaVersion := "2.10.3"

// Add sources to eclipse project
//EclipseKeys.withSource := true

libraryDependencies ++= Seq("org.slf4j" % "slf4j-api" % "1.7.5", "org.slf4j" % "slf4j-simple" % "1.7.5")

libraryDependencies += "org.mockito" % "mockito-all" % "1.9.5"
libraryDependencies += "javax.cache" % "cache-api" % "1.0.0"
libraryDependencies += "javax.servlet.jsp" % "jsp-api" % "2.2"
libraryDependencies += "javax" % "javaee-api" % "7.0"
libraryDependencies += "com.caucho" % "quercus" % "4.0.44"
libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "org.scalacheck" % "scalacheck_2.10" % "1.13.1"
libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.3.0-SNAP2"
