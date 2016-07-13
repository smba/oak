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

  private val quercus = new QuercusContext()

//  def getQuercus(scriptEncoding: String, isUnicodeSemantics: Boolean): QuercusContext = {
    quercus.setScriptEncoding("UTF-8")
    quercus.setUnicodeSemantics(true)
    quercus.init
    quercus.start
//    return quercus
//  }
  
  /**
   * @param path to the PHP source file to parse
   * @return QuercusProgram parsed AST
   */
  def loadFromFile(path: Path): QuercusProgram = {
    val reader = new FileReader(path.toString)

    val parser = {
//      val context = getQuercus("UTF-8", true)
      val pathObject = new FilePath(path.toString())
      new QuercusParser(quercus, pathObject, reader)
    }
    return parser.parse()
  }


  
  def loadAndParse(path: Path): QuercusProgram = {
    
    // Create a new java.io.FileReader for the source file
    val fileInputStream = new FileReader(path.toFile().getAbsolutePath)

    // Create a new FilePath instance for the QuercusParser
    val sourcePath = new FilePath(path.getFileName.toString())
    
    // Get the Quercus context
    quercus.setPwd(sourcePath)
    
    QuercusParser.parse(quercus, sourcePath, fileInputStream)
  }

  /**
   * @param script source code to parse, e.g. "<?php echo 'Hi!'; ?>"
   * @return QuercusProgram parsed AST
   */
  def loadFromScript(script: String): QuercusProgram = {

    val contentReader = new StringReader(script)

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

object Bener extends App {

}

object OakEngine {

  
}