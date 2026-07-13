package com.example.kotlinlessons.lesson06

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinlessons.R

class Lesson6Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lesson6)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val employee = Employee().apply {
            name = "Venkata Ramana"
            department = "Android"
            salary = 70000
        }

        Log.i(Tag, "Employee: $employee")
        Log.i(Tag, "Employee name: ${employee.name}")
        Log.i(Tag, "Employee department: ${employee.department}")
        Log.i(Tag, "Employee salary: ${employee.salary}")

        var employeeName: String? = "Venkata Ramana"
        employeeName?.let {
            Log.i(Tag, "Employee name is not null: $employeeName")
        }

        Employee().also {
            Log.i(Tag, "Employee created")
        }

        val result = run {
            val a = 100
            val b = 200
            a + b
        }
        Log.i(Tag, "Addition: $result")

        val employee2 = Employee()
        with(employee2) {
            name = "Raju"
            department = "QA"
            salary = 50000
        }
        Log.i(Tag, "Employee2: $employee2")
        Log.i(Tag, "Employee2 name: ${employee2.name}")
        Log.i(Tag, "Employee2 department: ${employee2.department}")
        Log.i(Tag, "Employee2 salary: ${employee2.salary}")

        val employees = mutableSetOf<Employee2>()
        employees.add(Employee2().apply {
            name = "Suresh"
            department = "iOS"
            salary = 60000
        })
        employees.add(Employee2().apply {
            name = "Ramesh"
            department = "Android"
            salary = 60000
        })
        employees.add(Employee2().apply {
            name = "Vahesh"
            department = "Web"
            salary = 60000
        })
        Log.i(Tag, "Employees Set: $employees")

        employees.forEach {
            Log.i(Tag, "Employee in Set: ${it.name}, ${it.department}, ${it.salary}")
        }
    }

    companion object {
        private const val Tag = "Lesson6Activity"
    }
}

class Employee {

    var name = ""

    var department = ""

    var salary = 0

}

data class Employee2(

    var name: String = "",

    var department: String = "",

    var salary: Int = 0

)
