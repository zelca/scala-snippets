package functions

/**
  * The ugliest way to solve FizzBuzz problem
  */
object FizzBuzz extends App {

  def fizz = new PartialFunction[Int, Int] {
    def apply(i: Int) = {
      print("Fizz")
      i
    }

    def isDefinedAt(i: Int) = i % 3 == 0
  }

  def buzz = new PartialFunction[Int, Int] {
    def apply(i: Int) = {
      if (isDefinedAt(i)) print("Buzz")
      i
    }

    def isDefinedAt(i: Int) = i % 5 == 0
  }

  def rest: PartialFunction[Int, Int] = {
    case i: Int =>
      print(i.toString)
      i
  }

  val fizzBuzz = (fizz andThen buzz) orElse fizz orElse buzz orElse rest

  import scala.math.random

  val data = 3 :: 5 :: 15 :: (1 to 10).map(i => (20 * random).toInt).toList

  data.foreach(fizzBuzz andThen (i => println(" <- " + i)))

}
