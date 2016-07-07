import sbt._
import Keys._

object OakBuild extends Build {
	lazy val hessian = Project(id = "hessian", base = file("hessian"))
	lazy val kernel = Project(id = "kernel", base = file("kernel")) dependsOn(hessian)
	lazy val quercus = Project(id = "quercus", base = file("quercus")) dependsOn(kernel)
	lazy val oak = Project(id = "oak", base = file("edu.cmu.cs.oak")) dependsOn(quercus)
	lazy val symex = Project(id = "symex", base = file("edu.iastate.symex")) dependsOn(phpparser, featureexprlib)
	lazy val phpparser = Project(id = "phpparser", base = file("org.eclipse.php"))
	lazy val featureexprlib = Project(id = "featureexpr", base = file("de.fosd.typechef.featureexpr"))
	//lazy val difftest = Project(id = "difftest", base = file("edu.cmu.cs.oak.difftest"))
}
