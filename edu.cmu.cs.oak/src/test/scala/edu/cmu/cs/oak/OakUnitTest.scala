package edu.cmu.cs.oak

import java.net.URL

import org.scalatest.FunSpec
import org.scalatest.junit.JUnitRunner

import edu.cmu.cs.oak.analysis.ASTVisitor
import edu.cmu.cs.oak.core.OakEngine
import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.value.OakValueSequence
import edu.cmu.cs.oak.nodes.ConcatNode
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.BooleanValue
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.ObjectValue
import edu.cmu.cs.oak.value.DoubleValue
import org.junit.runner.RunWith
import scala.xml.PrettyPrinter
import org.scalatest.FunSuite

/**
 * This test class contains unit tests based on PHP snippets and its
 * corresponding expected behavior. We test expressions and statements
 * for basic code coverage.
 */

@RunWith(classOf[JUnitRunner]) //optional
object OakUnitTest extends FunSuite {

  // engine and interpreter instance for testing
  val engine = new OakEngine()
  val interpreter = new OakInterpreter()

  val pp = new PrettyPrinter(200, 0)

  /**
   * Reads a PHP source code string, parses & executes it.
   *
   * @param script PHP source code
   * @return (ControlCode, Environment)
   */
  def readAndExecute(script: String): (String, Environment) = {
    return interpreter.execute(engine.loadFromScript("<?php " + script + " ?>"))
  }

  /**
   * Read a PHP source code from file, parses & executes it.
   *
   * @param script PHP source code file
   * @return (ControlCode, Environment)
   */
  def loadAndExecute(url: URL): (String, Environment) = {
    val program = engine.loadFromFile(url)
    return interpreter.execute(program)
  }

  /* utility method */
  def url(fileName: String): URL = {
    getClass.getResource("/" + fileName)
  }

  test("VarExpr") {
    assert(readAndExecute("$i = 5;")._2.lookup("$i") == IntValue(5))
    assert(readAndExecute("$i = 13.37;")._2.lookup("$i") == DoubleValue(13.37))
    assert(readAndExecute("$i = 42;")._2.lookup("$i") == IntValue(42))
    assert(readAndExecute("$i = false;")._2.lookup("$i") == BooleanValue(false))
    assert(readAndExecute("$i = true;")._2.lookup("$i") == BooleanValue(true))
    assert(readAndExecute("$i = 'tisch';")._2.lookup("$i") == StringValue("tisch"))
    assert(readAndExecute("$i = 3; $j = $i;")._2.lookup("$j") == IntValue(3))
  }

  test("BinaryExpression + BooleanExpr") {
    assert(readAndExecute("$b = true; $j = $b && false;")._2.lookup("$j") == BooleanValue(false))
    assert(readAndExecute("$b = true; $j = $b || false;")._2.lookup("$j") == BooleanValue(true))
    assert(readAndExecute("$b = true; $j = !$b;")._2.lookup("$j") == BooleanValue(false))
  }

  test("BinaryExpression + ArithmeticExpr") {
    assert(readAndExecute("$i = 1 + 1")._2.lookup("$i") == IntValue(2))
    assert(readAndExecute("$i = 1 + 1.1")._2.lookup("$i") == DoubleValue(2.1))
    assert(readAndExecute("$i = 1.1 + 1.1")._2.lookup("$i") == DoubleValue(2.2))

    assert(readAndExecute("$i = 1 - 1")._2.lookup("$i") == IntValue(0))
    assert(readAndExecute("$i = 1 - 0.5")._2.lookup("$i") == DoubleValue(0.5))
    assert(readAndExecute("$i = 1.2 - 0.6")._2.lookup("$i") == DoubleValue(0.6))

    assert(readAndExecute("$i = 1 * 2")._2.lookup("$i") == IntValue(2))
    assert(readAndExecute("$i = 1 * 2.1")._2.lookup("$i") == DoubleValue(2.1))
    assert(readAndExecute("$i = 1.0 * 2.1")._2.lookup("$i") == DoubleValue(2.1))

    assert(readAndExecute("$i = 6 / 2")._2.lookup("$i") == IntValue(3))
    assert(readAndExecute("$i = 6 / 2.0")._2.lookup("$i") == IntValue(3))
    assert(readAndExecute("$i = 5.2 / 2.0")._2.lookup("$i") == DoubleValue(2.6))

    assert(readAndExecute("$i = 6 % 5")._2.lookup("$i") == IntValue(1))
    assert(readAndExecute("$i = 6 % 5.0")._2.lookup("$i") == IntValue(1))
  }

  test("IfStatement") {
    assert(readAndExecute("$i = 0; if ($i < 1) {$j = 1;} else {$j = 0}")._2.lookup("$j") == IntValue(1))
    assert(readAndExecute("$i = 1; if ($i < 1) {$j = 1;} else {$j = 0}")._2.lookup("$j") == IntValue(0))
    assert(RegressionTest.test(url("testScripts/ifStatement01.php"))._1)
    assert(readAndExecute("$i = 0; if ($i < 'i') {$j = 1;} else {$j = 0}")._2.lookup("$j") == Choice("true && ($i < \"i\")", IntValue(1), IntValue(0)))
  }

  test("WhileStatement") {
    assert(readAndExecute("$i = 0; $j = 2; while ($i < 10) {$j = $j*$j; $i = $i+1;}")._2.lookup("$j") == Choice("true && ($i < 10)", IntValue(4), IntValue(2)))
  }

  test("Function calls, branching") {
    var env = readAndExecute("function f($i, $k) { return $i + $k;} $i = 1; $j = f($i, 2);")
    assert(env._2.lookup("$i") == IntValue(1) && env._2.lookup("$j") == IntValue(3))

    env = readAndExecute("echo 'nm'; function f() { echo 'here';} f(); echo 'there';")
    assert(env._2.getOutput.last == StringValue("there"))

    assert(RegressionTest.test(url("testScripts/functions01.php"))._1)
  }

  test("count()") {
    assert(RegressionTest.test(url("testScripts/count01.php"))._1)
  }
  
  test("Array values") {
    assert(RegressionTest.test(url("testScripts/arrayValues01.php"))._1)
  }
  
  test("Reference values") {
    var env = loadAndExecute(url("referenceValue01.php"))._2
    assert(env.lookup("$a") == IntValue(2) && env.lookup("$b") == IntValue(2))

    env = loadAndExecute(url("referenceValue02.php"))._2
    assert(env.lookup("$b") == IntValue(9) && env.lookup("$a").asInstanceOf[ArrayValue].get(IntValue(0)) == IntValue(9))

    env = loadAndExecute(url("referenceValue03.php"))._2
    assert(env.lookup("$a") == IntValue(4))
  }

  test("Objects") {
    assert(RegressionTest.test(url("testScripts/objects01.php"))._1)
  }

}