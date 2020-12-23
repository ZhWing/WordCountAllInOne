package main.scala

import java.io.PrintWriter
import scala.io.Source

object WordCount {
  def main(args: Array[String]): Unit = {

    val out = new PrintWriter("../words.txt")

    Source
      .fromFile("data/word.txt")
      .getLines()
      .flatMap(_.split(","))
      .map((_, 1))
      .toList
      .groupBy(_._1)
      .map(x => (x._1, x._2.map(_._2).sum))
      .toList
      .sortBy(line => -line._2.toInt)
      .foreach(line => {
        out.println(line._1 + "\t" + line._2)
      })

    out.close()
  }
}
