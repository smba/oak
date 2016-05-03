package edu.cmu.cs.oak.env


abstract class NumericValue extends OakValue {
  def +(v:NumericValue): NumericValue
  def -(v:NumericValue): NumericValue
  def *(v:NumericValue): NumericValue
  def /(v:NumericValue): NumericValue
  def %(v:NumericValue): NumericValue

  def <(v: NumericValue): BooleanValue

  def >(v: NumericValue): BooleanValue
}