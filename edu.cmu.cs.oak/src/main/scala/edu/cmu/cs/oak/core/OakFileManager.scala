package edu.cmu.cs.oak.core

import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path

import scala.collection.mutable.Stack

import edu.cmu.cs.oak.value.MapChoice
import edu.cmu.cs.oak.value.OakValue
import edu.cmu.cs.oak.value.OakValueSequence

trait OakFileManager {

  /**
   *  Path to the entry point
   */
  protected var base: Option[Path] = None

  /**
   * (Mutable) include stack (similar to a call stack) to keep track
   * of recent includes in order to avoid recursion and manage
   * "require_once" statements.
   */
  protected val includes = Stack[Path]()

  /**
   * Set the path to the entry point/initially executed script file, such
   * as .../index.php. Method throws an exception in case one attempts to
   * overwrite the entry point.
   *
   * @param base_path path to the initially executed script
   *
   * @throws RuntimeException
   */
  def setBasePath(base_path: Path) {
    if (base.isEmpty) {
      base = Some(FileSystems.getDefault.getPath(base_path.toString()))
    } else {
      //throw new RuntimeException("Path to entry point already defined.")
    }
  }

  /**
   * Resolves a parsed/evaluated path fragment, for instance "../wp-includes/script.php".
   * Since any require[_once] statement in PHP refers to the current script, path fragments,
   * as long as they are relative paths, refer to the current scripts path.
   * An exception is thrown if the path cannot be resolved OR the path does not link to
   * any actual file.
   *
   * @param fragment Path fragment (can be relative path)
   * @return Resolved java.ni.file.Path instance
   *
   * @throws FileNotFoundException
   */
  def resolvePath(fragmento: String): Path = {

    val fragment = fragmento//.replaceAll("../", "").replaceAll("./", "")
    if (!includes.isEmpty) {

      /**
       * Resolve the path by accessing the current files parent until you reach
       *  the system root or succeed with resolution. thx to Stefan 2 for this idea
       */
      var p = includes.top.getParent
      while (p.getParent != null) {
        val p1 = p.resolve(fragment).normalize().toAbsolutePath()
        val p2 = p.resolve(fragment.slice(1, fragment.size)).normalize().toAbsolutePath()
        if (Files.exists(p1)) {
          return p1
        } else if (Files.exists(p2)) {
          return p2
        } else {
          p = p.getParent
        }

      }

      return null
    } else {
      val path = base.get.getParent.resolve(fragment).normalize()
      return if (Files.exists(path)) {
        path
      } else {
        null
      }
    }
  }

  /**
   * Adds a new path to the include paths stack.
   *
   * @param new_current Path to script to import next
   */
  def setCurrentPath(new_current: Path) {
    includes.push(new_current)
  }

  /**
   * Pops current path from the include paths stack.
   */
  def resumePreviousCurrent() {
    includes.pop()
  }

  /**
   * Returns the path to the currently executed script.
   *
   * @return Path to the currently executed script.
   */
  def getCurrentPath(): Path = {
    includes.top
  }

  /**
   * Returns the path to the system root.
   *
   * @return Path to the entry point
   */
  def getBasePath(): Path = {
    return this.base.get
  }

  /**
   * After evaluating the include expression, this is (usually..) an OakValueSequence
   * wit nested MapChoices and other fancy values. Since MapChoices and Sequences can be nested,
   * we flatten the evaluated value by resolving ALL possible variants.
   *
   * For instance:
   * (a | b (c | d) ) e (f | g)  where (x|y) is a choice and enything else from left to right is the/a sequence
   *
   * would be flattened to
   *
   * aef
   * bcef
   * bdef
   * aeg
   * bceg
   * bdeg
   *
   */
  def flattenPath(path: OakValue): Seq[String] = {
    val vs = createVariants(path).getEntries().map(v => v.getEntries() mkString "")
    return vs
  }

  /**
   *
   */
  private def createVariants(v: OakValue): Variants = v match {
    case s: OakValueSequence => {
      val x = s.getSequence.map(ov => createVariants(ov))
      x.tail.fold(x.head)(_ * _)
    }
    case c: MapChoice => {
      val x = c.toMap.keys.map(k => createVariants(k)).map(e => e.getEntries())
      new Variants(x.tail.fold(x.head)(_ ++ _))
    }
    case v: OakValue => {
      new Variants(List(new Variant(List(v))))
    }
  }
}
