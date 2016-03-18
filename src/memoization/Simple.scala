package memoization

/**
  * One parameter memoization
  */
object Simple extends App {

  import scala.collection.mutable

  case class Memo[X, R](f: X => R) extends (X => R) {

    import scala.collection.mutable

    private val cache = mutable.Map.empty[X, R]

    override def apply(x: X): R = cache.getOrElseUpdate(x, f(x))
  }

  val freq = mutable.Map.empty[Int, Int]

  lazy val square: Memo[Int, Int] = Memo {
    case x =>
      freq.put(x, freq.getOrElse(x, 0) + 1)
      x * x
  }

  import scala.math.random

  val input = (1 to 100).map(i => (10 * random).toInt).toList

  println(input.groupBy(x => x).mapValues(_.size).toList.sortBy(_._1))

  println(input.map(square(_)))

  println(freq.toList.sortBy(_._1))

}

