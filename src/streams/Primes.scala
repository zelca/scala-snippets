package streams

/**
  * Use streams to calculate prime numbers
  */
object Primes extends App {

  def sieve(s: Stream[Int]): Stream[Int] = s.head #:: sieve(s.tail.filter(_ % s.head != 0))

  // all primes as a lazy sequence (slow)
  val primes_ = sieve(Stream.from(2))

  // all primes as a lazy sequence (fast)
  val primes: Stream[Int] = 2 #:: Stream.from(3, 2).filter(i => primes.takeWhile(j => j * j <= i).forall(k => i % k > 0))

  val n = 1000

  val time1 = System.currentTimeMillis
  println(primes_.take(n).toList)
  println(System.currentTimeMillis - time1)

  val time2 = System.currentTimeMillis
  println(primes.take(n).toList)
  println(System.currentTimeMillis - time2)

}