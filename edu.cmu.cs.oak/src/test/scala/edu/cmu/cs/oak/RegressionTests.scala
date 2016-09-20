package edu.cmu.cs.oak

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner


/**
 * Regression tests
 */
@RunWith(classOf[JUnitRunner])
class RegressionTests extends FunSuite {
 
  test("Simple Statements 1") {
    RegressionTest.test("environments/env01.php")
  }
  test("Simple Statements 2") {
    RegressionTest.test("environments/switch.php")
  }
  test("Simple Statements 3") {
    RegressionTest.test("environments/while.php")
  }
  test("Simple Statements 4") {
    RegressionTest.test("testScripts/ordie.php")
  }
  
  test("OOP 1") {
    RegressionTest.test("testScripts/classes01.php")
  }
  test("OOP 2") {
    RegressionTest.test("testScripts/objects01.php")
  }
  test("OOP 3") {
    RegressionTest.test("testScripts/staticFields.php")
  }
  
  test("Arrays 1") {
    RegressionTest.test("testScripts/arrayValues01.php")
  }
  test("Arrays 2") {
    RegressionTest.test("testScripts/nestedArrays.php")
  }
  test("Arrays 3") {
    RegressionTest.test("testScripts/array_functions.php")
  }
  
  test("References 1") {
    RegressionTest.test("testScripts/functions01.php")
  }
  test("References 2") {
    RegressionTest.test("testScripts/references01.php")
  }
  test("References 3") {
    RegressionTest.test("testScripts/references02.php")
  }
  test("References 4") {
    RegressionTest.test("testScripts/reference_expr.php")
  }
  
  
  test("InnerOuter 1") {
    RegressionTest.test("testScripts/outer_to_inner_scope/createLoopEnvironment.php")
  }
  test("InnerOutern 2") {
    RegressionTest.test("testScripts/outer_to_inner_scope/createFunctionOrMethodEnvironment.php")
  }
  test("InnerOuter 3") {
    RegressionTest.test("testScripts/branching1.php")
  }
  
  
  test("Global 1") {
    RegressionTest.test("testScripts/globals01.php")
  }
  test("Global 2") {
    RegressionTest.test("testScripts/isGlobal.php")
  }
  test("Global 3") {
    RegressionTest.test("testScripts/globalscope.php")
  }
  

//  test("SchoolMate") {
//    RegressionTest.test("schoolmate/index.php")
//  }
  test("Callbacks 1") {
    RegressionTest.test("testScripts/callbacks.php")
  }
//  test("Calllbacks 2") {
//    RegressionTest.test("callbackAPI/wp_callback_test.php")
//  }
  test("builtin") {
    RegressionTest.test("testScripts/substr.php")
  }
  test("Inner 2 Outer") {
    RegressionTest.test("testScripts/inner_to_outer_scope/join01.php")
  }
  
  // global function scoping
  test("GFS 1") {
    RegressionTest.test("testScripts/scoping/global_function_scope_nested.php")
  }
  test("GFS 2") {
    RegressionTest.test("testScripts/scoping/global_function_scope_branching02.php")
  }
  test("GFS 3") {
    RegressionTest.test("testScripts/scoping/global_function_scope_branching01.php")
  }
  test("GFS 4") {
    RegressionTest.test("testScripts/scoping/global_function_scope02.php")
  }
  test("GFS 5") {
    RegressionTest.test("testScripts/scoping/global_function_scope01.php")
  }


}