
trait ReadWrite {

  def read(fileLocation: String): String

  def write(fileName: String, content: List[List[String]], dirPath: String): Boolean

}