package com.example.tp1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun switchToCheckPalindrome(view: View) {
        val intent = Intent(this, PalindromeCheckerActivity::class.java)
        startActivity(intent)
    }

    fun switchToTemperatureConverter(view: View) {
        val intent = Intent(this, TemperatureConverterActivity::class.java)
        startActivity(intent)
    }
}