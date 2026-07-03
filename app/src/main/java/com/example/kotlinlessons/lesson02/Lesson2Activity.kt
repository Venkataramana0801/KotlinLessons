package com.example.kotlinlessons.lesson02

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinlessons.R

class Lesson2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lesson2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        displayEmployee("Venkata Ramana", 13, "ABC Technology")
        val bonus = calculateBonus(50000, 10)
        Log.i(TAG, "Employee Bonus: $bonus")
        val bonus2 = calculateBonusSingleExpressionFunction(50000, 20)
        Log.i(TAG, "Employee Bonus: $bonus2")
        showMessage("Title")
        showMessage("Title", "Welcome to the Kotlin")
        val squareResult = square(10)
        Log.i(TAG, "Sqaure: $squareResult")
        greetings("Kotlin")
    }

    private fun displayEmployee(
        name: String,
        experience: Int,
        company: String
    ) {
        Log.i(TAG, "Employee Name: $name")
        Log.i(TAG, "Employee Experince: $experience")
        Log.i(TAG, "Employee Company: $company")
    }

    private fun calculateBonus(salary: Int, bonusPercentage: Int): Int {
        return salary * bonusPercentage / 100
    }

    private fun calculateBonusSingleExpressionFunction(salary: Int, bonusPercentage: Int) =
        salary * bonusPercentage / 100


    private fun showMessage(
        title: String,
        message: String = "Welcome"
    ) {
        Log.i(TAG, "Title: $title and $message")
    }

    private fun square(a: Int) = a * a

    private companion object {
        const val TAG = "MainActivity"
    }
}

private fun greetings(greeting: String) {
    Log.i("Welcome", "Top level function: $greeting")
}