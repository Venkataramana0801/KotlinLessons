package com.example.kotlinlessons.lesson16

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.kotlinlessons.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Lesson16Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lesson16)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        try {
            100/0
        }catch (e: ArithmeticException){
            Log.i(TAG,e.toString())
        }finally {
            Log.i(TAG,"Process completed")
        }

        try {
            validateBillAmount(-25.0)
        } catch (e: IllegalArgumentException) {
            Log.e(TAG, "Caught exception: ${e.message}")
        }

        try {
            validateBillAmountCustom(-15.0)
        } catch (e: InvalidBillAmountException) {
            Log.e(TAG, "Caught custom exception: ${e.message}")
        }

        runCatching {
            processPayment(1500.0)
        }.onSuccess { result ->
            Log.i(TAG, "Payment Success: $result")
        }.onFailure { error ->
            Log.e(TAG, "Payment Failure: ${error.message}")
        }

        val validNumber = "2500".toIntOrNull()
        val invalidNumber = "ABC".toIntOrNull()

        Log.i(TAG, "Result of '2500'.toIntOrNull(): $validNumber")
        Log.i(TAG, "Result of 'ABC'.toIntOrNull(): $invalidNumber")

        lifecycleScope.launch {
            try {
                val bill = fetchElectricityBill()
                Log.i(TAG, "Electricity bill fetched: ₹$bill")
            } catch (e: Exception) {
                Log.e(TAG, "Failed to fetch electricity bill: ${e.message}")
            }
        }
    }

    suspend fun fetchElectricityBill(): Double {
        delay(1000)
        // Simulating a network failure
        throw Exception("Server error: Unable to fetch electricity bill")
    }

    fun processPayment(amount: Double): String {
        validateBillAmount(amount)
        return "₹$amount"
    }

    fun validateBillAmount(amount: Double) {
        if (amount <= 0.0) {
            throw IllegalArgumentException("Bill amount must be greater than zero. Provided: $amount")
        }
    }

    fun validateBillAmountCustom(amount: Double) {
        if (amount <= 0.0) {
            throw InvalidBillAmountException("Bill amount must be greater than zero. Provided: $amount")
        }
    }

    companion object {
        const val TAG = "Lesson16Activity"
    }
}

class InvalidBillAmountException(message: String) : Exception(message)
