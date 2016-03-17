package object unapply {

  case class Person(name: String, surname: String)

  object Person {

    def unapply(input: String) = input.split(" ").toList match {
      case name :: surname :: Nil => Some(Person(name, surname))
      case _ => None
    }

  }

}

object UnapplyMain extends App {

  val data = List("John Smith","aaa bbb","","not a person")

  import unapply._

  data.collect {
    case Person(person) => person
  } foreach println

}
