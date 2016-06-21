package edu.cmu.cs.oak

import scala.xml.PrettyPrinter

import org.junit.runner.RunWith
import org.scalatest.FunSpec
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.core.OakEngine
import java.nio.file.Paths
import edu.cmu.cs.oak.env.Environment
import java.nio.file.Path
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.BooleanValue
import edu.cmu.cs.oak.value.Choice
import scala.collection.immutable.Stack
import edu.cmu.cs.oak.value.ArrayValue
import edu.cmu.cs.oak.value.DoubleValue
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.BooleanValue
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.DoubleValue
import edu.cmu.cs.oak.value.SymbolValue

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
    return interpreter.execute(engine.loadFromScript("<?php " + script + " ?>"), new Environment(null, Stack[String](), "true"))
  }

  /**
   * Read a PHP source code from file, parses & executes it.
   *
   * @param script PHP source code file
   * @return (ControlCode, Environment)
   */
  def loadAndExecute(path: Path): (String) = {
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
    //assert(RegressionTest.test(url("testScripts/ifStatement01.php"))._1)
    assert(loadAndExecute(url("testScripts/ifStatement04.php"))._2.lookup("$j").toString() equals "Π⟨1, 0⟩" )
  }

  test("WhileStatement") {
    assert(readAndExecute("$i = 0; $j = 2; while ($i < 10) {$j = $j*$j; $i = $i+1;}")._2.lookup("$j") == IntValue(4))
  }

  test("Function calls, branching") {
    var env = loadAndExecute(url("testScripts/functions02.php"))
    assert(env._2.lookup("$i") == IntValue(1) && env._2.lookup("$j") == IntValue(3))

    //env = loadAndExecute(url("testScripts/functions03.php"))
    //assert(env._2.getOutput.last == StringValue("there"))

    //assert(RegressionTest.test(url("testScripts/functions01.php"))._1)
  }

  test("count()") {
    //assert(RegressionTest.test(url("testScripts/count01.php"))._1)
  }
  
  test("Array values") {
    //assert(RegressionTest.test(url("testScripts/arrayValues01.php"))._1)
  }
  
  test("Reference values") {
    var env = readAndExecute("$a = 1; $b = &$a;	$b += 1;echo $a;")._2
    assert(env.lookup("$a").isInstanceOf[SymbolValue] && env.lookup("$b").isInstanceOf[SymbolValue])

    env = readAndExecute("$a = array(3,2,1); $b = &$a[0]; $b *= $b; echo $a[0];")._2
    assert(env.lookup("$b").isInstanceOf[SymbolValue] && env.lookup("$a").asInstanceOf[ArrayValue].get(IntValue(0), env).isInstanceOf[SymbolValue])

    env = readAndExecute("function foo(&$x) { $x *= 2; } $a = 2; foo($a); echo $a;")._2
    assert(env.lookup("$a") == IntValue(4))
  }

  test("Objects") {
    //assert(RegressionTest.test(url("testScripts/objects01.php"))._1)
  }
  
  test("Schoolmate") {
    //assert(RegressionTest.test(url("schoolmate/index.php"))._1)
  }
  
  test("Env1") {
    val result = loadAndExecute(url("environments/env01.php"))
    assert(result._2.getOutput().toString() equals "π(($x < 0),A,B)CSymbolNode([Symbolic:(\"a\" < 9)])beceZ")
  }
  
 test("switch") {
    val result = loadAndExecute(url("environments/switch.php"))
    assert(result._2.output+"" equals "π($o == 1,A,π($o == 2,B,π($o == 3,C,D)))π($o == 1,5,π($o == 2,6,π($o == 3,4,4)))") 
  }
 
 test("While") {
   val result = loadAndExecute(url("environments/while.php"))
   assert(result._2.getOutput().toString() equals "ρ(AC)Bb")
 }
	*/
}