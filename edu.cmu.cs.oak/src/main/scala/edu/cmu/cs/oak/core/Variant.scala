package edu.cmu.cs.oak.core

import edu.cmu.cs.oak.value.OakValue
import scala.collection.mutable.ListBuffer
import edu.cmu.cs.oak.value.OakValueSequence
import edu.cmu.cs.oak.value.MapChoice


class Variant(entries: Seq[OakValue]) {
  
  def +(that: OakValue): Variant = {
    new Variant(getEntries ++ Seq(that))
  }
  
  // x * y = xy
  def +(that: Variant): Variant = {
    new Variant(this.entries ++ that.getEntries)
  }
  
  // x * (y, z) = (xy, xz)
  def *(that: Variants): Variants = {
    new Variants(that.getEntries().map(variant => this + variant))
  }
  
  def getEntries() = entries
  
}

class Variants(entries: Seq[Variant]) {
  
  def +(that: OakValue): Variants = {
    new Variants(getEntries.map(e => e + that))
  }
  
  // (x, y) + z = (xz, yz)
  def *(that: Variant): Variants = {
    new Variants(this.getEntries().map(variant => variant + that))
  }

  // (a, b) * (c, d) = (ac, ad, bc, bd)
  def *(that: Variants): Variants = {
    new Variants(getEntries().map(y => y * that).map(z => z.getEntries()).fold(Seq[Variant]())(_ ++ _))
  }
  
  def getEntries() = entries
}
