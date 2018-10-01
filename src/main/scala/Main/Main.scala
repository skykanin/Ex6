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

  def initThread(func: () => Unit): Thread = new Thread(() => func())

  initThread(() => println("Hello concurrent")).start()

  def fibArray(n: Int): Array[() => Unit] = n match {
    case 0 => Array(() => println(fib(0)))
    case _ => Array(() => println(fib(n))) ++ fibArray(n-1)
  }

  // fibArray(5).foreach(x => x())

  // Maps each lambda in the array to a thread
  fibArray(5).map(initThread)

  /*
    3e) The code isn't thread save because it has a side effect which changes the global state.
        This can influence other threads. To make it thread-safe return the counter + 1 without mutating it.
        The result is that we lose the global mutable state.
  */
  private val counter: Int = 0
  def increaseCounter(): Int = counter + 1

  /*
    3f) A deadlock is when process x is waiting for process y to finish and process y is waiting for process x to
        finish. Which results in a deadlock, meaning that the program will run in an endless loop and never halt.
        There are several methods to preventing deadlock like:
          - correct lock ordering
          - lock timeouts
          - deadlock detection
        It's also possible to prevent deadlocks with pure functions. Since pure functions don't have side effects it's
        always safe to run pure functions in parallel.
   */
}
