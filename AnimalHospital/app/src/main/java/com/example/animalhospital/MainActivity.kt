package com.example.animalhospital

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun navigateToAnimalSignup(view: View) {
        val intent = Intent(this, AnimalSignup::class.java)
        startActivity(intent)
    }
}