package edu.cmu.cs.oak.core

import java.io.FileNotFoundException
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path

import scala.collection.mutable.Stack
import java.nio.file.Paths

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
      throw new RuntimeException("Path to entry point already defined.")
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
  def resolvePath(fragment: String): Path = {
    if (!includes.isEmpty) {
      val path = includes.top.getParent.resolve(fragment).normalize()
      if (!Files.exists(path)) {
        
        base.get.getParent.getParent.resolve(fragment).normalize()
      } else {
        path
      }
    } else {
      base.get.getParent.resolve(fragment).normalize()
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
}
