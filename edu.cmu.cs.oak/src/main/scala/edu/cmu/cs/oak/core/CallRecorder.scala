package edu.cmu.cs.oak.core

import java.io.PrintWriter
import scala.collection.mutable.AnyRefMap
import scala.collection.mutable.ListBuffer
import edu.cmu.cs.oak.value.SymbolicValue
import java.nio.file.Path
import edu.cmu.cs.oak.env.Call
import java.nio.file.Paths

trait CallRecorder {
  
  /** Record of undefined functions called */
  val undefinedCalls = AnyRefMap[String, ListBuffer[Call]]()

  /** Record of defined function calls returning symbolic values */
  val definedButSymbolicCalls = AnyRefMap[String, ListBuffer[(Call, SymbolicValue)]]()
  
  final val UNDEFINED_CALLS = Paths.get(getClass.getResource("/records/undefined_calls.xml").getPath)
  final val SYMBOLIC_CALLS = Paths.get(getClass.getResource("/records/symbolic_calls.xml").getPath)

  def recordUndefinedCall(call: Call) {
    if (undefinedCalls.get(call.name).isEmpty) {
      undefinedCalls.put(call.name, ListBuffer[Call](call))
    } else {
      undefinedCalls.get(call.name).get.append(call)
    }
  }

  def recordDefinedSymbolicCall(call: Call, s: SymbolicValue) {
    if (definedButSymbolicCalls.get(call.name).isEmpty) {
      definedButSymbolicCalls.put(call.name, ListBuffer[(Call, SymbolicValue)]((call, s)))
    } else {
      definedButSymbolicCalls.get(call.name).get.append((call, s))
    }
  }

  def serializeUndefinedCalls() {
    val pw = new PrintWriter(UNDEFINED_CALLS.toFile)
    val xml = {
      <Routines>
        {
          for (name <- undefinedCalls.keys.toSeq.sortWith(_ > _)) yield {
            <Routine Name={ name } Calls = {undefinedCalls.get(name).get.size.toString}>
              {
                for (call <- undefinedCalls.get(name).get) yield {
                  <Call File={ call.location._1.toString } Line={ call.location._2.toString }>
                    {
                      for (arg <- call.args) yield {
                        <Arg Value={ if (arg != null) arg.toString else "null" }/>
                      }
                    }
                  </Call>
                }
              }
            </Routine>
          }
        }
      </Routines>
    }
    pw.write((new scala.xml.PrettyPrinter(80, 2)).format(xml))
    pw.close
    
    println(undefinedCalls.size + " function(s) were undefined.")
  }

  def serializeSymbolicCalls() {
    val pw = new PrintWriter(SYMBOLIC_CALLS.toFile)
    val xml = {
      <Routines>
        {
          for (name <- definedButSymbolicCalls.keys.toSeq.sortWith(_ > _)) yield {
            <Routine Name={ name }>
              {
                for (call_tuple <- definedButSymbolicCalls.get(name).get) yield {
                  <Call File={ call_tuple._1.location._1.toString } Line={ call_tuple._1.location._2.toString } Value={ call_tuple._2.toString }>
                    {
                      for (arg <- call_tuple._1.args) yield {
                        <Arg Value={ if (arg != null) arg.toString else "null" }/>
                      }
                    }
                  </Call>
                }
              }
            </Routine>
          }
        }
      </Routines>
    }
    pw.write((new scala.xml.PrettyPrinter(80, 2)).format(xml))
    pw.close
    
  }
}