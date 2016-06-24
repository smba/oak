package edu.cmu.cs.oak.value

case class DoubleValue(value:Double) extends NumericValue {
  override def +(v:NumericValue): DoubleValue = {
    return v match {
      case i: IntValue => DoubleValue(value + i.getValue)
      case d: DoubleValue => DoubleValue(value + d.getValue)
    }
  }
  override def -(v:NumericValue): DoubleValue = {
    return v match {
      case i: IntValue => DoubleValue(value - i.getValue)
      case d: DoubleValue => DoubleValue(value - d.getValue)
    }
  }
  override def *(v:NumericValue): DoubleValue = {
    return v match {
      case i: IntValue => DoubleValue(value * i.getValue)
      case d: DoubleValue => DoubleValue(value * d.getValue)
    }
  }
  override def /(v:NumericValue): DoubleValue = {
    return v match {
      case i: IntValue => DoubleValue(value / i.getValue)
      case d: DoubleValue => DoubleValue(value / d.getValue)
    }
  }
  override def %(v:NumericValue): DoubleValue = {
    return v match {
      case i: IntValue => DoubleValue(value % i.getValue)
      case d: DoubleValue => DoubleValue(value % d.getValue)
    }
  }

  override def <(v: NumericValue): BooleanValue = {
    return v match {
      case i: IntValue => BooleanValue(value < i.getValue)
      case d: DoubleValue => BooleanValue(value < d.getValue)
    }
  }

  override def >(v: NumericValue): BooleanValue = {
    return v match {
      case i: IntValue => BooleanValue(value > i.getValue)
      case d: DoubleValue => BooleanValue(value > d.getValue)
    }
  }
  def getValue: Double = value
}