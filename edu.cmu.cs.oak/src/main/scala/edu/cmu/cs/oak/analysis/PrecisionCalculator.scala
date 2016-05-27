package edu.cmu.cs.oak.analysis

import edu.cmu.cs.oak.value.StringValue
import java.net.URL
import java.nio.file.Path

object PrecisionCalculator {

  /**
   * Computes the precision of the (output-oriented)
   * symbolic execution by measuring the ration of
   * available string literals and literals occuring
   * in the output.
   * String literals are matched by file name and line
   * in which they have been found, according to the AST.
   *
   * @param s1 Set of string literals retrieved from the DNode tree
   * @param s2 Set of available string literals, retrieved from the AST
   *
   * @return Ratio of found and available string literals
   */
  def computePrecision(retrieved: Set[StringValue], available: Set[StringValue]): (Int, Int) = {

    // |retrieved| <= |available|
    assert(retrieved.size <= available.size)
    ((retrieved intersect available).size, available.size)
  }

  def notCoveredLiterals(retrieved: Set[StringValue], available: Set[StringValue]): Set[StringValue] = {

    // |retrieved| <= |available|
    retrieved &~ available
  }

  def availableLiterals(path: Path): Set[StringValue] = {
    val visitor = new ASTVisitor(path)
    visitor.init
    return visitor.retrieveStringLiterals()
  }

}