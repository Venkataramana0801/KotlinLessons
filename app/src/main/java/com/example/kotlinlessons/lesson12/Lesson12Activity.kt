package com.example.kotlinlessons.lesson12

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinlessons.R

class Lesson12Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lesson12)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val customerName = BillData<String>("Venkata Ramana")
        val amount = BillData<Double>(100.0)
        val billsPending = BillData<Int>(3)

        Log.i(TAG, "Customer name is ${customerName.data}")
        Log.i(TAG, "Amount is ${amount.data}")
        Log.i(TAG, "Bills pending is ${billsPending.data}")

        val firstBillType = getFirstItem(
            listOf("Electricity", "Gas", "Water")
        )
        val firstAmount = getFirstItem(
            listOf(1000.0, 2000.0, 3000.0)
        )

        Log.i(TAG, "First Bill Type is $firstBillType")
        Log.i(TAG, "First Amount is $firstAmount")

        val electricityBill = ElectricityBill(100.0, 5.0)
        printBillAmount(electricityBill)
    }

    interface Billable {
        fun getAmount(): Double
    }

    class ElectricityBill(val units: Double, val ratePerUnit: Double = 1.0) : Billable {
        override fun getAmount(): Double {
            return units * ratePerUnit
        }
    }

    class BillData<T>(val data: T)

    companion object {
        private const val TAG = "Lesson12Activity"
    }

    fun <T> getFirstItem(items: List<T>): T?{
        return items.firstOrNull()
    }

    fun <T : Billable> printBillAmount(bill: T) {
        Log.i(TAG, "Bill amount is ${bill.getAmount()}")
    }

}