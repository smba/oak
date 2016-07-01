package edu.cmu.cs.oak

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

//@RunWith(classOf[JUnitRunner])
class RegressionTests extends FunSuite {

  test("1") {
    RegressionTest.test("environments/env01.php")
  }
  test("2") {
    RegressionTest.test("environments/switch.php")
  }
  
  test("3") {
    RegressionTest.test("environments/while.php")
  }
  
  test("4") {
    RegressionTest.test("testScripts/classes01.php")
  }
  
  test("objects") {
    RegressionTest.test("testScripts/objects01.php")
  }
  
  test("arrays") {
    RegressionTest.test("testScripts/arrayValues01.php")
  }
  
  test("References") {
    RegressionTest.test("testScripts/functions01.php")
    RegressionTest.test("testScripts/references01.php")
    RegressionTest.test("testScripts/references02.php")
  }
  
  test("Outer to inner scope") {
    //RegressionTest.test("testScripts/outer_to_inner_scope/createLoopEnvironment.php")
    RegressionTest.test("testScripts/outer_to_inner_scope/createFunctionOrMethodEnvironment.php")
  }

  test("Schoolmate") {
    RegressionTest.test("schoolmate/index.php")
  }
	
}