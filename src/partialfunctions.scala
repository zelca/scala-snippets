package object partialfunctions {

  def fizz = new PartialFunction[Int, Int] {
    def apply(i: Int) = {
      print("Fizz")
      i
    }

    def isDefinedAt(i: Int) = i % 3 == 0
  }

  def buzz: PartialFunction[Int, Int] = {
    case i if i % 5 == 0 =>
      print("Buzz")
      i
  }

  def other: PartialFunction[Int, Int] = {
    case i =>
      print(i.toString)
      i
  }

  def space: PartialFunction[Any, Any] = {
    case _ => print(" ")
  }

}

object partialFunctionsMain extends App {

  import scala.math.random

  val data = 15 :: (1 to 100).map(i => (20 * random).toInt).toList

  println(data)

  import partialfunctions._

  val fizzBuzz = fizz andThen buzz  orElse other andThen space

  data.foreach(fizzBuzz)

}
