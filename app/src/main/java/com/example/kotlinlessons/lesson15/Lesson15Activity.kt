package com.example.kotlinlessons.lesson15

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.kotlinlessons.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class Lesson15Activity : AppCompatActivity() {

    private val _paymentStatus = MutableStateFlow("PENDING")
    val paymentStatus: StateFlow<String> = _paymentStatus.asStateFlow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lesson15)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            getPaymentStatus().collect { status ->
                Log.i(TAG, "Payment status: $status")
            }
        }

        handlePaymentStateFlow()
        handleCombineBills()
        handleMapAndFilter()
    }

    private fun handleMapAndFilter() {
        val amountsFlow = flowOf(500.0, 1000.0, 1500.0, 2000.0)

        lifecycleScope.launch {
            amountsFlow
                .filter { it > 1000.0 }
                .map { "₹$it" }
                .collect { formattedAmount ->
                    Log.i(TAG, "Filtered & mapped bill: $formattedAmount")
                }
        }
    }

    private fun handleCombineBills() {
        val electricityBill = MutableStateFlow(1500)
        val waterBill = MutableStateFlow(500)

        lifecycleScope.launch {
            electricityBill.combine(waterBill) { electricity, water ->
                electricity + water
            }.collect { total ->
                Log.i(TAG, "Total bill: ₹$total")
            }
        }

        lifecycleScope.launch {
            delay(2000)
            electricityBill.value = 2000
        }
    }

    private fun handlePaymentStateFlow() {
        lifecycleScope.launch {
            paymentStatus.collect { status ->
                Log.i(TAG, "StateFlow payment status: $status")
            }
        }

        lifecycleScope.launch {
            delay(2000)
            _paymentStatus.value = "SUCCESS"
            delay(2000)
            _paymentStatus.value = "FAILED"
        }
    }

    fun getPaymentStatus(): Flow<String> = flow {
        emit("PENDING")
        delay(2000)
        emit("PROCESSING")
        delay(2000)
        emit("SUCCESS")
    }

    companion object {
        const val TAG = "Lesson15Activity"
    }
}