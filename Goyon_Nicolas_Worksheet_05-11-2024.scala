// Databricks notebook source
// MAGIC %md
// MAGIC #### Functions 
// MAGIC Functions are **expressions** that have:
// MAGIC - parameters 
// MAGIC - take arguments.
// MAGIC
// MAGIC ##### Anonymouns Functions 
// MAGIC You can define an anonymous function i.e., **a function that has no name**
// MAGIC
// MAGIC - On the left of => is a list of parameters. 
// MAGIC - On the right is an expression involving the parameters.
// MAGIC

// COMMAND ----------

(x: Int) => x + 1

// COMMAND ----------

// MAGIC %md
// MAGIC ##### Functions with name
// MAGIC
// MAGIC

// COMMAND ----------

val addOne = (x: Int) => x + 1
println(addOne(1)) // 2

// COMMAND ----------

// MAGIC %md
// MAGIC A function can have multiple parameters:

// COMMAND ----------

val add = (x: Int, y: Int) => x + y
println(add(1, 2)) // 3

// COMMAND ----------

// MAGIC %md
// MAGIC Or it can have no parameters at all

// COMMAND ----------

val getTheAnswer = () => 42
println(getTheAnswer()) // 42

// COMMAND ----------

// MAGIC %md
// MAGIC ## Methods
// MAGIC
// MAGIC Methods look and behave very similar to functions
// MAGIC   - but there are a few key differences between them.
// MAGIC
// MAGIC - Methods are defined with the **def** keyword. 
// MAGIC - **def** is followed by a 
// MAGIC   - name, 
// MAGIC   - parameter list(s)
// MAGIC   - a return type, and a body:

// COMMAND ----------

def add(x: Int, y: Int): Int = x + y
println(add(1, 2)) // 3

// COMMAND ----------

// MAGIC %md
// MAGIC Notice how the return type **Int** is declared after the parameter list and a **:**
// MAGIC
// MAGIC A method can take multiple parameter lists:

// COMMAND ----------

def addThenMultiply(x: Int, y: Int)(multiplier: Int): Int = (x + y) * multiplier
println(addThenMultiply(1, 2)(3)) // 9

// COMMAND ----------

// MAGIC %md
// MAGIC Or no parameter lists at all:

// COMMAND ----------

def name: String = System.getProperty("user.name")
println("Hello, " + name + "!")

// COMMAND ----------

// MAGIC %md
// MAGIC ## Classes
// MAGIC
// MAGIC You can define classes with the class keyword, followed by its name and constructor parameters:

// COMMAND ----------

class Greeter(prefix: String, suffix: String) {
  def greet(name: String): Unit =
    println(prefix + name + suffix)
}

// COMMAND ----------

// MAGIC %md
// MAGIC - The return type of the method greet is Unit, which signifies that there is nothing meaningful to return. 
// MAGIC - It is used similarly to void in Java and C. 
// MAGIC   - (A difference is that, because every Scala expression must have some value, there is actually a singleton value of type Unit, written (). It carries no information.)

// COMMAND ----------

val greeter = new Greeter("Hello, ", "!")
greeter.greet("Scala developer") // Hello, Scala developer!

// COMMAND ----------

// MAGIC %md
// MAGIC ## Case Classes
// MAGIC
// MAGIC - Scala has a special type of class called a “case” class. 
// MAGIC - By default, instances of case classes are immutable, and 
// MAGIC - Case classes are compared by value (unlike classes, whose instances are compared by reference). 
// MAGIC - This makes them additionally useful for pattern matching.
// MAGIC
// MAGIC You can define case classes with the **case class** keywords:
// MAGIC
// MAGIC

// COMMAND ----------

case class Point(x: Int, y: Int)

// COMMAND ----------

// MAGIC %md
// MAGIC You can instantiate case classes without the new keyword:

// COMMAND ----------

val point = Point(1, 2)
val anotherPoint = Point(1, 2)
val yetAnotherPoint = Point(2, 2)

// COMMAND ----------

// MAGIC %md
// MAGIC Instances of case classes are **compared by value**, not by reference:

// COMMAND ----------

if (point == anotherPoint) {
  println(s"$point and $anotherPoint are the same.")
} else {
  println(s"$point and $anotherPoint are different.")
} // Point(1,2) and Point(1,2) are the same.

if (point == yetAnotherPoint) {
  println(s"$point and $yetAnotherPoint are the same.")
} else {
  println(s"$point and $yetAnotherPoint are different.")
} // Point(1,2) and Point(2,2) are different.

// COMMAND ----------

// MAGIC %md
// MAGIC ## Objects
// MAGIC
// MAGIC - Objects are single instances of their own definitions. 
// MAGIC - You can think of them as singletons of their own classes.
// MAGIC
// MAGIC You can define objects with the **object** keyword

// COMMAND ----------

object IdFactory {
  private var counter = 0
  def create(): Int = {
    counter += 1
    counter
  }
}

// COMMAND ----------

// MAGIC %md
// MAGIC You can access an object by referring to its name

// COMMAND ----------

val newId: Int = IdFactory.create()
println(newId) // 1
val newerId: Int = IdFactory.create()
println(newerId) // 2

// COMMAND ----------

// MAGIC %md
// MAGIC What is Inheritence and multiple inheritance

// COMMAND ----------

// MAGIC %md
// MAGIC ## Traits
// MAGIC - Traits are abstract data types containing certain fields and methods. 
// MAGIC - In Scala inheritance, a class can only extend one other class, but it can extend multiple traits.
// MAGIC
// MAGIC You can define traits with the *trait* keyword

// COMMAND ----------

trait Greeter {
  def greet(name: String): Unit
}

// COMMAND ----------

// MAGIC %md
// MAGIC Traits can also have default implementations

// COMMAND ----------

trait Greeter {
  def greet(name: String): Unit =
    println("Hello, " + name + "!")
}

// COMMAND ----------

// MAGIC %md
// MAGIC You can extend traits with the **extends** keyword and **override an implementation** with the override keyword:

// COMMAND ----------

class DefaultGreeter extends Greeter

class CustomizableGreeter(prefix: String, postfix: String) extends Greeter {
  override def greet(name: String): Unit = {
    println(prefix + name + postfix)
  }
}

val greeter = new DefaultGreeter()
greeter.greet("Scala developer") // Hello, Scala developer!

val customGreeter = new CustomizableGreeter("How are you, ", "?")
customGreeter.greet("Scala developer") // How are you, Scala developer?

// COMMAND ----------

// MAGIC %md
// MAGIC Here, DefaultGreeter extends only one single trait, but it could extend multiple traits.

// COMMAND ----------

// MAGIC %md
// MAGIC ## Program Entry Point
// MAGIC - The **main** method is the entry point of a Scala program. 
// MAGIC - The Java Virtual Machine requires a main method, named main, that takes one argument: an array of strings.
// MAGIC - In Scala 2 you must define a main method manually. Using an object, you can define the main method as follows:

// COMMAND ----------

object Main {
  def main(args: Array[String]): Unit =
    println("Hello, Scala developer!")
}

// COMMAND ----------

// MAGIC %md
// MAGIC
