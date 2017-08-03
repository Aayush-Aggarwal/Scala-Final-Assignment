object MySQL extends Database {

  override val url = "jdbc:mysql://localhost:3306/testcase?allowMultiQueries=true"
  override val driver = "com.mysql.cj.jdbc.Driver"
  override val username = "root"
  override val password = "askitayush"

}