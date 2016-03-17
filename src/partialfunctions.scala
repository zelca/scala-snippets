package object partialfunctions {

  def fizz = new PartialFunction[Int, Unit] {
    def apply(i: Int) = {
      print("Fizz")
    }

    def isDefinedAt(i: Int) = i % 3 == 0
  }

  def buzz: PartialFunction[Int, Unit] = {
    case i: Int if i % 5 == 0 =>
      print("Buzz")
  }

  def other: PartialFunction[Int, Unit] = {
    case i: Any =>
      print(i.toString)
  }

}

object partialFunctionsMain extends App {

  import scala.math.random

  val data = 3 :: 15 :: (1 to 100).map(i => (20 * random).toInt).toList

  println(data)

  import partialfunctions._

  println(buzz.isDefinedAt(6))

  val fizzBuzz = fizz orElse buzz orElse other

  data.foreach(fizzBuzz)

}
