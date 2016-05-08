package edu.cmu.cs.oak

import org.junit.runner.RunWith
import org.scalatest.FunSpec
import org.scalatest.junit.JUnitRunner

import edu.cmu.cs.oak.core.OakEngine
import edu.cmu.cs.oak.core.OakInterpreter
import edu.cmu.cs.oak.env.Environment
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.value.DoubleValue
import edu.cmu.cs.oak.value.BooleanValue
import edu.cmu.cs.oak.value.StringValue
import edu.cmu.cs.oak.value.Choice

/**
 * This test class contains unit tests based on PHP snippets and its
 * corresponding expected behavior. We test expressions and statements
 * for basic code coverage.
 */
@RunWith(classOf[JUnitRunner]) //optional
class OakUnitTest extends FunSpec {

  // engine and interpreter instance for testing
  val engine = new OakEngine()
  val interpreter = new OakInterpreter()

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
  def loadAndExecute(fileName: String): (String, Environment) = {
    val program = engine.loadFromFile(getClass.getResource("/" + fileName))
    return interpreter.execute(program)
  }

  describe("Expression") {
    describe("ExprStatement with") {
      describe("VarExpr") {
        it("should be evaluated correctly") {
          assert(readAndExecute("$i = 5;")._2.lookup("$i") == IntValue(5))
          assert(readAndExecute("$i = 13.37;")._2.lookup("$i") == DoubleValue(13.37))
          assert(readAndExecute("$i = 42;")._2.lookup("$i") == IntValue(42))
          assert(readAndExecute("$i = false;")._2.lookup("$i") == BooleanValue(false))
          assert(readAndExecute("$i = true;")._2.lookup("$i") == BooleanValue(true))
          assert(readAndExecute("$i = 'tisch';")._2.lookup("$i") == StringValue("tisch"))
          assert(readAndExecute("$i = 3; $j = $i;")._2.lookup("$j") == IntValue(3))
        }
      }
    }
    describe("BinaryExpression with") {
      describe("BooleanExpression") {
        it("should be evaluated correctly") {
          assert(readAndExecute("$b = true; $j = $b && false;")._2.lookup("$j") == BooleanValue(false))
          assert(readAndExecute("$b = true; $j = $b || false;")._2.lookup("$j") == BooleanValue(true))
          assert(readAndExecute("$b = true; $j = !$b;")._2.lookup("$j") == BooleanValue(false))
        }
        it("may happen") {
          println(readAndExecute("$i = 10; echo 'A'; if ($i < '32öld') { $j = $i < '32öld'; echo 'echo'; $k = 99; echo $j + 1;} else {$j = 6; echo 'echo'; echo 'BI4TCH';} echo $j; while ($i < '900f0') { echo 'Z';}")._2.getOutput().mkString(" "))

          println(readAndExecute("$a = 'a'; if ($a == 9) {echo 'Brief';} else { if ($a == 10) { if ($a == 40) { $j = 1337;} else {echo 'wesen';} } else { echo 'zelt';} }")._2.getOutput().mkString(" "))

          println(readAndExecute("function foo($x) { if ($x == 8) {echo 'erst das';} else { echo 'oder das';} echo 'dann das'; return 'und dann das' + $x;} echo foo(3); echo foob('');")._2.getOutput().mkString(" "))

          println(loadAndExecute("bener.php")._2.getOutput().mkString(" "))
        }
      }
      describe("NumericExpr") {
        describe("Arithmetic expressions") {
          it("should evaluate additions correctly") {
            assert(readAndExecute("$i = 1 + 1")._2.lookup("$i") == IntValue(2))
            assert(readAndExecute("$i = 1 + 1.1")._2.lookup("$i") == DoubleValue(2.1))
            assert(readAndExecute("$i = 1.1 + 1.1")._2.lookup("$i") == DoubleValue(2.2))
          }
          it("should evaluate subtraction correctly") {
            assert(readAndExecute("$i = 1 - 1")._2.lookup("$i") == IntValue(0))
            assert(readAndExecute("$i = 1 - 0.5")._2.lookup("$i") == DoubleValue(0.5))
            assert(readAndExecute("$i = 1.2 - 0.6")._2.lookup("$i") == DoubleValue(0.6))
          }
          it("should evaluate multiplications correctly") {
            assert(readAndExecute("$i = 1 * 2")._2.lookup("$i") == IntValue(2))
            assert(readAndExecute("$i = 1 * 2.1")._2.lookup("$i") == DoubleValue(2.1))
            assert(readAndExecute("$i = 1.0 * 2.1")._2.lookup("$i") == DoubleValue(2.1))
          }
          it("should evaluate divisions correctly") {
            assert(readAndExecute("$i = 6 / 2")._2.lookup("$i") == IntValue(3))
            assert(readAndExecute("$i = 6 / 2.0")._2.lookup("$i") == IntValue(3))
            assert(readAndExecute("$i = 5.2 / 2.0")._2.lookup("$i") == DoubleValue(2.6))
          }
          it("should evaluate mod operations correctly") {
            assert(readAndExecute("$i = 6 % 5")._2.lookup("$i") == IntValue(1))
            assert(readAndExecute("$i = 6 % 5.0")._2.lookup("$i") == IntValue(1))
          }
        }
      }
    }
  }

  describe("Statements") {

    describe("IfStatement") {
      it("should be executed correctly") {
        assert(readAndExecute("$i = 0; if ($i < 1) {$j = 1;} else {$j = 0}")._2.lookup("$j") == IntValue(1))
        assert(readAndExecute("$i = 1; if ($i < 1) {$j = 1;} else {$j = 0}")._2.lookup("$j") == IntValue(0))

        readAndExecute("echo 'INITIAL'; $i = 1; if ($i < 'g') { echo 'A'; echo 'B';} else { echo 'A'; echo 'C';}; echo 'D';")

        // symbolic execution with Choice
        assert(readAndExecute("$i = 0; if ($i < 'i') {$j = 1;} else {$j = 0}")._2.lookup("$j") == Choice("true && ($i < \"i\")", IntValue(1), IntValue(0)))

      }
    }

    describe("WhileStatement") {
      it("should be executed correctly ,") {
        assert(readAndExecute("$i = 0; $j = 2; while ($i < 10) {$j = $j*$j; $i = $i+1;}")._2.lookup("$j") == Choice("true && ($i < 10)", IntValue(4), IntValue(2)))
      }
      it("a") {
        val source = "function f($i, $k) { return $i + $k;} $i = 1; $j = f($i, 2);"
        val env = readAndExecute(source)
        assert(env._2.lookup("$i") == IntValue(1) && env._2.lookup("$j") == IntValue(3))

      }
      it("b") {
        val env = readAndExecute("echo 'nm'; function f() { echo 'here';} f(); echo 'there';")
        println(env._2.getOutput.reverse.mkString(" "))
        assert(env._2.getOutput.last == StringValue("there"))
      }

      it("c") {
        val source = "echo 'A'; function f($x) {echo 'pre'; if ($x > 0) { echo 'B2D'; } else { echo 'B3'; } echo 'post';} f(1); f('text'); echo 'Z';"
        val env = readAndExecute(source)
        println(env._2.getOutput.toString)
      }
    }
  }
}