package edu.cmu.cs.oak.env

import edu.cmu.cs.oak.value.{ FunctionDef, OakValue, OakVariable }

import scala.collection.immutable.Stack
import scala.collection.mutable.ListBuffer
import edu.cmu.cs.oak.value.ClassDef
import edu.cmu.cs.oak.value.ObjectValue
import edu.cmu.cs.oak.nodes.DNode
import edu.cmu.cs.oak.env.heap.OakHeap
import com.caucho.quercus.expr.Expr
import scala.collection.mutable.HashSet
import edu.cmu.cs.oak.value.Choice
import edu.cmu.cs.oak.value.OakValueSequence
import edu.cmu.cs.oak.value.NullValue

trait Environment extends EnvListener {

  /**
   * Updates a variable in the environment, i.e. it stores a variable
   * assignment, such as '$i = 8'.
   *
   * @paran name Name of the variable
   * @param value OakValue to assign to the variable
   */
  def update(name: String, value: OakValue)

  /**
   * Looks up an variable and returns its context-dependant value.
   *
   * @param name Name of the variable
   *
   * @return value Value of the variable
   */
  def lookup(name: String): OakValue

  /**
   * Returns a DNode tree of the environment's output.
   * @return root node of the DNode tree
   */
  def getOutputModel(): DNode

  /**
   * Add output to environment.
   * @param value Value to add to output
   */
  def addOutput(value: OakValue)

  /**
   * Get the output sequence from the environment.
   * @return output as sequence of values
   */
  @deprecated def getOutput(): List[OakValue]

  /**
   * Manipulation of references via the environment
   *  @param name of the variabke
   *  @param reference to point to
   */
  def setRef(name: String, ref: OakVariable)

  /**
   * Get the current reference of a variable at runtime.
   * @param name Name of the variable
   * @return reference that variable 'name points to
   */
  def getRef(name: String): OakVariable

  /**
   * Adds a function definition to the environment.
   * @param value Function definition to add
   */
  def addFunction(value: FunctionDef)

  /**
   * Looks up a function definition in the environment.
   * @param name Name of the function
   * @return corresponding function definition
   */
  def getFunction(name: String): FunctionDef

  /**
   * Adds a class definition to the environment.
   * @param value ClassDef to add
   */
  //def addClass(value: ClassDef)

  /**
   * Looks up a class definition in the environment.
   * @param name Name of the class
   * @return corresponding class definition
   */
  //def getClass(name: String): ClassDef

  /**
   * Returns the current call stack at runtime.
   * @return Stack of strings where each string denotes a function call
   */
  def getCalls(): Stack[String]

  /**
   * Returns the environment's parent environment (null if top-level env)
   * @param parent environment
   */
  def getParent(): EnvListener

  /**
   * Receive and store output from a child environment
   * @param value Sequence of output values to add
   */
  def receiveOutput(value: Seq[OakValue])

  /**
   * Returns the environments path condition
   * @return path condition
   */
  def getConstraint(): String

  /**
   * Prepends the passed output to the environments output.
   * @param pre List of values to add
   */
  def prependOutput(pre: List[OakValue])

  /**
   * Returns the environments map of variable names to references
   * @return map of variable names to references
   */
  def getVariables(): Map[String, OakVariable]

  /**
   * Serializes the entire state, i.e., serializes variable names
   * and values to XML.
   * TODO implement parser for serialized environment
   *
   * @return XML representation of the environment
   */
  def toXml: scala.xml.Elem

  def unset(name: String)
  
  def unsetArrayElement(name: String, index: OakValue)
  
  def join(that: Environment): Environment

}

/**
 * Includes static methods used by any environment.
 */
object Environment {

  /**
   * Creates a new function environment that is used to
   * execute a function call. The function call is documented
   * via the environments call stack.
   *
   * @param dis Parent environment to bounce output to
   * @param f Name of the function
   *
   * @return FunctionEnv
   */
  def createFunctionEnvironment(dis: Environment, f: String): FunctionEnv = {
    return new FunctionEnv(dis, dis.getCalls push f, dis.getConstraint)
  }

  /**
   * Creates a new object environment that is used to
   * execute a function call.
   *
   * @param dis Parent environment to bounce output to
   * @param f Name of the function
   *
   * @return ObjectEnv
   */
  def createObjectEnvironment(dis: Environment, obj: ObjectValue): ObjectEnv = {
    return ObjectEnv(dis, dis.getCalls(), dis.getConstraint, obj)
  }

  /**
   * Splits an environment into two branch environments that
   * can be joined afterwards.
   *
   * @param newConstraint Path constrained to add to the branches
   *
   * @param Tuple of two branch environments
   */
  def fork(parent: Environment, newConstraint: String): (BranchEnv, BranchEnv) = {
    val b1 = new BranchEnv(parent, parent.getCalls(), parent.getConstraint() + " && " + newConstraint)
    val b2 = new BranchEnv(parent, parent.getCalls(), parent.getConstraint() + " && NOT(" + newConstraint + ")")

    /* Add variables of parent environment to the branch environments. */
    parent.getVariables.keySet.foreach {
      k =>
        {
          b1.update(k, parent.lookup(k))
          b2.update(k, parent.lookup(k))
        }
    }
    return (b1, b2)
  }
  

  /**
   *
   */
  def splitN(parent: Environment, expr: Expr, cases: List[Expr]): List[BranchEnv] = {

    var envs = List[BranchEnv]()
    cases.foreach {
      c => envs ++= List(new BranchEnv(parent, parent.getCalls, parent.getConstraint + " && (" + expr + " == " + c + ")"))
    }
    envs.toList.foreach {
      e =>
        parent.getVariables.keySet.foreach {
          k =>
            {
              e.update(k, parent.lookup(k))
            }
        }
    }
    return envs
  }

  def joinN(parent: Environment, envs: List[BranchEnv], default: BranchEnv): Environment = {

    def choice(varmap: Map[String, OakValue], defaultValue: OakValue): Choice = {
      val keyIterator = varmap.keySet.toIterator
      var currentkey = keyIterator.next
      var choice = Choice(currentkey.toString, varmap.get(currentkey).get, defaultValue)
      while (keyIterator.hasNext) {
        currentkey = keyIterator.next
        choice = Choice(currentkey.toString, varmap.get(currentkey).get, choice)
      }
      choice
    }

    /* Identify all variable names */
    val variableNames = {
      var buffer = new HashSet[String]
      envs.foreach {
        case env => buffer = buffer union env.getVariables.keySet
      }
      buffer.toSet
    }

    /* find out, which variables changed by merging all update sets */
    val changed = envs.map {
      env => env.getUpdates
    }.foldLeft(Set[String]())(_ union _)

    /* Generate a choice for any changed variable */
    var choices = Map[String, Choice]()
    changed.foreach {
      c =>
        {
          var cMap = Map[String, OakValue]() // Constraint -> Value
          envs.foreach {
            env =>
              {
                val value = try {
                  env.lookup(c)
                } catch {
                  case _ => NullValue("Environment::joinN")
                }
                cMap += (env.getConstraint -> value)
              }
          }
          val defaultValue = if (default != null) {
            try {
              default.lookup(c)
            } catch {
              case _ => NullValue("Environment::joinN")
            }
          } else {
            NullValue("Environment::joinN")
          }
          choices += (c -> choice(cMap, defaultValue))
        }
    }

    /* Merge output */
    val outputChoice = {
      var outputMap = Map[String, OakValueSequence]()
      envs.foreach {
        env => outputMap += (env.getConstraint -> OakValueSequence(env.getOutput))
      }
      val defaultOutput = if (default != null) {
        try {
          OakValueSequence(default.getOutput)
        } catch {
          case _ => NullValue("Environment::joinN")
        }
      } else {
        NullValue("Environment::joinN")
      }
      choice(outputMap, defaultOutput)
    }
    
    parent.addOutput(outputChoice)
    choices.foreach {
      case (name, choice) => {
        parent.update(name, choice)
      }
    }
    
    parent
  }
}