import edu.cmu.cs.oak.core.{Interpreter, OakEngine}
import edu.cmu.cs.oak.env._
import org.scalatest.FlatSpec

object OakTest {
  def engine = new OakEngine()
}

/**
  * TODO simplify
  */
class OakTest extends FlatSpec {

  def loadAndExecute(script: String): Environment = {
    return Interpreter.execute(OakTest.engine.loadFromScript("<?php " + script + " ?>"))
  }

  "Simple ExprStatement with VarExpr (double)" should "be assigned correctly" in {
    assert(loadAndExecute("$i = 13.37;").lookup("$i") == DoubleValue(13.37))
  }
  
  "Simple ExprStatement with VarExpr (int)" should "be assigned correctly" in {
    assert(loadAndExecute("$i = 42;").lookup("$i") == IntValue(42))
  }
  
  "Simple ExprStatement with VarExpr (false)" should "be assigned correctly" in {
    assert(loadAndExecute("$i = false;").lookup("$i") == BooleanValue(false))
  }
  
  "Simple ExprStatement with VarExpr (true)" should "be assigned correctly" in {
    assert(loadAndExecute("$i = true;").lookup("$i") == BooleanValue(true))
  }
  
  "Simple ExprStatement with VarExpr (String)" should "be assigned correctly" in {
    assert(loadAndExecute("$i = 'bener';").lookup("$i") == StringValue("bener"))
  }
  
  "Simple ExprStatement with VarExpr" should "be evaluated correctly" in {
    assert(loadAndExecute("$i = 3; $j = $i;").lookup("$j") == IntValue(3))
  }
  
  "Simple BinaryAndExpr expressions" should "be evaluated correctly" in {
    assert(loadAndExecute("$b = true; $j = $b && false;").lookup("$j") == BooleanValue(false))
  }
  
  "Simple BinaryOrExpr expressions" should "be evaluated correctly" in {
    assert(loadAndExecute("$b = true; $j = $b || false;").lookup("$j") == BooleanValue(true))
  }
  
  "Simple NotExpr expressions" should "be evaluated correctly" in {
    assert(loadAndExecute("$b = true; $j = !$b;").lookup("$j") == BooleanValue(false))
  }
  
  "NumericExpressions PLUS" should "be evaluated correctly" in {
    assert(loadAndExecute("$i = 1 + 1").lookup("$i") == IntValue(2))
    assert(loadAndExecute("$i = 1 + 1.1").lookup("$i") == DoubleValue(2.1))
    assert(loadAndExecute("$i = 1.1 + 1.1").lookup("$i") == DoubleValue(2.2))
  }
  
  "NumericExpressions MINUS" should "be evaluated correctly" in {
    assert(loadAndExecute("$i = 1 - 1").lookup("$i") == IntValue(0))
    assert(loadAndExecute("$i = 1 - 0.5").lookup("$i") == DoubleValue(0.5))
    assert(loadAndExecute("$i = 1.2 - 0.6").lookup("$i") == DoubleValue(0.6))
  }
  
  "NumericExpressions MUL" should "be evaluated correctly" in {
    assert(loadAndExecute("$i = 1 * 2").lookup("$i") == IntValue(2))
    assert(loadAndExecute("$i = 1 * 2.1").lookup("$i") == DoubleValue(2.1))
    assert(loadAndExecute("$i = 1.0 * 2.1").lookup("$i") == DoubleValue(2.1))
  }
  
  "NumericExpressions DIV" should "be evaluated correctly" in {
    assert(loadAndExecute("$i = 6 / 2").lookup("$i") == IntValue(3))
    assert(loadAndExecute("$i = 6 / 2.0").lookup("$i") == IntValue(3))
    assert(loadAndExecute("$i = 5.2 / 2.0").lookup("$i") == DoubleValue(2.6))
  }
  
  "NumericExpressions MOD" should "be evaluated correctly" in {
    assert(loadAndExecute("$i = 6 % 5").lookup("$i") == IntValue(1))
    assert(loadAndExecute("$i = 6 % 5.0").lookup("$i") == IntValue(1))
  }

  "Simple ExprStatement with String (double)" should "be assigned correctly" in {
    assert(loadAndExecute("$i = 'HelloWorld';").lookup("$i") == StringValue("HelloWorld"))
  }

  "IF statements " should " be executed correctly" in {
    // concrete execution
    assert(loadAndExecute("$i = 0; if ($i < 1) {$j = 1;} else {$j = 0}").lookup("$j") == IntValue(1))
    assert(loadAndExecute("$i = 1; if ($i < 1) {$j = 1;} else {$j = 0}").lookup("$j") == IntValue(0))

    // symbolic execution with Choice
    assert(loadAndExecute("$i = 0; if ($i < 'i') {$j = 1;} else {$j = 0}").lookup("$j") == Choice("true && ($i < \"i\")", IntValue(1), IntValue(0)))
  }

  "WHILE statements " should " be symbolically executed correctly" in {
    // symbolic execution with Choice
    assert(loadAndExecute("$i = 0; $j = 2; while ($i < 10) {$j = $j*$j; $i = $i+1;}").lookup("$j") == Choice("true && ($i < 10)", IntValue(4), IntValue(2)))
  }

  "Function declaration" should "be parsed" in {
    val source = "function f($i, $k) { return $i + $k;} $i = 1; $j = f($i, 2);"
    val env = loadAndExecute(source)
    assert(env.lookup("$i") == IntValue(1) && env.lookup("$j") == IntValue(3))
  }
  
}