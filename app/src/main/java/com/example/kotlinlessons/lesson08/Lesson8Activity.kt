package com.example.kotlinlessons.lesson08

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinlessons.R
import com.example.kotlinlessons.lesson07.Employee

class Lesson8Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lesson8)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = "Venkata Ramana"
        name.welcome()

        val sqaure = 5.square()
        Log.i(TAG, "Square of 5 is $sqaure")

        Log.i(TAG, "First character of $name is ${name.firstCharacter}")

        val employee = Employee("Venkata Ramana", 60000, 13, "Android")

        Log.i(TAG, "Is ${employee.name} a high salary? ${employee.isHighSalary()}")
        Log.i(TAG, "Is ${employee.name} a high experience? ${employee.isHighExperience()}")

        val employees = listOf(
            Employee("Venkata Ramana", 60000, 13, "Android"),
            Employee("Ravi", 50000, 8, "iOS"),
            Employee("Ramesh", 70000, 15, "Android")
        )

        Log.i(TAG, "Average salary of employees is ${employees.averageSalary()}")

        employees.printEmployees()
    }

    data class Employee(
        val name: String,
        val salary: Int,
        val experience: Int,
        val department: String
    )

    companion object {
        private const val TAG = "Lesson8Activity"
    }

    fun String.welcome() {
        Log.i(TAG, "Welcome $this")
    }

    fun Int.square(): Int {
        return this * this
    }

    val String.firstCharacter: Char
        get() = this.first()

    fun Employee.isHighSalary(): Boolean {
        return salary > 50000
    }

    fun Employee.isHighExperience(): Boolean {

        return experience > 10
    }

    fun List<Employee>.printEmployees() {
        forEach {
            Log.i(
                "Extension",
                "${it.name} - ${it.department}"
            )
        }
    }

    fun List<Employee>.averageSalary(): Double {
        return if (isEmpty()) {
            0.0
        } else {
            return sumOf { it.salary }.toDouble() / size
        }
    }
}