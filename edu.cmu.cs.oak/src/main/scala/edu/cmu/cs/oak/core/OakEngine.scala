package edu.cmu.cs.oak.core

import java.io.StringReader

import com.caucho.quercus.QuercusContext
import com.caucho.quercus.parser.QuercusParser
import com.caucho.quercus.program.QuercusProgram
import com.caucho.quercus.script.EncoderStream
import com.caucho.vfs.{ ReadStream, VfsStream }

import scala.io.Source
import java.net.URL
import java.io.File
import java.io.FileReader
import java.nio.file.Path
import com.caucho.vfs.FilePath

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
    println(new FilePath(path.toString()))
    val parser = new QuercusParser(OakEngine.getQuercus("UTF-8", true), new FilePath(path.toString()), reader)
    return parser.parse()
  }

  /**
   * @param script source code to parse, e.g. "<?php echo 'Hi!'; ?>"
   * @return QuercusProgram parsed AST
   */
  def loadFromScript(script: String): QuercusProgram = {

    val contentReader = new StringReader(script)

    val quercus = OakEngine.getQuercus("UTF-8", true) // TODO isUnicodeSemantics

    /*
     * TODO Check encoding of script string and/or source file
     */
    val program: QuercusProgram = try {
      if ( /* TODO isUnicodeSemantics*/ true) {
        QuercusParser.parse(quercus, null, contentReader);
      } else {
        val is = EncoderStream.open(contentReader, quercus.getScriptEncoding());
        val rs = new ReadStream(new VfsStream(is, null));
        QuercusParser.parse(quercus, null, rs)
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