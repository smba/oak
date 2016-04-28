package edu.iastate.symex.run

import edu.iastate.symex.datamodel.DataModel
import edu.iastate.symex.datamodel.ReadWriteDataModelToFromXml
import edu.iastate.symex.util.logging.MyLevel
import edu.iastate.symex.util.logging.MyLogger
import edu.iastate.symex.util.Timer
import edu.iastate.symex.core.PhpExecuter
import java.io.File

object Runner extends App {
  val PHP_FILE = "/home/stefan/Desktop/php/test1.php"
  val timer = new Timer();
  MyLogger.setLevel(MyLevel.PROGRESS);

  MyLogger.log(MyLevel.PROGRESS, "[RunSymexForFile:" + PHP_FILE + "] Started.");

  val dataModel = new PhpExecuter().execute(new File(PHP_FILE));

  MyLogger.log(MyLevel.PROGRESS, "[RunSymexForFile:" + PHP_FILE + "] Done in " + timer.getElapsedSecondsInText() + ".");

  MyLogger.log(MyLevel.INFO, dataModel.toIfdefString());
  
  println(dataModel.toIfdefString())
  
  (new ReadWriteDataModelToFromXml()).writeDataModelToXmlFile(dataModel, "/home/stefan/Desktop/php/out.xml");
  //MyLogger.writeLogMessagesToFile(new File("/Users/HUNG/Desktop/logs.txt"));
}