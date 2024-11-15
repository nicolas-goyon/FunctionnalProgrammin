// Databricks notebook source
// MAGIC %md
// MAGIC # Functional Programming in Scala
// MAGIC ***
// MAGIC
// MAGIC > #### Table of Contents
// MAGIC ***
// MAGIC
// MAGIC ##### 1. Functional Programming ✅
// MAGIC ##### 2. Pure Functions ✅
// MAGIC ##### 3. Immutability ✅
// MAGIC ##### 4. First-Class and Higher-Order Functions ✅
// MAGIC ##### 5. Function Literals and Anonymous Functions ✅
// MAGIC ##### 6. Currying and Partial Application
// MAGIC ##### 7. Functional Collections
// MAGIC ##### 8. Lazy Evaluation
// MAGIC ##### 9. Functional Programming in Practice
// MAGIC ##### 10. Conclusion
// MAGIC

// COMMAND ----------

// MAGIC %md
// MAGIC > **1. Functional Programming (FP)**
// MAGIC ***
// MAGIC ***
// MAGIC
// MAGIC Functional Programming (FP) is a programming paradigm where computation is treated as:
// MAGIC - the evaluation of mathematical functions
// MAGIC - avoiding changing state and 
// MAGIC - mutable data
// MAGIC
// MAGIC It emphasizes the use of functions, immutability, and expressions over statements.

// COMMAND ----------

// MAGIC %md
// MAGIC > **Key Concepts**
// MAGIC
// MAGIC **Declarative Style:** 
// MAGIC  - Focuses on what to solve rather than how to solve it.
// MAGIC
// MAGIC **Expressions Over Statements:** 
// MAGIC  - Builds programs using expressions that evaluate to values.
// MAGIC
// MAGIC **Function Composition:**
// MAGIC  - Combining simple functions to build more complex ones.
// MAGIC
// MAGIC **Referential Transparency:** 
// MAGIC  - An expression can be replaced with its value without changing the program's behavior.
// MAGIC  

// COMMAND ----------

// MAGIC %md
// MAGIC > **Scala and Functional Programming**
// MAGIC
// MAGIC
// MAGIC Scala is a hybrid language that _supports_ both object-oriented and functional programming paradigms, making it an excellent choice for learning FP concepts. It provides powerful features like higher-order functions, immutability, and pattern matching.
// MAGIC
// MAGIC

// COMMAND ----------

// MAGIC %md
// MAGIC > **2. Pure Functions**
// MAGIC ***
// MAGIC ***
// MAGIC
// MAGIC A pure function is a function where:
// MAGIC - The output value is determined only by its input values.
// MAGIC - It has no side effects (does not alter any state outside the function).
// MAGIC
// MAGIC **Characteristics**
// MAGIC
// MAGIC **Deterministic:** 
// MAGIC - Same input always yields the same output.
// MAGIC
// MAGIC **No Side Effects:** 
// MAGIC - Doesn't modify any external state (e.g., no I/O operations, no changing global variables).

// COMMAND ----------

// Pure function
def multiply(a: Int, b: Int): Int = a * b

// COMMAND ----------

// Calling the pure function 'multiply'
val result_after_first_call = multiply(3, 4)

// COMMAND ----------

val result_after_second_call = multiply(3, 4)

// COMMAND ----------

// Non-pure function due to side effect (modifying 'total')
var total = 0

def addToTotal(a: Int): Int = {
  total += a
  total
}


// COMMAND ----------

// Calling the non-pure function 'addToTotal'
val total_after_first_call = addToTotal(5)

// COMMAND ----------

val total_after_second_call = addToTotal(5)

// COMMAND ----------

// MAGIC %md
// MAGIC **Benefits of Pure Functions**
// MAGIC
// MAGIC - Easier Testing: 
// MAGIC   - Predictable outputs make unit testing straightforward
// MAGIC - Referential Transparency: 
// MAGIC   - Facilitates reasoning about code
// MAGIC - Parallelization: 
// MAGIC   - Safe to execute in parallel environments

// COMMAND ----------

// MAGIC %md
// MAGIC > **3. Immutability**
// MAGIC ***
// MAGIC ***
// MAGIC
// MAGIC Immutability means that once a data structure is created, it cannot be changed. Any modification results in a new data structure.
// MAGIC
// MAGIC **In Scala**
// MAGIC
// MAGIC `val` vs. `var`:
// MAGIC
// MAGIC - `val`: Immutable reference (cannot be reassigned).
// MAGIC
// MAGIC - `var`: Mutable reference (can be reassigned).

// COMMAND ----------

val list = List(1, 2, 3)

// COMMAND ----------

val list1 = List(0, 1, 2, 3)

// COMMAND ----------

val list2 = -1 :: list1

// COMMAND ----------

// Attempting to modify 'list' directly will result in a compilation error
list = List(0, 1, 2, 3)


// COMMAND ----------

// MAGIC %md
// MAGIC **cons operator or prepend operator ::**
// MAGIC - It is used to add an element to the front of a list, creating a new list without modifying the original one.
// MAGIC - `::` takes _an element on the left_ and _a list_ on the right, and it prepends the element to the front of the list.
// MAGIC - Prepending `0 ::`  to 'list' creates a new list

// COMMAND ----------

// To 'modify', create a new list
val newList = 0 :: list

// COMMAND ----------

// MAGIC %md
// MAGIC **Benefits of Immutability**
// MAGIC
// MAGIC - Thread Safety: 
// MAGIC   - Immutable objects are inherently thread-safe.
// MAGIC - Predictability: 
// MAGIC   - Reduces unexpected side effects.
// MAGIC - Easier Debugging: 
// MAGIC   - State changes are explicit.

// COMMAND ----------

// MAGIC %md
// MAGIC
// MAGIC > **4. First-Class and Higher-Order Functions**
// MAGIC ***
// MAGIC ***
// MAGIC
// MAGIC **First-Class Functions**
// MAGIC ***
// MAGIC
// MAGIC - Functions are treated as first-class citizens, meaning they can be:
// MAGIC
// MAGIC  - Assigned to variables.
// MAGIC  - Passed as arguments to other functions.
// MAGIC  - Returned from functions.
// MAGIC
// MAGIC
// MAGIC **Higher-Order Functions**
// MAGIC ***
// MAGIC - A higher-order function is a function that takes other functions as parameters or returns a function as a result.
// MAGIC
// MAGIC

// COMMAND ----------

// MAGIC %md
// MAGIC Example: Assigning Functions to Variables

// COMMAND ----------

val greet = (name: String) => s"Hello, $name!"

// COMMAND ----------

println(greet("Faaiz")) 

// COMMAND ----------

// MAGIC %md
// MAGIC Example: Passing Functions as Arguments

// COMMAND ----------

def applyTwice(f: Int => Int, x: Int): Int = f(f(x))

// COMMAND ----------

val increment = (x: Int) => x + 1

// COMMAND ----------

println(applyTwice(increment, 5)) 

// COMMAND ----------

// MAGIC %md
// MAGIC Example: Returning Functions from Functions

// COMMAND ----------

def multiplier(factor: Int): Int => Int = {
  (x: Int) => x * factor
}

// COMMAND ----------

// MAGIC %md
// MAGIC - `multiplier` is a function that takes an integer parameter, `factor`.
// MAGIC
// MAGIC - The return type of multiplier is `Int => Int`. 
// MAGIC
// MAGIC - This means it returns a function that takes an Int as input and returns an Int as output.
// MAGIC
// MAGIC - Key Point: 
// MAGIC   - multiplier is a higher-order function because it returns a function as its result.
// MAGIC
// MAGIC - The inner function `(x: Int) => x * factor` is a closure because it "captures" the factor value from the environment in which it was created (the call to multiplier).

// COMMAND ----------

val triple = multiplier(3)


// COMMAND ----------

println(triple(20))

// COMMAND ----------

// MAGIC %md
// MAGIC **Benefits of Higher-Order Functions**
// MAGIC
// MAGIC - Abstraction: 
// MAGIC   - Encapsulate behaviors.
// MAGIC
// MAGIC - Functional Composition: 
// MAGIC   - Build complex operations from simpler ones.
// MAGIC

// COMMAND ----------

// MAGIC %md
// MAGIC > **5. Function Literals and Anonymous Functions**
// MAGIC
// MAGIC - Function literals, also known as `lambda expressions` or `anonymous functions`, allow you to define functions without naming them. 
// MAGIC
// MAGIC - They are used for short snippets of code that are passed to higher-order functions.

// COMMAND ----------

// MAGIC %md
// MAGIC Syntax

// COMMAND ----------

// // Standard syntax
// (val1: Type1, val2: Type2) => expression

// Example
(x: Int, y: Int) => x + y



// COMMAND ----------

// MAGIC %md
// MAGIC Example: Using Anonymous Functions with Collections

// COMMAND ----------

val numbers = List(1, 2, 3, 4, 5)


// COMMAND ----------

// Using anonymous function with map
val squares = numbers.map((x: Int) => x * x)

// COMMAND ----------

println(squares)

// COMMAND ----------

// MAGIC %md
// MAGIC **Simplified Syntax with Type Inference**
// MAGIC - Scala can infer types in many cases, allowing for more concise code.

// COMMAND ----------

// Type inference
val squares = numbers.map(x => x * x)

// COMMAND ----------

// MAGIC %md
// MAGIC **Using Underscore Notation `_`**
// MAGIC - When the parameter appears only once, you can use the underscore (_) as a placeholder.

// COMMAND ----------

// Using underscore notation
val double = numbers.map(_ * 2)
println(double)

// COMMAND ----------

val incremented = numbers.map(_ + 1)
println(incremented)


// COMMAND ----------

// MAGIC %md
// MAGIC > **6. Currying and Partial Application**
// MAGIC ***
// MAGIC ***
// MAGIC
// MAGIC - Currying is the process of transforming a function that takes multiple arguments into a sequence of functions, each with a single argument. 
// MAGIC
// MAGIC - It allows for more flexible function definitions and can enable partial application.
// MAGIC
// MAGIC ```scala
// MAGIC def functionName(arg1: Type1)(arg2: Type2)...(argN: TypeN): ReturnType = {
// MAGIC   // function body
// MAGIC }
// MAGIC ```

// COMMAND ----------

def add(x: Int)(y: Int): Int = x + y

// COMMAND ----------

// Using the curried function
println(add(5)(10))

// COMMAND ----------

// MAGIC %md
// MAGIC **Partial Application**
// MAGIC - Partial application involves fixing a few arguments of a function, producing another function of fewer arguments.
// MAGIC
// MAGIC

// COMMAND ----------

// MAGIC %md
// MAGIC **Using Partial Application with Currying**

// COMMAND ----------

// Partially applying 'add' by fixing 'x' to 5
val addFive = add(5)_


// COMMAND ----------

println(addFive(10)) 


// COMMAND ----------

// MAGIC %md
// MAGIC **Explanation**
// MAGIC - `add(5)` returns a function that takes an Int and adds 5 to it.
// MAGIC - The underscore `_` is a placeholder indicating that we are not supplying all arguments yet.

// COMMAND ----------

// MAGIC %md
// MAGIC **Real-World Example: Logging Function**

// COMMAND ----------

def log(level: String)(message: String): Unit = {
  println(s"[$level] $message")
}


// COMMAND ----------

// Create specialized logging functions
val infoLog = log("INFO")_
val errorLog = log("ERROR")_


// COMMAND ----------

// MAGIC %md
// MAGIC - `log` is a curried function, meaning it’s defined with two sets of parameter lists.
// MAGIC
// MAGIC - The first parameter list takes a `level` of type String (e.g., `"INFO"` or `"ERROR"`).
// MAGIC
// MAGIC - The second parameter list takes a `message` of type `String`, which is the actual log message to print.
// MAGIC
// MAGIC - Inside the function, it prints the message with the specified log level in brackets

// COMMAND ----------

// Use the specialized functions
infoLog("This is an informational message.")
errorLog("This is an error message.")

// COMMAND ----------

// MAGIC %md
// MAGIC **Partial Function Application Without Currying**
// MAGIC - You can partially apply functions that are not curried by using placeholders for missing arguments.

// COMMAND ----------

def multiply(a: Int, b: Int, c: Int): Int = a * b * c


// COMMAND ----------

// Partially apply 'multiply' by fixing 'a' to 2
val multiplyByTwo = multiply(2, _: Int, _: Int)

// COMMAND ----------

println(multiplyByTwo(3, 4))

// COMMAND ----------

// MAGIC %md
// MAGIC **Benefits of Currying**
// MAGIC
// MAGIC - Function Reusability: 
// MAGIC   - Create specialized versions of general functions.
// MAGIC
// MAGIC - Code Clarity: 
// MAGIC   - Makes intent explicit and code more expressive.
// MAGIC
// MAGIC - Modularity: 
// MAGIC   - Break down complex functions into simpler, composable parts.

// COMMAND ----------

// MAGIC %md
// MAGIC > **7. Functional Collections**
// MAGIC ***
// MAGIC ***
// MAGIC
// MAGIC **Immutable Collections**
// MAGIC ***
// MAGIC Scala provides a rich set of immutable collections in the scala.collection.immutable package, which is imported by default.
// MAGIC
// MAGIC Common Immutable Collections:
// MAGIC
// MAGIC - List: A linear immutable sequence.
// MAGIC - Vector: An immutable, fast, and random-access sequence.
// MAGIC - Set: An unordered collection of unique elements.
// MAGIC - Map: A collection of key-value pairs.
// MAGIC
// MAGIC

// COMMAND ----------

// MAGIC %md
// MAGIC **Functional Methods**
// MAGIC ***
// MAGIC
// MAGIC Immutable collections come with a plethora of methods that facilitate functional programming.
// MAGIC
// MAGIC
// MAGIC **Transformation Methods:**
// MAGIC
// MAGIC - map: Applies a function to each element.
// MAGIC - flatMap: Maps each element and flattens the result.
// MAGIC - collect: Maps and filters based on partial functions.
// MAGIC
// MAGIC **Filtering Methods:**
// MAGIC
// MAGIC - filter: Selects elements that meet a condition.
// MAGIC - filterNot: Selects elements that do not meet a condition.
// MAGIC - takeWhile: Takes elements as long as a condition is true.
// MAGIC - dropWhile: Drops elements as long as a condition is true.
// MAGIC
// MAGIC **Reduction Methods:**
// MAGIC
// MAGIC - foldLeft / foldRight: Reduces a collection to a single value with an initial seed.
// MAGIC - reduceLeft / reduceRight: Reduces a collection without an initial value.
// MAGIC - aggregate: Aggregates elements with separate combiners and reducers.
// MAGIC

// COMMAND ----------

// MAGIC %md
// MAGIC Examples: 
// MAGIC
// MAGIC Mapping Over a Collection

// COMMAND ----------

// val ys = xs.map((x: Int) => x * 15.0)
// val ys = xs.map {_ * 15.0}

val numbers = List(1, 2, 3, 4, 5)

// Doubling each element
val doubled = numbers.map(_ * 2)
println(doubled)


// COMMAND ----------

// MAGIC %md
// MAGIC FlatMapping

// COMMAND ----------

val nestedNumbers = List(List(1, 2), List(3, 4), List(5))

// Flattening and mapping
val incremented = nestedNumbers.flatMap(list => list.map(_ + 1))
println(incremented) 


// COMMAND ----------

// MAGIC %md
// MAGIC collect
// MAGIC
// MAGIC - The collect method applies a partial function to elements of the collection, filtering and transforming elements simultaneously.

// COMMAND ----------


// Collecting only even numbers and doubling them
val doubledEvens = numbers.collect { case x if x % 2 == 0 => x * 2 }
println(doubledEvens) // Output: List(4, 8, 12)


// COMMAND ----------

// MAGIC %md
// MAGIC Filtering a Collection

// COMMAND ----------

// Selecting even numbers
val evens = numbers.filter(_ % 2 == 0)
println(evens) 

// COMMAND ----------

// MAGIC %md
// MAGIC **filterNot**
// MAGIC
// MAGIC  -The filterNot method removes elements that satisfy a given condition.

// COMMAND ----------

// Filtering out even numbers
val odds = numbers.filterNot(_ % 2 == 0)
println(odds) // Output: List(1, 3, 5)


// COMMAND ----------

// MAGIC %md
// MAGIC **takeWhile**
// MAGIC - The takeWhile method takes elements from the collection as long as a condition is true and stops once the condition fails.

// COMMAND ----------

// Taking elements while they are less than 4
val lessThanFour = numbers.takeWhile(_ < 4)
println(lessThanFour) 


// COMMAND ----------

// MAGIC %md
// MAGIC **dropWhile**
// MAGIC
// MAGIC - The dropWhile method drops elements as long as a condition is true, and once the condition fails, it keeps the remaining elements.

// COMMAND ----------

// Dropping elements while they are less than 4
val fromFourOnwards = numbers.dropWhile(_ < 4)
println(fromFourOnwards) 

// COMMAND ----------

// MAGIC %md
// MAGIC Reducing a Collection

// COMMAND ----------

// Summing all elements
val sum = numbers.reduce(_ + _)
println(sum)


// COMMAND ----------

// MAGIC %md
// MAGIC **Folding with an Initial Value**

// COMMAND ----------

// Concatenating strings with an initial value
val words = List("Scala", "is", "fun")
val sentence = words.foldLeft("Programming in")(_ + " " + _)
println(sentence)


// COMMAND ----------

// MAGIC %md
// MAGIC **foldRight**
// MAGIC
// MAGIC - The foldRight method combines elements from the right side (end of the list) to the left, using an initial value and a binary function.

// COMMAND ----------

// Concatenating strings from the right
val words = List("Scala", "is", "fun")
val sentenceRight = words.foldRight("!")(_ + " " + _)
println(sentenceRight) 

// COMMAND ----------

// MAGIC %md
// MAGIC **reduceLeft**
// MAGIC - The reduceLeft method reduces the elements from the left to the right, applying a binary function, without an initial value.

// COMMAND ----------

//val numbers = List(1, 2, 3, 4, 5)
// Finding the maximum number by reducing from the left
val maxNumber = numbers.reduceLeft((x, y) => if (x > y) x else y)
println(maxNumber)


// COMMAND ----------

// MAGIC %md
// MAGIC **reduceRight**
// MAGIC - The reduceRight method reduces the elements from the right to the left, applying a binary function, without an initial value.

// COMMAND ----------

//val numbers = List(1, 2, 3, 4, 5)
// Finding the minimum number by reducing from the right
val minNumber = numbers.reduceRight((x, y) => if (x < y) x else y)
println(minNumber) // Output: 1


// COMMAND ----------

// MAGIC %md
// MAGIC **aggregate**
// MAGIC - The aggregate method is used for parallel processing and combines elements using two functions:
// MAGIC
// MAGIC   - A sequence operation that operates on each partition.
// MAGIC   - A combination operation that merges the results from each partition.
// MAGIC
// MAGIC Here’s an example of aggregating values from a list with both sequence and combination functions:

// COMMAND ----------

// val numbers = List(1, 2, 3, 4, 5)
// Summing squares in parallel partitions
val sumOfSquares = numbers.aggregate(0)(
  (acc, num) => acc + num * num,     // sequence operation
  (acc1, acc2) => acc1 + acc2        // combination operation
)
println(sumOfSquares) // Output: 91 (1*1 + 2*2 + 3*3 + 4*4 + 5*5 + 6*6)


// COMMAND ----------

// MAGIC %md
// MAGIC **Working with Maps**

// COMMAND ----------

val ages = Map("Faaiz" -> 42, "Vincent" -> 45, "Thomas" -> 32)

// COMMAND ----------

// Transforming map values
val incrementedAges = ages.mapValues(_ + 1)
println(incrementedAges) // Output: Map(Alice -> 26, Bob -> 30, Charlie -> 33)

// COMMAND ----------

// Filtering map entries
val adults = ages.filter { case (_, age) => age >= 30 }
println(adults) // Output: Map(Charlie -> 32)


// COMMAND ----------

// MAGIC %md
// MAGIC Using Set Operations

// COMMAND ----------

val setA = Set(1, 2, 3)
val setB = Set(3, 4, 5)


// COMMAND ----------

// Union
println(setA union setB) // Output: Set(1, 2, 3, 4, 5)

// COMMAND ----------

// Intersection
println(setA intersect setB) // Output: Set(3)


// COMMAND ----------

// Difference
println(setA diff setB) // Output: Set(1, 2)

// COMMAND ----------

// MAGIC %md
// MAGIC > **Lazy Evaluation**
// MAGIC ***
// MAGIC ***
// MAGIC
// MAGIC - Lazy evaluation is a computation strategy that delays the evaluation of an expression until its value is needed. 
// MAGIC
// MAGIC - In Scala, this can help improve performance by avoiding unnecessary calculations.
// MAGIC
// MAGIC **Using lazy val**
// MAGIC
// MAGIC - Declaring a value as `lazy` defers its initialization until it is first accessed.

// COMMAND ----------

lazy val expensiveComputation = {
  println("Performing expensive computation...")
  // Simulate a heavy computation
  Thread.sleep(1000)
  42
}


// COMMAND ----------

println("Before accessing 'expensiveComputation'")
println(expensiveComputation) // Triggers computation
println(expensiveComputation) // Uses cached value

// COMMAND ----------

// MAGIC %md
// MAGIC > **9. Functional Programming in Practice**
// MAGIC ***
// MAGIC ***
// MAGIC
// MAGIC **Functional Design Principles**
// MAGIC ***
// MAGIC
// MAGIC - Modularity: 
// MAGIC   - Break down problems into small, reusable functions.
// MAGIC
// MAGIC - Pure Functions: 
// MAGIC   - Write functions without side effects for predictability.
// MAGIC
// MAGIC - Immutability: 
// MAGIC  - Favor immutable data structures to avoid unintended state changes.
// MAGIC
// MAGIC - Function Composition: 
// MAGIC  - Build complex operations by composing simple functions.
// MAGIC
// MAGIC - Expressiveness: 
// MAGIC  - Use higher-order functions and combinators to write concise code.
// MAGIC
// MAGIC **Considerations**
// MAGIC
// MAGIC - Leverage the Standard Library: Utilize Scala's rich collection of functional utilities.
// MAGIC
// MAGIC - Avoid Shared Mutable State: Minimize the use of var and mutable collections.
// MAGIC
// MAGIC - Embrace Pattern Matching: Use pattern matching for elegant data decomposition.
// MAGIC
// MAGIC Error Handling: Use Option, Either, or Try to handle errors functionally.
// MAGIC

// COMMAND ----------

// MAGIC %md
// MAGIC // User.csv
// MAGIC ```
// MAGIC id,name,age,city
// MAGIC 1,P1,28,New York
// MAGIC 2,P2,22,San Francisco
// MAGIC 3,P3,30,New York
// MAGIC 4,P4,25,Boston
// MAGIC 5,P5,35,San Francisco
// MAGIC ```

// COMMAND ----------

// MAGIC %md
// MAGIC **Goal**
// MAGIC - Filter users aged 25 and above.
// MAGIC - Transform data to extract names and cities.
// MAGIC - Group users by city.

// COMMAND ----------

// MAGIC %md
// MAGIC > **10. Conclusion**
// MAGIC ***
// MAGIC ***
// MAGIC
// MAGIC Functional programming in Scala leverages powerful concepts such as immutability, higher-order functions, and lazy evaluation to create robust, maintainable, and scalable software. By embracing these principles, developers can write code that is easier to reason about, test, and debug.
// MAGIC
// MAGIC **Key Takeaways**
// MAGIC
// MAGIC - Function Literals and Anonymous Functions: Enable concise and expressive code, especially when working with higher-order functions.
// MAGIC
// MAGIC - Currying and Partial Application: Allow for flexible function definitions and specialization, enhancing code reuse.
// MAGIC
// MAGIC - Functional Collections: Provide a rich set of immutable data structures and operations for effective data manipulation.
// MAGIC
// MAGIC - Lazy Evaluation: Offers performance benefits by deferring computations until necessary.
// MAGIC
