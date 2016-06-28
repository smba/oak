package edu.cmu.cs.oak

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RegressionTests extends FunSuite {

  test("Base unit tests") {
    RegressionTest.test("environments/env01.php")
    RegressionTest.test("environments/switch.php")
    RegressionTest.test("environments/while.php")
    RegressionTest.test("testScripts/classes01.php")
  }
  
  test("Schoolmate") {
    RegressionTest.test("schoolmate/index.php")
  }
  
  test("Addressbook") {
    RegressionTest.test("addressbook/z-push/index.php")
  }
  
}