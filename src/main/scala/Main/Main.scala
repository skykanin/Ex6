package Main

import java.util.concurrent.atomic.{AtomicInteger, AtomicReference}

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
    2
    a) It's arguments are a function that takes no arguments and returns BigInt and a boolean
    b) With lazy the function call won't be evaluated before it's value needs to be evaluated in the print statement
    c) It's helpful to use lazy evaluation when we don't need to unnecessarily evaluate large computations before necessary
  */
  def my_func(f: () => BigInt, b: Boolean): Unit = {
    lazy val t = f()
    if (b) println (t)
  }

  // my_func(()=>1000000000, b = true)

  // Takes a function and returns that function wrapped in a Thread
  def initThread(func: () => Unit): Thread = new Thread(() => func())

  // initThread(() => println("Hello concurrent")).start()

  def fibArray(n: Int): Array[() => Unit] = n match {
    case 0 => Array(() => println(fib(0)))
    case _ => Array(() => println(fib(n))) ++ fibArray(n-1)
  }

  fibArray(5).foreach(x => x())

  // Maps each lambda in the array to a thread
  fibArray(5).map(initThread)

  /*
    3e) The code isn't thread save because it's not clear what counter state is being referred to when changing it.
        from different threads. To make it thread-safe we can use an AtomicInteger which basically wraps around the
        type of the state to make it clear what state we are returning from the context of the thread.
  */
  private val counter: AtomicInteger = new AtomicInteger(0)
  def increaseCounter(): AtomicInteger = {
    counter.set(counter.get() + 1)
    counter
  }

  /*
    3f) A deadlock is when process x is waiting for process y to finish and process y is waiting for process x to
        finish. Which results in a deadlock, meaning that the program will run in an endless loop and never halt.
        There are several methods for preventing deadlocks like:
          - correct lock ordering
          - lock timeouts
          - deadlock detection
        It's also possible to prevent deadlocks with pure functions. Since pure functions don't have side effects it's
        always safe to run pure functions in parallel.
   */

}
