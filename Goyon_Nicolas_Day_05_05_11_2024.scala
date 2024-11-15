// Databricks notebook source
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types.{IntegerType, StringType, StructType}
// Logger.getLogger("org").setLevel(Level.ERROR)

val porgramStartTime = System.nanoTime()

val logFile = "/FileStore/tables/stackoverflow.csv" 


val schema = new StructType()
  .add("postTypeId", IntegerType, nullable = true)
  .add("id", IntegerType, nullable = true)
  .add("acceptedAnswer", StringType, nullable = true)
  .add("parentId", IntegerType, nullable = true)
  .add("score", IntegerType, nullable = true)
  .add("tag", StringType, nullable = true)

  
val df = spark.read
    .option("header", "false")
    .schema(schema)
    .option("inferSchema","true")
    .csv(logFile)

df.printSchema()
df.show(5)


println(s"Number of lines : ${df.count()}")

val programElapsedTime = (System.nanoTime() - porgramStartTime) / 1e9
println(s"Reading done in : $programElapsedTime s")


