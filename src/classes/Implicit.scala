package classes

/**
  * Extension for built-in classes
  */
object Implicit extends App {

  implicit class ExtendedString(string: String) {

    def -(that: String) = string.replaceAll("[" + that + "]", "")

  }

  println("qhieasasaaslaslsos vwosaqqrpld" - "qvipas")

}
