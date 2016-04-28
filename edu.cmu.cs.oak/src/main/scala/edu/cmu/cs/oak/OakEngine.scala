package edu.cmu.cs.oak

import java.io.StringReader

import scala.io.Source

import com.caucho.quercus.QuercusContext
import com.caucho.quercus.parser.QuercusParser
import com.caucho.quercus.program.QuercusProgram
import com.caucho.quercus.script.EncoderStream
import com.caucho.vfs.ReadStream
import com.caucho.vfs.VfsStream
import com.caucho.quercus.statement.BlockStatement
import java.nio.charset.CharsetDecoder

/**
 * Loads scripts, handles the Quercus parser and manages the interpreter.
 */
class OakEngine {
  
  /**
   * @param Absolute path to the PHP source file to parse
   * @return QuercusProgram parsed AST
   */
  def loadFromFile(path: String): QuercusProgram = {
    val content = try {
      Source.fromFile(path, "UTF-8").getLines.mkString
    } catch {
      case e: Exception => throw new RuntimeException(e)
    }
    return loadFromScript(content)
  }

  /**
   * @param PHP source code to parse, e.g. "<?php echo 'Hi!'; ?>"
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