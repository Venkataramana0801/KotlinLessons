package com.example.kotlinlessons.lesson11

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinlessons.R

class Lesson11Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_lesson11)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val payment = Payment()
        payment.onPaymentSuccess()
        payment.onPaymentFailure()

        val electricityBillProcessor = ElectricityBillProcessor("Venkata Ramana")
        electricityBillProcessor.displayCustomer()
        electricityBillProcessor.processBill(100.0)
    }

    interface BillPaymentListener {
        fun onPaymentSuccess()
        fun onPaymentFailure()
    }

    abstract class BillProcessor(val customerName: String) {
        abstract fun processBill(amount: Double)
        fun displayCustomer() {
            Log.i(TAG, "Customer Name is $customerName")
        }
    }

    class Payment : BillPaymentListener {
        override fun onPaymentSuccess() {
            Log.i(TAG, "Payment Success")
        }

        override fun onPaymentFailure() {
            Log.i(TAG, "Payment Failure")
        }
    }

    class ElectricityBillProcessor(customerName: String) : BillProcessor(customerName) {
        override fun processBill(amount: Double) {
            Log.i(TAG, "Electricity Bill for $customerName is $amount")
        }
    }

    companion object {
        private const val TAG = "Lesson11Activity"
    }
}