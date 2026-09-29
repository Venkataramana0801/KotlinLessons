package com.example.kotlinlessons.lesson07

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinlessons.R

class Lesson7Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lesson7)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Function to display the name
        fun greet(name: String) {
            Log.i(Tag, "Welcome $name")
        }
        greet("Venkata Ramana")

        //Lambda to display the name
        val greet1 = { name: String ->
            Log.i(Tag, "Welcome $name")
        }
        greet1("Venkata Ramana")

        val square = { value: Int ->
            value * value
        }
        Log.i(Tag, "Square of 5 is ${square(5)}")

        //Lambda with multiple parameters
        val add = { a: Int, b: Int ->
            a + b
        }
        Log.i(Tag, "Sum of 10 and 20 is ${add(10, 20)}")

        //Lambda with no parameters
        val sayHello = {
            Log.i(Tag, "Hello from Lambda!")
        }
        sayHello()

        //Higher-order function: passing a lambda as an argument
        fun performOperation(a: Int, b: Int, operation: (Int, Int) -> Int) {
            val result = operation(a, b)
            Log.i(Tag, "Result of operation: $result")
        }
        performOperation(15, 5) { x, y -> x + y }
        performOperation(15, 5) { x, y -> x * y }
        performOperation(15, 5) { x, y -> x / y }

        //Higher-order function: returning a function
        fun getGreetingFunction(): (String) -> Unit {
            return { name -> Log.i(Tag, "Greetings, $name!") }
        }

        val greeter = getGreetingFunction()
        greeter("Android Developer")

        // Using 'it' for single parameter lambdas
        val doubleValue: (Int) -> Int = { it * 2 }
        Log.i(Tag, "Double of 10 is ${doubleValue(10)}")

        // Common higher-order function: let
        val name: String? = "Kotlin"
        name?.let {
            Log.i(Tag, "The length of the name is ${it.length}")
        }

        // Common higher-order functions on collections: filter and map
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val evenSquares = numbers
            .filter { it % 2 == 0 }
            .map { it * it }
        Log.i(Tag, "Even squares: $evenSquares")

        val employees = listOf(
            Employee("Alice", 50000),
            Employee("Bob", 60000),
            Employee("Charlie", 45000)
        )
        val highPaidEmployees = employees.filter { it.salary > 50000 }.map { it.name }
        Log.i(Tag, "High paid employees: $highPaidEmployees")

        // Sorting a list using a lambda
        val sortedEmployees = employees.sortedBy { it.salary }
        Log.i(Tag, "Employees sorted by salary: $sortedEmployees")

        employees.forEach(::printEmployee)
    }

    fun printEmployee(employee: Employee) {
        Log.i(Tag, "Employee: ${employee.name}, Salary: ${employee.salary}")
    }

    companion object {
        private const val Tag = "Lesson7Activity"
    }
}

data class Employee(

    val name: String,

    val salary: Int

)