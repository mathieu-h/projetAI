package cp.examples

import JaCoP.scala._

object Transistors extends jacop {
  def main(args: Array[String]) {
    def ntran(b: BoolVar, x: BoolVar, y: BoolVar) = b -> (x #= y)
    def ptran(b: BoolVar, x: BoolVar, y: BoolVar) = (~b) -> (x #= y)

    val a = BoolVar("a")
    val b = BoolVar("b")
    val c = BoolVar("c")
    val sum = BoolVar("sum")
    val carry = BoolVar("carry")
    val nca = BoolVar("nca")
    val t = for (i <- List.range(0, 6)) yield BoolVar("t" + i)
    val q = for (i <- List.range(0, 6)) yield BoolVar("q" + i)
    val one = true //new BoolVar("1", 1, 1)
    val zero = false //new BoolVar("0", 0, 0)

    // sum part
    ptran(nca, t(0), one)
    ptran(c, one, t(4))
    ptran(b, t(0), t(4))
    ptran(a, t(0), t(1))
    ptran(nca, t(4), t(1))
    ptran(t(1), one, sum)
    ntran(a, t(1), t(2))
    ntran(nca, t(1), t(5))
    ntran(t(1), sum, zero)
    ntran(b, t(2), t(5))
    ntran(nca, t(2), zero)
    ntran(c, t(5), zero)

    // carry part
    ptran(a, q(0), one)
    ptran(b, q(0), one)
    ptran(a, q(1), one)
    ptran(c, q(0), nca)
    ptran(b, q(1), nca)
    ptran(nca, one, carry)
    ntran(c, nca, q(2))
    ntran(b, nca, q(3))
    ntran(nca, carry, zero)
    ntran(a, q(2), zero)
    ntran(b, q(2), zero)
    ntran(a, q(3), zero)

    val result = satisfyAll(search(List(a, b, c, sum, carry), input_order, indomain_min))

    println(a + " " + b + " " + " " + c + " " + " " + sum + " " + " " + carry)

  }

}
