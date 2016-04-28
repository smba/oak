package edu.cmu.cs.oak

import com.caucho.quercus.script.QuercusScriptEngineFactory

object Interpreter extends App {
  val factory = new QuercusScriptEngineFactory();
  val engine = factory.getScriptEngine();
  
  val code = "<?php $foo = strlen('abc'); print $foo; return 'yikes'; ?>";
  val o = engine.eval(code);
  System.out.println(o);
}