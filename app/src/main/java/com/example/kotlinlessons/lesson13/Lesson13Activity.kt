package com.example.kotlinlessons.lesson13

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinlessons.R

import kotlin.properties.Delegates

class Lesson13Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lesson13)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val electricityPayment = ElectricityPayment(paymentProcessor)
        electricityPayment.onPaymentSuccess()
        electricityPayment.onPaymentFailure()

        paymentStatus = "SUCCESS"
        paymentStatus = "FAILED"
    }

    private val paymentProcessor: BillPaymentProcessor by lazy {
        Log.i(TAG, "Initializing BillPaymentProcessor lazily")
        BillPaymentProcessor()
    }

    private var paymentStatus: String by Delegates.observable("PENDING") { _, oldValue, newValue ->
        Log.i(TAG, "Payment status changed from $oldValue to $newValue")
    }

    interface BillPaymentListener {
        fun onPaymentSuccess()
        fun onPaymentFailure()
    }

    class BillPaymentProcessor : BillPaymentListener {
        override fun onPaymentSuccess() {
            Log.i(TAG, "Success")
        }

        override fun onPaymentFailure() {
            Log.i(TAG, "Failure")
        }
    }

    class ElectricityPayment(paymentProcessor: BillPaymentListener = BillPaymentProcessor()) : BillPaymentListener by paymentProcessor

    companion object {
        private const val TAG = "Lesson13Activity"
    }

}