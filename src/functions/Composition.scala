package functions

/**
  * Proper andThen for partial pure functions
  */
object Composition extends App {

  def int2Char: PartialFunction[Int, Char] = {
    case 1 => 'a'
    case 2 => 'b'
  }

  def char2String = new PartialFunction[Char, String] {
    def apply(i: Char) = "passed"

    def isDefinedAt(c: Char) = c == 'b'
  }

  println("= andThen =")

  val f1 = int2Char andThen char2String
  List(1, 2, 3).collect(f1).foreach(println)

  implicit class ComposePartial[A, B](pf: PartialFunction[A, B]) {
    def andThenPartial[C](that: PartialFunction[B, C]): PartialFunction[A, C] =
      Function.unlift(pf.lift(_) flatMap that.lift)
  }

  println("= andThenPartial =")

  val f2 = int2Char andThenPartial char2String
  List(1, 2, 3).collect(f2).foreach(println)

}
