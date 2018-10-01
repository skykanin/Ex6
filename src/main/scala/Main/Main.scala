package Main

object Main extends App {
  var x: Array[Int] = Array()

  for (i <- 1 to 50) x = x :+ i

  val y = 51 to 100

  x = x ++ y
  //x.foreach(println)
  
  // Returns the sum of an int array
  def sumArr(a: Array[Int]): Int = {
    var sum = 0
    for (z <- a) sum += z
    sum
  }

  // Returns the sum of an int array recursively
  def sumArrRec(as: Array[Int]) : Int = as.length match {
    case 0 => 0
    case _ =>
        println(s"${as.tail.length}")
        as.head + sumArrRec(as.tail)
  }

  // Returns nth fib number recursively
  def fib( n : Int) : BigInt = n match {
    case 0 | 1 => n
    case _ => fib( n-1 ) + fib( n-2 )
  }

  /*
    a) It's arguments are a function that takes no arguments and returns BigInt and a boolean
    b) With lazy the function call won't be evaluated before it's value needs to be evaluated in the print statement
    c) It's helpful to use lazy evaluation when we don't need to unnecessarily evaluate large computations before necessary
  */
  def my_func(f: () => BigInt, b: Boolean): Unit = {
    lazy val t = f()
    if (b) println (t)
  }
  //my_func(()=>1000000000, b = true)
}
