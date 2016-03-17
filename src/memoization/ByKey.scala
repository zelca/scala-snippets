package memoization

/**
  * Memoization by key
  */
object ByKey extends App {

  case class Memo1[X, K, R](f: X => R)(implicit ev: X => K) extends (X => R) {

    import scala.collection.mutable

    private val cache = mutable.Map.empty[K, R]

    override def apply(x: X): R = cache.getOrElseUpdate(x, f(x))
  }

}
