import java.io.{File, PrintWriter}

import scala.io.Source
import scala.io.Source.fromFile


object ReadWriteFromFile extends ReadWrite {

  def read(fileLocation: String): String = {

    val file = new File(fileLocation)

    try {
      val content = Source.fromFile(file).getLines().mkString("\n")
      content
    }
    catch {
      case error: Exception => throw new Exception(s"$error : This Error is coming while reading the file")
    }

  }

  def write(fileName: String, content: List[List[String]], dirPath: String): Boolean = {
    val writeData = content.map(testCase=>testCase.reduce((ele1,ele2)=>ele1+","+ele2)).reduce(_ + "\n" + _)
    //println(writeData)

    new File(dirPath).mkdir()
    val writeToFile = new PrintWriter(dirPath + "/" + fileName + ".csv")
    try {
      writeToFile.write(writeData)
      writeToFile.close()
      true
    }
    catch {
      case e: Exception => false
    }
  }

}