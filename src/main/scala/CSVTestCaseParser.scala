import java.io.File

import scala.io.Source

import com.opencsv.{CSVParser}

object CSVTestCaseParser {

  val file = new File("/home/knoldus/IdeaProjects/MySqlDDL.csv")
  val content = Source.fromFile(file).getLines().mkString("\n")
  val content1= content.split("\n").toList


  val reader2 = new CSVParser()
  val parse= reader2.parseLine(content1(0))

}
