scalaVersion := "2.10.0"

libraryDependencies += "javax.enterprise" % "cdi-api" % "1.0-SP4"  exclude("javax.annotation", "jsr250-api") exclude("org.jboss.spec.javax.interceptor", "jboss-interceptors-api_1.1_spec")

libraryDependencies += "javax.cache" % "cache-api" % "1.0.0" 

