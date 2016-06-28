package edu.cmu.cs.oak

import org.junit.runner.RunWith
import org.scalatest.Suites
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class OakTest extends Suites(
  new RegressionTests,
  new OakUnitTest
)