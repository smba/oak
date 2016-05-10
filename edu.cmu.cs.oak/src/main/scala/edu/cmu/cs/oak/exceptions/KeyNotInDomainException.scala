package edu.cmu.cs.oak.exceptions

import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.ArrayValue

class KeyNotInDomainException(name: String, av: ArrayValue, key: OakValue) extends OakException {
  
}