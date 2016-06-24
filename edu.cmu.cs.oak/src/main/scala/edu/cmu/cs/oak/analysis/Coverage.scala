package edu.cmu.cs.oak.analysis

import java.io.File
import java.nio.file.Paths

import edu.cmu.cs.oak.value.StringValue

class Coverage {

  

}

object Coverage extends App {
  
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
  
  val literals = getProjectLiterals(new File("/home/stefan/git/oak/edu.cmu.cs.oak/src/test/resources/schoolmate"))
  println("Found " + literals.size + " literals with "  + literals.map{l=>l.value.size}.foldLeft(0)(_ + _ ) + " characters.")
}