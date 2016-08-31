package edu.cmu.cs.oak.analysis

import java.io.PrintWriter
import java.nio.file.Paths
import scala.util.Random
import edu.cmu.cs.oak.value.StringValue
import java.nio.file.Path
import java.io.File

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{ future, Future }
/**
 * Selection of useful methods used during analysis.
 *
 */
object OakUtility {

  /**
   * Utility Method,
   */
  def url(fileName: String): Path = {
    Paths.get(getClass.getResource("/" + fileName).getPath)
  }

  def getSample(n: Int, projects: Seq[File], path: Path) {
    val allLiterals = projects.map(p => getProjectLiterals(p)._1).fold(Set[StringValue]())(_ union _).toIterator
    val sample = Random.shuffle(allLiterals).take(n).toList

    val pw = new PrintWriter(path.toFile())
    val p = sample.zipWithIndex.map { case (sv, i) => i + "|\"" + sv.value.replaceAll("\"", "'") + "\"|" + sv.file + "|" + sv.lineNr }.mkString("\n")
    pw.write("#|String Value|Location|Line Number|\n" + p)
    pw.close
  }

  /**
   * Retrieves <strong>all</strong>  parsable string literals that are used in a project
   * and returns a set of StringValues (string literals plus location information).
   *
   * @param file java.io.File Root location of the system
   * @return Set of all parsable string literals
   *
   */
  def getProjectLiterals(file: File): (Set[StringValue], Set[(String, Int)]) = {

    // Parallel parsing tasks
    val files = getPHPFiles(file)
    val tasks = for (phpFile <- files.zipWithIndex) yield future {
      //println(s"${phpFile._2}/${files.size} ${phpFile._1.toPath().toString()}")
      (new ASTVisitor(Paths.get(phpFile._1.getAbsolutePath))).retrieveStringLiterals()
    }
    val aggregated = Future.sequence(tasks)
    var parsed = Await.result(aggregated, 45.minutes)
    
    // sequentieller shit
//    val parsed = getPHPFiles(file).map {
//      file =>
//        {
//          print("#")
//          (new ASTVisitor(Paths.get(file.getAbsolutePath))).retrieveStringLiterals()
//        }
//    }
    println("Merging parse results")
    val string_literals = parsed.map(p => p._1).foldLeft(Set[StringValue]())(_ union _)
    val include_expressions = parsed.map(p => p._2).foldLeft(Set[(String, Int)]())(_ union _)
    println("Finished parsing.")
    return (string_literals, include_expressions)
  }

  /**
   * Returns a stream of all files having the file extension ".php" or ".inc" for a
   * given system location (the system root folder).
   *
   * @param file java.io.File Root location of the system
   * @return java. of PHP/INC files
   */
  def getPHPFiles(file: File): Stream[File] = {

    // Get file tree
    def getFileTree(f: File): Stream[File] = {
      f #:: (if (f.isDirectory) f.listFiles().toStream.flatMap(getFileTree)
      else Stream.empty)
    }

    // Look for file names ending with ".inc" or ".php"
    getFileTree(file).filter(p => p.getName.endsWith(".php") || p.getName.endsWith(".inc"))
  }

}
