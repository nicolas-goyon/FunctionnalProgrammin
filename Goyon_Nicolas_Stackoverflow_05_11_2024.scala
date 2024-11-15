// Databricks notebook source
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

// COMMAND ----------

val csvDataFile = "/FileStore/tables/stackoverflow" 

// COMMAND ----------

val df = spark.read
      .option("header", "false")
      .option("inferSchema", "true")
      .csv(csvDataFile)

// COMMAND ----------

println(s"\nCount of records in CSV file: ${df.count()}")

// COMMAND ----------

df.printSchema()

// COMMAND ----------

df.show(5)
