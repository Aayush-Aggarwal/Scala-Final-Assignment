
import java.sql.{Connection, DriverManager}
import com.opencsv.CSVParser
import org.apache.log4j.Logger


trait Database {

  val url: String = ""
  val driver: String = ""
  val username: String = ""
  val password: String = ""
  val fileName:String=""
  val connection=Connection
  def connectToDatabase: Connection = {

    val connection: Connection = try {
      Class.forName(driver)
      DriverManager.getConnection(url, username, password)
    } catch {
      case e: Exception => throw new Exception("Connection was not Successful")
    }

    connection
  }


  def query(fileName1: String): Boolean = {
    val content = ReadWriteFromFile.read(fileName1)
    val content1 = content.split("\n").toList



    val reader2 = new CSVParser()
    val parse = content1.map(testcase => reader2.parseLine(testcase).toList)

    val executionTimeList = parse.map {
      testCase => queryTime(testCase, connection)
    }
    val zipper = parse.zip(executionTimeList)

    val testcaseWithTime = zipper.map(test => test._1 ::: List(test._2))
    val output = ReadWriteFromFile.write(fileName, testcaseWithTime, "/home/knoldus/IdeaProjects/DB-Performance")
    MySQL.closeConnection(connection)
    output
  }

  def queryTime(testCase : List[String],connection: Connection ):String= {

    val statement = connection.createStatement
    statement.execute(testCase(2))
    val t4 = System.nanoTime()
    statement.execute(testCase(3))
    val t5 = System.nanoTime()
    statement.execute(testCase(4))
    (t5 - t4).toString
  }
    def closeConnection(connection: Connection): Unit = {
    connection.close
  }

}