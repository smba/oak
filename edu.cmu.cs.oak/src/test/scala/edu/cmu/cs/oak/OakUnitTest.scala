package edu.cmu.cs.oak

import scala.xml.PrettyPrinter

import org.junit.runner.RunWith
import org.scalatest.FunSpec
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/**
 * This test class contains unit tests based on PHP snippets and its
 * corresponding expected behavior. We test expressions and statements
 * for basic code coverage.
 */

@RunWith(classOf[JUnitRunner]) //optional
object OakUnitTest extends FunSuite {

  /*
  // engine and interpreter instance for testing
  val engine = new OakEngine()
  val interpreter = new OakInterpreter()

  //val pp = new PrettyPrinter(200, 0)

  /**
   * Reads a PHP source code string, parses & executes it.
   *
   * @param script PHP source code
   * @return (ControlCode, Environment)
   */
  def readAndExecute(script: String): (String, Environment) = {
    return interpreter.execute(engine.loadFromScript("<?php " + script + " ?>"), new SimpleEnv(null, Stack[String](), "true"))
  }

  /**
   * Read a PHP source code from file, parses & executes it.
   *
   * @param script PHP source code file
   * @return (ControlCode, Environment)
   */
  def loadAndExecute(path: Path): (String, Environment) = {
    return interpreter.execute(path)
  }

  /* utility method */
  def url(fileName: String): Path = {
    Paths.get(getClass.getResource("/" + fileName).getPath)
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
    assert(RegressionTest.test(url("testScripts/ifStatement01.php"))._1)
    assert(loadAndExecute(url("testScripts/ifStatement02.php"))._2.lookup("$j") == IntValue(1))
    assert(loadAndExecute(url("testScripts/ifStatement03.php"))._2.lookup("$j") == IntValue(0))
    assert(loadAndExecute(url("testScripts/ifStatement04.php"))._2.lookup("$j") == Choice("true && ($i < \"i\")", IntValue(1), IntValue(0)))
  }

  test("WhileStatement") {
    assert(readAndExecute("$i = 0; $j = 2; while ($i < 10) {$j = $j*$j; $i = $i+1;}")._2.lookup("$j") == Choice("true && ($i < 10)", IntValue(4), IntValue(2)))
  }

  test("Function calls, branching") {
    var env = loadAndExecute(url("testScripts/functions02.php"))
    assert(env._2.lookup("$i") == IntValue(1) && env._2.lookup("$j") == IntValue(3))

    env = loadAndExecute(url("testScripts/functions03.php"))
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
    var env = readAndExecute("$a = 1; $b = &$a;	$b += 1;echo $a;")._2
    assert(env.lookup("$a") == IntValue(2) && env.lookup("$b") == IntValue(2))

    env = readAndExecute("$a = array(3,2,1); $b = &$a[0]; $b *= $b; echo $a[0];")._2
    assert(env.lookup("$b") == IntValue(9) && env.lookup("$a").asInstanceOf[ArrayValue].get(IntValue(0)) == IntValue(9))

    env = readAndExecute("function foo(&$x) { $x *= 2; } $a = 2; foo($a); echo $a;")._2
    assert(env.lookup("$a") == IntValue(4))
  }

  test("Objects") {
    assert(RegressionTest.test(url("testScripts/objects01.php"))._1)
  }
  
  test("Schoolmate") {
    assert(RegressionTest.test(url("schoolmate/index.php"))._1)
  }
	*/
}