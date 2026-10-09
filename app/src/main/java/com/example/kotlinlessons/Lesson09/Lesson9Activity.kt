package com.example.kotlinlessons.Lesson09

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinlessons.R

class Lesson9Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_lesson9)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        EmployeeUtils.welcome("Venkata Ramana")
        val bonus = EmployeeUtils.calculateBonus(50000)
        Log.i(TAG, "Bonus is $bonus")


        Log.i(TAG, "Comapny ${Employee.COMPANY}")
        Log.i(TAG, "Company ${Employee.companyName()}")

        Log.i(TAG, "Company URL ${AppConfig.serviceUrl}")
        AppConfig.printConfig()

        val listener = object : PaymentListener {
            override fun onPaymentSuccess() {
                Log.i(TAG, "Payment Success")
            }

            override fun onPaymentFailure() {
                Log.i(TAG, "Payment Failure")
            }
        }

        listener.onPaymentSuccess()
        listener.onPaymentFailure()
    }

    object EmployeeUtils {
        fun welcome(name: String) {
            Log.i(TAG, "Welcome $name")
        }

        fun calculateBonus(salary: Int): Int = salary * 10 / 100
    }

    companion object {
        private const val TAG = "Lesson9Activity"
    }

    class Employee(
        val name: String,
        val salary: Int
    ) {
        companion object {
            const val COMPANY = "ABC Technologies"

            fun companyName(): String {
                // Return the company name
                return COMPANY
            }
        }
    }


    object AppConfig {

        const val APP_NAME = "Utility Billing"

        var serviceUrl = "https://example.com"

        fun printConfig() {
            Log.i(TAG, "App: $APP_NAME")
            Log.i(TAG, "Service URL: $serviceUrl")
        }
    }

    interface PaymentListener {
        fun onPaymentSuccess()
        fun onPaymentFailure()
    }
}