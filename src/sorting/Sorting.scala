package sorting

/**
  * Insert sort + implicit class
  */
object Sorting extends App {

  def insert(item: Int, input: List[Int]): List[Int] = input match {
    case Nil => item :: Nil
    case x :: xs if item < x => item :: x :: xs
    case x :: xs => x :: insert(item, xs)
  }

  def sorted(x: List[Int]) = x.foldLeft(List[Int]())((b, a) => insert(a, b))

  implicit class SortableList(val list: List[Int]) {
    def sort = sorted(list)
  }

  import scala.math.random

  val input = (1 to (100 * random.toFloat).round).toList.sortWith((x, y) => random < 0.5d)

  println(input)
  println(sorted(input))
  println(input.sort)
  println(input.sorted)

}