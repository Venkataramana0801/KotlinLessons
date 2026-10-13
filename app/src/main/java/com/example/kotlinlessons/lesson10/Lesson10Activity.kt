package com.example.kotlinlessons.lesson10

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinlessons.R

class Lesson10Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_lesson10)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.i(TAG, "Create Bill Type is ${CreateBillType.Electicity}")
        Log.i(TAG, "Payment Status is ${PaymentStatus.PENDING}")
        Log.i(TAG, "Payment Status is ${displayPaymentStatus(PaymentStatus.SUCCESS)}")

        handleLoginResult(LoginResult.Success("Venkata Ramana"))
        handleLoginResult(LoginResult.Loading)
        handleLoginResult(LoginResult.Failure("Error"))

    }

    enum class CreateBillType {
        Electicity,
        Gas,
        Water
    }

    enum class PaymentStatus {
        PENDING, SUCCESS, FAILED, CANCELLED
    }

    fun displayPaymentStatus(status: PaymentStatus): String {
        return when (status) {
            PaymentStatus.PENDING -> "Payment is pending"
            PaymentStatus.SUCCESS -> "Payment is successful"
            PaymentStatus.FAILED -> "Payment failed"
            PaymentStatus.CANCELLED -> "Payment cancelled"
        }
    }

    sealed class LoginResult {
        data class Success(val username: String) : LoginResult()
        data class Failure(val errorMessage: String) : LoginResult()
        data object Loading : LoginResult()
    }

    fun handleLoginResult(result: LoginResult) {
        when (result) {
            LoginResult.Loading ->{
                Log.i(TAG, "Loading")
            }
            is LoginResult.Success -> {
                Log.i(TAG, "Login successful for ${result.username}")
            }

            is LoginResult.Failure -> {
                Log.i(TAG, "Login failed: ${result.errorMessage}")
            }
        }
    }

    companion object {
        private const val TAG = "Lesson10Activity"
    }
}