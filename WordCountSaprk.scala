package cn.zhw.spark.day1

import org.apache.spark.{SparkConf, SparkContext}

object WordCountSaprk {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("WordCount")

    val context = new SparkContext(conf)
    val RDD = context.textFile("../words.txt")
    RDD
    .flatMap(line => line.split(","))
    .map(word => (word, 1))
    .reduceByKey(_+_)
    .foreach(println)
  }
}