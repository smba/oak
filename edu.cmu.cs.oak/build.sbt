name := "oak" // with Quercus

version := "1.0"

scalaVersion := "2.10.3"

// Add sources to eclipse project
EclipseKeys.withSource := true

libraryDependencies += "com.caucho" % "quercus" % "4.0.45" withSources() withJavadoc()
