package edu.cmu.cs.oak.analysis

import java.io.File
import java.nio.file.Paths

import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.core.ControlCode
import edu.cmu.cs.oak.core.OakEngine
import edu.cmu.cs.oak.env.Environment
import java.nio.file.Path
import edu.cmu.cs.oak.nodes.DNode

class Coverage {

}

object Coverage extends App {
  
  // engine and interpreter instance for testing
  val engine = new OakEngine()
  val interpreter = new OakInterpreter()

  //val pp = new PrettyPrinter(200, 0)

  /**
   * Read a PHP source code from file, parses & executes it.
   *
   * @param script PHP source code file
   * @return (ControlCode, Environment)
   */
  def loadAndExecute(path: Path): (ControlCode.Value, Environment) = {
    return interpreter.execute(path)
  }

  /* utility method */
  def url(fileName: String): Path = {
    Paths.get(getClass.getResource("/" + fileName).getPath)
  }
  
  /**
   *
   */
  def findProjectLiterals(entryPoints: Seq[Path]): Set[StringValue] = {
    var literalSet = Set[StringValue]()
    entryPoints.foreach {
      ep => {
        literalSet = literalSet union DNode.extractStringLiterals(loadAndExecute(ep)._2.output)
      }
    }
    literalSet
  }
  
  def getProjectLiterals(file: File): Set[StringValue] = {
    getPHPFiles(file).map{f=>(new ASTVisitor(Paths.get(f.getAbsolutePath))).retrieveStringLiterals()}.foldLeft(Set[StringValue]())(_ union _)
  }
  
  def getPHPFiles(file: File): Stream[File] = {
    def getFileTree(f: File): Stream[File] = {
      f #:: (if (f.isDirectory) f.listFiles().toStream.flatMap(getFileTree)
      else Stream.empty)
    }
    getFileTree(file).filter(_.getName.endsWith(".php"))
  }
  
  def getCoverage(projectPath: File, entryPoints: Seq[Path]): (Int, Int) = {
    val isRelevant = (lit: StringValue) => true
    val projectLiterals = getProjectLiterals(projectPath).filter { lit => isRelevant(lit) }
    val foundLiterals = findProjectLiterals(entryPoints).filter { lit => isRelevant(lit) }
    (foundLiterals.size, projectLiterals.size)
  }
  
  //val literals = getProjectLiterals(new File("/home/stefan/git/oak/edu.cmu.cs.oak/src/test/resources/wordpress"))
  //println("Found " + literals.size + " literals with "  + literals.map{l=>l.value.size}.fold(0)(_ + _ ) + " characters.")

  val wp = new File("/home/stefan/git/oak/edu.cmu.cs.oak/src/test/resources/wordpress")
  val eps = List("wordpress/index.php", "wordpress/wp-admin/install.php", "wordpress/wp-admin/index.php").map(ep => url(ep))
  println(getCoverage(wp, eps))
}