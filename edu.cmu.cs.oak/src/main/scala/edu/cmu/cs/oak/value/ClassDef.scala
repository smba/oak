package edu.cmu.cs.oak.value

case class ClassDef(name: String, fields: List[String], methods: Map[String, FunctionDef]) {
  
  /**
   * Constructors
   */
  var constructors = Map[Int, FunctionDef]()
  
  def addConstructor(i: Int, fd: FunctionDef): Unit = {
    constructors += (i -> fd)
  }
  
  def getConstructor(i: Int): FunctionDef = {
    try {
      constructors.get(i).get
    } catch {
      case nsee: NoSuchElementException => throw new RuntimeException("Constructor for " + name + " with " + i + " arguments not found (" + constructors.size + " constructor(s) available).")
    }
  }
  
  
  def getName(): String = name
  def getMethods(fname: String): FunctionDef = methods.get(fname).get
  def getFields(): List[String] = fields
  
  
  
}