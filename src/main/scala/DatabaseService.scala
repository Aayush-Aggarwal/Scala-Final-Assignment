import java.sql.Connection


trait DatabaseService extends Database {

  val connection: Connection
  val fileName: String

  def queryTime(filePath: String): Unit = {

    val fileContent = ReadWriteFromFile.read(filePath)
    val fileContentList = fileContent.split("\n").toList
    val testCaseList = fileContentList.map(testCase => CSVTestCaseParser.reader2(testCase))
    val timeTakenList = testCaseList.map {
      testCase => executeQueries(testCase, connection)
    }
    val testCasesZipTimeTaken = testCaseList zip timeTakenList

  }
}