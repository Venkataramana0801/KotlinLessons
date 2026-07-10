package com.example.kotlinlessons.lesson05

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinlessons.R

class Lesson5Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lesson5)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val employeeList = listOf(
            "Venkata Ramana",
            "Venkatesh",
            "Ravinder",
            "Raju",
            "Sridhar"
        )

        employeeList.forEach {
            Log.i(TAG, "Employee Name: $it")
        }

        val employeeList2 = mutableListOf(
            "Venkata Ramana",
            "Venkatesh",
            "Ravinder",
            "Raju",
            "Sridhar"
        )
        employeeList2.remove("Venkatesh")
        employeeList2.add("Jagu")

        employeeList2.forEach {
            Log.i(TAG, "Updated Employee Name: $it")
        }

        val employeeDepartment = setOf(
            "Android",
            "IOS",
            "Web",
            "UI",
            "QC",
            "IOS",
            "Android"
        )
        employeeDepartment.forEach {
            Log.i(TAG, "Employee Department: $it")
        }

        val employeesMap = mapOf(
            1 to "Jagu",
            2 to "Venkata",
            3 to "Ramana",
            4 to "Devi",
            5 to "Siri"
        )
        employeesMap.forEach {
            Log.i(TAG, "employeesMap: $it")
        }
        employeesMap.forEach { (id, name) ->
            Log.i(TAG, "Id: $id Name: $name")
        }
        val listofEmployees = listOf<Employee>(
            Employee(1, "Venkatesh", 13, 50000, "Android"),
            Employee(2, "Ravinder", 15, 60000, "Web"),
            Employee(3, "Raju", 12, 55000, "IOS"),
            Employee(4, "Sridhar", 16, 55000, "UI"),
            Employee(5, "Krishna", 14, 50000, "QC"),
            Employee(6, "Jagu", 14, 50000, "Android")
        )

        val employee = listofEmployees.filter {
            it.department == "Android"
        }
        employee.forEach {
            Log.i(TAG, "employee name filter by department Android: ${it.name}")
        }
        listofEmployees.map {
            Log.i(TAG, "map employee Name: ${it.name}")
        }

        val employee1 = listofEmployees.find {
            it.id == 4
        }
        Log.i(TAG, "Employee by find by id4 $employee1")
        val employee2 = listofEmployees.firstOrNull {
            it.salary > 60000
        }
        Log.i(TAG, "Employee by firstOrNull $employee2")

        val check1 = listofEmployees.any {
            it.experience > 15
        }
        Log.i(
            TAG,
            "Employee any one experience more than 15 years by any(true/false): $check1"
        )
        //Need to check the difference check1 and check2
        val check2 = listofEmployees.any()
        Log.i(
            TAG,
            "Employee any one experience more than 15 years by any (true/false): $check2"
        )

        val check3 = listofEmployees.all {
            it.experience > 15
        }
        Log.i(TAG, "Employee any one experience more than 15 years by all  $check3")

        val count = listofEmployees.count {
            it.department == "Android"
        }
        Log.i(TAG, "Android employees count $count")

        val sortEmpAscending = listofEmployees.sortedBy {
            it.salary
        }
        Log.i(TAG, "List of employees sort by salary Ascending :$sortEmpAscending")
        val sortEmpDesending = listofEmployees.sortedByDescending {
            it.salary
        }
        Log.i(TAG, "List of employees sort by salary Desending: $sortEmpDesending")

        val empGroup = listofEmployees.groupBy {
            it.department
        }
        Log.i(TAG, "List of employees sort by Group $empGroup")

        empGroup.forEach { (department, employees) ->

            Log.i(TAG, "Department: $department")

            employees.forEach {
                Log.i(TAG, it.name)
            }

        }
    }

    companion object {
        private const val TAG = "Lesson5Activity"
    }
}

data class Employee(
    val id: Int,
    val name: String,
    val experience: Int,
    val salary: Int,
    val department: String
)
