package edu.cmu.cs.symex.test

import org.eclipse.php.internal.core.PHPVersion
import org.eclipse.php.internal.core.ast.nodes.ASTParser

import edu.iastate.symex.php.nodes.ProgramNode
import edu.iastate.symex.util.ASTHelper
import edu.iastate.symex.util.logging.MyLevel
import edu.iastate.symex.util.logging.MyLogger
import scala.xml.Null
import org.eclipse.php.internal.core.ast.nodes.Program
import edu.iastate.symex.core.GlobalEnv
import edu.iastate.symex.core.PhpExecuter
import java.io.File

object AbstractPHPTestCase extends App {
  val tc = new PhpExecuter()
  val env = new GlobalEnv()
  val dm = tc.execute(new File("/home/stefan/Desktop/php/test1.php"))
  println(dm.toIfdefString())
}