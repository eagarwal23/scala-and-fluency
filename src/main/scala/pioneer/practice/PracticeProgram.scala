package pioneer.practice
import Practice.square

object PracticeProgram extends App{
  println("Enter a number: ")
  val inputNum = scala.io.StdIn.readInt()
  val finalResult = square(inputNum)
  println(s"The square of the number you input, ${inputNum}, is ${finalResult}."))
