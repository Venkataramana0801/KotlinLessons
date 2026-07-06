package com.example.kotlinlessons.lesson04

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinlessons.R

class Lesson4Activity : AppCompatActivity() {
    val activityName = "Lesson4Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lesson4)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val employee1 = Employee1()
        Log.i(activityName, "Employee Name: ${employee1.name}")
        Log.i(activityName, "Employee Experience: ${employee1.experience}")
        Log.i(activityName, "Employee Company: ${employee1.company}")

        val employee2 = Employee2("Venkata Ramana", 13, "ABC Technologies")
        Log.i(activityName, "Employee Name: ${employee2.name}")
        Log.i(activityName, "Employee Experience: ${employee2.experience}")
        Log.i(activityName, "Employee Company: ${employee2.company}")

        val employee3 = Employee3("Venkata Ramana", "ABC Technologies")
        Log.i(activityName, "Employee Name: ${employee3.name}")
        Log.i(activityName, "Employee Company: ${employee3.company}")

        val employee4 = Employee4()
        Log.i(activityName, "Emplyoee Salary: ${employee4.salary}")

        val employeeData1 = EmployeeData(1, "Venkata Ramana", "ABC Technologies")
        val employeeData2 = EmployeeData(1, "Venkata Ramana", "ABC Technologies")
        Log.i(activityName, "Employee data: $employeeData1")
        Log.i(activityName, "Objects Euqual or not: ${employeeData1 == employeeData2}")

       val employeeData3=employeeData1.copy(2, "Venkatatesh")
        Log.i(activityName, "Emloyee data: $employeeData3")

    }
}

class Employee1 {
    val name = "Venkata Ramana"
    val experience = 13
    val company = "ABC Technologies"
}

class Employee2(
    val name: String,
    val experience: Int,
    val company: String)

class Employee3(val name: String) {
    var company = ""

    constructor(name: String, company: String) : this(name) {
        this.company = company
    }

    init {
        Log.i("init Method", "Emplyoee created")
    }

}

class Employee4 {
    var salary = -100
        set(value) {

            if (value > 0) {
                field = value
            }

        }
        get() = field
}

data class EmployeeData(
    val id: Int,
    val name: String,
    val company: String,
) {

}

data class EmployeeData2(
    val id: Int,
    val name: String,
    val company: String,
) {

}
