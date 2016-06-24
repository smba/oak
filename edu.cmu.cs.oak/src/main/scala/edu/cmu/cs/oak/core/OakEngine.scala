package edu.cmu.cs.oak.core

import java.io.File
import java.io.FileReader
import java.io.StringReader
import java.net.URL
import java.nio.file.Path

import scala.io.Source

import com.caucho.quercus.QuercusContext
import com.caucho.quercus.parser.QuercusParser
import com.caucho.quercus.program.QuercusProgram
import com.caucho.quercus.script.EncoderStream
import com.caucho.vfs.FilePath
import com.caucho.vfs.ReadStream
import com.caucho.vfs.VfsStream
import java.io.FileInputStream

/**
 * Loads scripts, handles the Quercus parser
 * and manages the interpreter.
 */
class OakEngine {

  /**
   * @param path to the PHP source file to parse
   * @return QuercusProgram parsed AST
   */
  def loadFromFile(path: Path): QuercusProgram = {
    val reader = new FileReader(path.toString)

    val parser = {
      val context = OakEngine.getQuercus("UTF-8", true)
      val pathObject = new FilePath(path.toString())
      new QuercusParser(context, pathObject, reader)
    }
    return parser.parse()
  }

  def loadFromFile2(pathe: Path): QuercusProgram = {
    val filename = pathe.toString()
    val path = new FilePath(filename)
    val sb = new StringBuilder()
    for (line <- Source.fromFile(filename).getLines) {
      sb.append(line)
    }
    val engine = new OakEngine
    engine.loadFromScript(sb.mkString("\n"), path)
  }
  
  def loadAndParse(path: Path): QuercusProgram = {
    
    // Create a new java.io.FileReader for the source file
    val fileInputStream = new FileReader(path.toFile().getAbsolutePath)

    // Create a new FilePath instance for the QuercusParser
    val sourcePath = new FilePath(path.getFileName.toString())
    
    // Get the Quercus context
    val context = OakEngine.getQuercus("UTF-8", true)
    context.setPwd(sourcePath)
    
    QuercusParser.parse(context, sourcePath, fileInputStream)
  }

  /**
   * @param script source code to parse, e.g. "<?php echo 'Hi!'; ?>"
   * @return QuercusProgram parsed AST
   */
  def loadFromScript(script: String, path: com.caucho.vfs.Path): QuercusProgram = {

    val contentReader = new StringReader(script)

    val quercus = OakEngine.getQuercus("UTF-8", true) // TODO isUnicodeSemantics

    /*
     * TODO Check encoding of script string and/or source file
     */
    val program: QuercusProgram = try {
      if ( /* TODO isUnicodeSemantics*/ true) {
        QuercusParser.parse(quercus, path, contentReader);
      } else {
        val is = EncoderStream.open(contentReader, quercus.getScriptEncoding());
        val rs = new ReadStream(new VfsStream(is, null));
        QuercusParser.parse(quercus, path, rs)
      }
    } catch {
      case e: Exception => throw new RuntimeException(e)
    }
    return program
  }

  /**
   *
   */
  def loadResource(fileName: String): File = {
    val file = try {
      new File(getClass.getResource("/" + fileName).toString)
    } catch {
      case e: Exception => throw new RuntimeException(e)
    }
    return file
  }
}

object Bener extends App {

}

object OakEngine {

  private val quercus = new QuercusContext();

  def getQuercus(scriptEncoding: String, isUnicodeSemantics: Boolean): QuercusContext = {
    quercus.setScriptEncoding(scriptEncoding)
    quercus.setUnicodeSemantics(isUnicodeSemantics)
    quercus.init
    quercus.start
    return quercus
  }
}