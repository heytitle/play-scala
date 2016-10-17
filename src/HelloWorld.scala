import scala.annotation.tailrec


object HelloWorld {
  def main(args: Array[String]) = {
    println("Here is Pat first time scala")

    val a = 4;
    val al = 4L;

    println("`a` is an instance of "+a.getClass())
    println("`al` is an instance of "+al.getClass())

    val l = List(1,2,3,4)

    /* Reduce, Fold */
    printOpt("val l = List(1,2,3,4)", l )
    printOpt("l.reduce(_+_)", l.reduceLeft(_+_) )
    printOpt("l.foldLeft(0)(_+_)", l.foldLeft(0)(_+_))
    printOpt("l.sum", l.sum )
    printOpt( "l.reduceLeft(\"\")(\"(\"+_+\" + \"+_+\")\")", l.map(_.toString).reduceLeft("("+_+" + "+_+")") )
    printOpt( "l.reduceRight(\"\")(\"(\"+_+\" + \"+_+\")\")", l.map(_.toString).reduceRight("("+_+" + "+_+")") )

    /* Infix factorial */
    printOpt("5!", factorial(5) )
    printOpt("5!", 5! )

    /* Compose */
    val add2  = (x:Int) => x+2
    val time3 = (x:Int) => x*3
    printOpt("add2  = (x:Int) => x+2","")
    printOpt("time3 = (x:Int) => x*3","")

    printOpt("l.map( add2 compose time3 )", l.map(add2 compose time3) )
    printOpt("l.map( myCompose(add2,time3)",l.map( myCompose(add2,time3)) )

    /* Non Case class */
    val me = new NotCaseMe("Pat")
    val cpMe = new NotCaseMe("Pat")
    me.say
    printOpt("== non case class", me == cpMe )

    /* Case class */
    /* 1st pro : automatically defines companion object */
    val cme = CaseMe("Zidane")
    cme.say

    val cme2 = CaseMe("Zidane")
    cme.say
    printOpt("== case class", cme == cme2 )

    /* TODO */
    /* Calling by name and value */
    /* define function suct that we can call func arg1 arg2 */
  }

  def printOpt( opt: String, label: Any ): Unit ={
    println(s"$opt : $label")
  }

  def factorial( n: Int ): Long ={
    @tailrec def iter( i: Int, acc: Long ): Long = if( i <= 1) acc else iter(i-1, acc * i)
    iter(n, 1 )
  }

  def myCompose[A,B,C]( f1: B => C, f2: A => B): A => C = (x: A ) =>  f1(f2(x))

  implicit class MyInt( x: Int ){
    def ! = factorial(x)
  }

  class NotCaseMe ( name: String ){
   def say = println(s"Hi!, my name is $name.")
  }

  case class CaseMe ( name: String ){
    def say = println(s"Hi!, my name is $name.")
  }


}

