package object streams {

  import scala.math.BigInt

  lazy val fibs: Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).map(n => n._1 + n._2)

  def sieve(s: Stream[Int]): Stream[Int] = {
    s.head #:: sieve(s.tail.filter(_ % s.head != 0))
  }

  // all primes as a lazy sequence (slow)
  val primes_ = sieve(Stream.from(2))

  // all primes as a lazy sequence (fast)
  val primes: Stream[Int] = 2 #:: Stream.from(3, 2).filter(i => primes.takeWhile(j => j * j <= i).forall(k => i % k > 0))

}

object StreamsMain extends App {

  import streams._

  val n = 1000

  val time1 = System.currentTimeMillis
  println(primes_.take(n).toList)
  println(System.currentTimeMillis - time1)

  val time2 = System.currentTimeMillis
  println(primes.take(n).toList)
  println(System.currentTimeMillis - time2)

}