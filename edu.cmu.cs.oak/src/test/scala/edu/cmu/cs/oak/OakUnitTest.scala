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
import edu.cmu.cs.oak.core.ControlCode
import java.io.PrintWriter
import java.io.File
import edu.cmu.cs.oak.nodes.DNodeParser
import java.time.Instant
import java.time.Duration

/**
 * This test class contains unit tests based on PHP snippets and its
 * corresponding expected behavior. We test expressions and statements
 * for basic code coverage.
 */

//@RunWith(classOf[JUnitRunner]) //optional
object OakUnitTest extends App {

  // engine and interpreter instance for testing
  val engine = new OakEngine()
  val interpreter = new OakInterpreter()

  //val pp = new PrettyPrinter(200, 0)

  /**
   * Read a PHP source code from file, parses & executes it.
   *
   * @param script PHP source code file
   * @return (ControlCode, Environment)
   */
  def loadAndExecute(path: Path): (ControlCode.Value, Environment) = {
    return interpreter.execute(path)
  }

  /* utility method */
  def url(fileName: String): Path = {
    Paths.get(getClass.getResource("/" + fileName).getPath)
  }

  /*
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
* */
//  val env = loadAndExecute(url("environments/env02.php"))
  val before = Instant.now()
  val env = loadAndExecute(url("wordpress/wp-admin/install.php"))
  val after = Instant.now()
  println(Duration.between(before, after).toString())
  val pw = new PrintWriter(new File("/home/stefan/Desktop/output.xml"))
  pw.write(env._2.getOutputAsPrettyXML())
  pw.close
}