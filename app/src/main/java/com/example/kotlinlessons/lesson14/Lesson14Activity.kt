package com.example.kotlinlessons.lesson14

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.kotlinlessons.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Lesson14Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lesson14)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        CoroutineScope(Dispatchers.Main).launch {
            Log.i(TAG, "Coroutine started")
            delay(2000)
            Log.i(TAG, "Coroutine end")
        }
        lifecycleScope.launch {
            fetchBill()
            val gasBill = fetchGasBill()
            Log.i(TAG, "Gas bill: $gasBill")

            val electricityDeferred = async { fetchElectricityBill() }
            val waterDeferred = async { fetchWaterBill() }

            val electricityBill = electricityDeferred.await()
            val waterBill = waterDeferred.await()

            val totalBill = calculateTotalBill(electricityBill, waterBill)
            Log.i(TAG, "Total bill: $totalBill")
        }

    }

    suspend fun fetchBill() {
        Log.i(TAG, "Bill Coroutine started")
        delay(2000)
        Log.i(TAG, "Bill Coroutine end")
    }

    suspend fun fetchGasBill(): Double {
        return withContext(Dispatchers.IO) {
            delay(3000)
            1200.0
        }
    }

    suspend fun fetchElectricityBill(): Double {
        return withContext(Dispatchers.IO) {
            delay(2000)
            1500.0
        }
    }

    suspend fun fetchWaterBill(): Double {
        return withContext(Dispatchers.IO) {
            delay(2000)
            500.0
        }
    }

    suspend fun calculateTotalBill(vararg bills: Double): Double {
        return withContext(Dispatchers.Default) {
            bills.sum()
        }
    }

    companion object {
        const val TAG = "Lesson14Activity";
    }

}