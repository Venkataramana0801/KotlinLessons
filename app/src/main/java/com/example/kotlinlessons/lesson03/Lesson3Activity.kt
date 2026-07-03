package com.example.kotlinlessons.lesson03

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinlessons.R

class Lesson3Activity : AppCompatActivity() {
    private var employeeName: String? = null
    private val age: Int? = null
    private var activityName = "Lesson3Activity"
    private val value: Any = "Android"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lesson3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.i(activityName, "Length! ${employeeName?.length}");
        Log.e(activityName, "Name! ${employeeName ?: "Unknown Employee"}")

        employeeName = "Venkata Ramana"

        Log.i(activityName, "assigned Length! ${employeeName?.length}");
        Log.e(activityName, "assigned Name! ${employeeName ?: "Unknown Employee"}")

        employeeName.let {
            Log.e(activityName, "let Name! Welcome $it")
        }

        Log.e(activityName, "Age ${age ?: 0}")

        val valueCast = value as? String
        Log.i(activityName, "Safe cast $valueCast")

        val employee =
            Employee(
                null,
                "Android",
                null
            )

        Log.e(activityName, "Employee  Name! ${employee.name ?: "Unknown Employee"}")
        Log.e(activityName, "Employee  Department! ${employee.department ?: "Unknown Department"}")
        Log.e(activityName, "Employee  Email! ${employee.email ?: "Unknown Email"}")
    }
}

data class Employee(

    val name: String?,

    val department: String?,

    val email: String?

)