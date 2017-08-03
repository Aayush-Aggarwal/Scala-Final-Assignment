
import java.sql.{Connection, DriverManager}

import org.apache.log4j.Logger


trait Database {

  val url: String = ""
  val driver: String = ""
  val username: String = ""
  val password: String = ""

  def connectToDatabase: Connection = {

    val connection: Connection = try {
      Class.forName(driver)
      DriverManager.getConnection(url, username, password)
    } catch {
      case e: Exception => throw new Exception("Connection was not successfull")
    }

    connection
  }

  def executeQueries(testCase: TestCase, connection: Connection): Long = {

    val mysqlStatement = connection.createStatement()
    mysqlStatement.execute(testCase.preCondition)
    val start = System.nanoTime()
    val logger = Logger.getLogger(this.getClass)
    logger.info(s"${testCase.testCaseID} ${testCase.query}")
    mysqlStatement.execute(testCase.query)
    val end = System.nanoTime()
    mysqlStatement.execute(testCase.postCondition)
    end - start

  }

  def closeConnection(connection: Connection): Unit = {
    connection.close
  }

}