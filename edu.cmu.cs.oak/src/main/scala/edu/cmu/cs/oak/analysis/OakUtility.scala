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
import java.io.FileOutputStream
import edu.cmu.cs.oak.value.StringLiteralContext
import java.io.ObjectOutputStream
import java.io.FileInputStream
import java.io.ObjectInputStream

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

    val filename = file.toPath().toAbsolutePath().toString().hashCode() + ".cache"
    val path = Paths.get(OakUtility.url("").toString + "/" + filename)

    return if (path.toFile().exists()) { // ergebnisse schon mal gelesen
      val ois = new ObjectInputStream(new FileInputStream(path.toFile))
      val r = ois.readObject.asInstanceOf[ (Set[StringValue], Set[(String, Int)]) ]
      ois.close
      r
    } else { // parse und schreibe den shit
      // Parallel parsing tasks
      val files = getPHPFiles(file)
      val tasks = for (phpFile <- files.zipWithIndex) yield future {
        println(s"${phpFile._2}/${files.size} ${phpFile._1.toPath().toString()}")
        (new ASTVisitor(Paths.get(phpFile._1.getAbsolutePath))).retrieveStringLiterals()
      }
      val aggregated = Future.sequence(tasks)
      var parsed = Await.result(aggregated, 45.minutes)

      val string_literals = parsed.map(p => p._1).foldLeft(Set[StringValue]())(_ union _)
      val include_expressions = parsed.map(p => p._2).foldLeft(Set[(String, Int)]())(_ union _)

      val oos = new ObjectOutputStream(new FileOutputStream(path.toFile()))
      oos.writeObject((string_literals, include_expressions))
      oos.close

      (string_literals, include_expressions)
    }

  }

  def deserializeParseResults(file: File): (Set[StringValue], Set[(String, Int)]) = {
    val filename = file.toPath().toAbsolutePath().toString().hashCode() + ".xml"
    val path = Paths.get(OakUtility.url("").toString + "/" + filename)
    def p(label: String): Boolean = !(label equals "#PCDATA")

    def parseContent(node: scala.xml.Node): (Set[StringValue], Set[(String, Int)]) = {
      val nodes = node.child.filter {
        n => p(n.label)
      }
      (parseLiterals(nodes.head), parseIncludes(nodes.last))
    }

    def parseIncludes(node: scala.xml.Node): Set[(String, Int)] = {
      val nodes = node.child.filter {
        n => p(n.label)
      }
      nodes.map(node => parseInclude(node)).toSet
    }

    def parseInclude(node: scala.xml.Node): (String, Int) = {
      val file = node.attribute("file").head.text.trim
      val line = node.attribute("line").head.text.trim.toInt
      (file, line)
    }

    def parseLiterals(node: scala.xml.Node): Set[StringValue] = {
      val nodes = node.child.filter {
        n => p(n.label)
      }
      nodes.map(node => parseLiteral(node)).toSet
    }

    def parseLiteral(node: scala.xml.Node): StringValue = {
      val value = node.text
      val file = node.attribute("line").head.text.trim
      val line = node.attribute("line").head.text.trim.toInt
      val context = node.attribute("context").head.text.trim match {
        case "MISC" => StringLiteralContext.MISC
        case "FDEFINITION" => StringLiteralContext.FDEFINITION
        case "TEMPLATE" => StringLiteralContext.TEMPLATE
      }
      val fdef = {
        val name = node.attribute("fdef_name").head.text.trim
        val file = node.attribute("fdef_file").head.text.trim
        val line = node.attribute("fdef_line").head.text.trim.toInt
        (name, (file, line))
      }
      val sv = StringValue(value, file, line)
      sv.context = context
      sv.fdef = fdef
      return sv
    }

    parseContent(scala.xml.XML.loadFile(path.toFile))
  }

  def serializeParseResults(file: File, literals: Set[StringValue], includes: Set[(String, Int)]) {
    val filename = file.toPath().toAbsolutePath().toString().hashCode() + ".xml"
    val literals_xml = {
      <Literals>
        {
          for (literal <- literals) yield <Literal file={ literal.file } line={ literal.lineNr.toString } context={ literal.context.toString } fdef_name={ literal.fdef._1 } fdef_file={ literal.fdef._1 } fdef_line={ literal.fdef._2._2.toString }>
                                            { literal.value }
                                          </Literal>
        }
      </Literals>
    }
    val includes_xml = {
      <Includes>
        {
          for (include <- includes) yield <Include file={ include._1 } line={ include._2.toString }/>
        }
      </Includes>
    }
    val node = {
      <Content>
        { literals_xml }
        { includes_xml }
      </Content>
    }
    val path = Paths.get(OakUtility.url("").toString + "/" + filename)
    new FileOutputStream(path.toAbsolutePath().toString(), false).close();
    val pw = new PrintWriter(path.toFile())
    pw.write((new scala.xml.PrettyPrinter(80, 2)).format(node))
    pw.close
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
