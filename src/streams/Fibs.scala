package streams

/**
  * Use streams to calculate fibonacci numbers
  */
object Fibs extends App {

  import scala.math.BigInt

  lazy val fibs: Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).map(n => n._1 + n._2)

  println(fibs.take(10).toList)

}
