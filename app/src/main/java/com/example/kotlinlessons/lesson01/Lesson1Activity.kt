package com.example.kotlinlessons.lesson01

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinlessons.R

class Lesson1Activity : AppCompatActivity() {
    private val name = "Venkata Ramana"
    var name1 = ""
    var name2: String =""
    var name3: String? = null
    private val experience = 13
    private var experience1 = 0
    private var experience2: Int = 0

    private val company = "IT Solutions"
    private val isAndroidDeveloper = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lesson1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var salary = 50000

        Log.i("Name :", "$name")
        Log.i("Experience :", "$experience")
        Log.i("Company :", "$company")
        Log.i("IsAndroidDeveloper :", "$isAndroidDeveloper")
        Log.i("Before salary :", "$salary")
        salary = 60000
        Log.i("After salary :", "$salary")

        name1="Name1"
        name2="Name2"
        name3="Name3"

        experience1=10
        experience2=15
    }
}