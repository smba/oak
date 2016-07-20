package edu.cmu.cs.oak

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RegressionTests extends FunSuite {

  test("Simple Statements") {
    RegressionTest.test("environments/env01.php")
    RegressionTest.test("environments/switch.php")
    RegressionTest.test("environments/while.php")
    RegressionTest.test("testScripts/ordie.php")
  }
  
  test("OOP") {
    RegressionTest.test("testScripts/classes01.php")
    RegressionTest.test("testScripts/objects01.php")
    RegressionTest.test("testScripts/staticFields.php")
  }
  
  test("arrays") {
    RegressionTest.test("testScripts/arrayValues01.php")
    RegressionTest.test("testScripts/nestedArrays.php")
  }
  
  test("References") {
    RegressionTest.test("testScripts/functions01.php")
    RegressionTest.test("testScripts/references01.php")
    RegressionTest.test("testScripts/references02.php")
  }
  
  test("Outer to inner scope") {
    RegressionTest.test("testScripts/outer_to_inner_scope/createLoopEnvironment.php")
    RegressionTest.test("testScripts/outer_to_inner_scope/createFunctionOrMethodEnvironment.php")
  }
  
  test("Global variables") {
    RegressionTest.test("testScripts/globals01.php")
    RegressionTest.test("testScripts/isGlobal.php")
    RegressionTest.test("testScripts/globalscope.php")
  }
  
  test("Global function scoping") {
    RegressionTest.test("testScripts/scoping/global_function_scope01.php")
    RegressionTest.test("testScripts/scoping/global_function_scope02.php")
    RegressionTest.test("testScripts/scoping/global_function_scope_branching01.php")
    RegressionTest.test("testScripts/scoping/global_function_scope_branching02.php")
    RegressionTest.test("testScripts/scoping/global_function_scope_nested.php")
  }
  
  test("Inner to outer scope") {
    RegressionTest.test("testScripts/inner_to_outer_scope/join01.php")
  }

  test("Join optimization") {
    /*
     * Exempted from test suite
     */
    RegressionTest.test("testScripts/multiple.php")
  }
  
  test("Builtin functions") {
    RegressionTest.test("testScripts/substr.php")
  }
  
  test("callbacks") {
    RegressionTest.test("testScripts/callbacks.php")
    
    // Callback tests for WordPress
    //RegressionTest.test("callbackAPI/wp_callback_test.php")
  }
  
  test("Schoolmate") {
    RegressionTest.test("schoolmate/index.php")
  }
	
}