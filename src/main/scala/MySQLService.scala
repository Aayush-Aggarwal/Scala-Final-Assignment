import java.sql.Connection

object MySQLService extends DatabaseService {

  val connection: Connection = MySQL.connectToDatabase
  val fileName: String = "MySQL"

}