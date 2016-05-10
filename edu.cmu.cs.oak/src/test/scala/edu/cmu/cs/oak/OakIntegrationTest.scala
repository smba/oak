package edu.cmu.cs.oak

import org.scalacheck.Platform
import org.scalacheck.Prop.forAll
import org.scalacheck.Properties

import edu.cmu.cs.oak.value.DoubleValue
import edu.cmu.cs.oak.value.IntValue
import edu.cmu.cs.oak.value.NumericValue

object OakIntegrationTest extends Properties("DoubleValue") {

  property("addition with DoubleValue") = forAll { (a: Double, b: Double) 
    => (DoubleValue(a) + DoubleValue(b)).equals(DoubleValue(a + b))
  }
  
  property("addition with IntValue") = forAll { (a: Double, b: Int) 
    => (DoubleValue(a) + IntValue(b)).equals(DoubleValue(a + b))
  }
  
  property("subtraction with DoubleValue") = forAll { (a: Double, b: Double) 
    => (DoubleValue(a) - DoubleValue(b)).equals(DoubleValue(a - b))
  }
  
  property("subtraction with IntValue") = forAll { (a: Double, b: Int) 
    => (DoubleValue(a) - IntValue(b)).equals(DoubleValue(a - b))
  }

  property("multiplication with DoubleValue") = forAll { (a: Double, b: Double) 
    => (DoubleValue(a) * DoubleValue(b)).equals(DoubleValue(a * b))
  }
  
  property("multiplication with IntValue") = forAll { (a: Double, b: Int) 
    => (DoubleValue(a) * IntValue(b)).equals(DoubleValue(a * b))
  }
  
  property("division with DoubleValue") = forAll { (a: Double, b: Double) 
    => (DoubleValue(a) / DoubleValue(b)).equals(DoubleValue(a / b))
  }
  
  property("division with IntValue") = forAll { (a: Double, b: Int) 
    => (DoubleValue(a) / IntValue(b)).equals(DoubleValue(a / b))
  }
  
  property("modulo with DoubleValue") = forAll { (a: Double, b: Double) 
    => (DoubleValue(a) % DoubleValue(b)).equals(DoubleValue(a % b))
  }
  
  property("modulo with IntValue") = forAll { (a: Double, b: Int) 
    => (DoubleValue(a) % IntValue(b)).equals(DoubleValue(a % b))
  }

}
