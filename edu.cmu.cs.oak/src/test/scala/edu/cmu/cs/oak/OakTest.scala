package edu.cmu.cs.oak

import org.scalatest.Suites
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class OakTest extends Suites(
  OakUnitTest //new OakIntegrationTest
)