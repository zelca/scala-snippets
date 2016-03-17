package matching

/**
  * Example of unapply
  */
object Unapply extends App {

  case class Person(name: String, surname: String)

  object Person {

    def unapply(input: String) = input.split(" ").toList match {
      case name :: surname :: Nil => Some(Person(name, surname))
      case _ => None
    }

  }

  List("John Smith", "aaa bbb", "", "not a person").collect {
    case Person(person) => person
  } foreach println

}
