package object memoization {

  import scala.collection.mutable

  case class Memo0[X, R](f: X => R) extends (X => R) {

    private val cache = mutable.Map.empty[X, R]

    override def apply(x: X): R = cache.getOrElseUpdate(x, f(x))
  }

  case class Memo1[X, K, R](f: X => R)(implicit ev: X => K) extends (X => R) {

    private val cache = mutable.Map.empty[K, R]

    override def apply(x: X): R = cache.getOrElseUpdate(x, f(x))
  }

  case class Memo2[X, Y, K, R](f: (X, Y) => R)(implicit ev: ((X, Y)) => K) extends ((X, Y) => R) {

    private val cache = mutable.Map.empty[K, R]

    override def apply(x: X, y: Y): R = cache.getOrElseUpdate((x, y), f(x, y))
  }

}

object MemoizationMain extends App {

  import scala.math.random

  val input = (1 to 100).map(i => (10 * random).toInt).toList

  println(input)

  println(input.groupBy(x => x).mapValues(_.size).toList.sortBy(_._1))

  import scala.collection.mutable

  val freq1 = mutable.Map.empty[Int, Int]

  def square1(input: List[Int]): List[Int] = input match {
    case Nil => Nil
    case x :: xs =>
      freq1.put(x, freq1.getOrElse(x, 0) + 1)
      x * x :: square1(xs)
  }

  println(square1(input))

  println(freq1.toList.sortBy(_._1))

  println("== memoization ==")

  val freq2 = mutable.Map.empty[Int, Int]

  import memoization._

  def square2(input: List[Int]): List[Int] = {
    type SquareMemo = Memo0[Int, Int]

    lazy val square : SquareMemo = Memo0 {
      case x =>
        freq2.put(x, freq2.getOrElse(x, 0) + 1)
        x * x
    }

    input.map(square(_))
  }

  println(square2(input))

  println(freq2.toList.sortBy(_._1))

}
