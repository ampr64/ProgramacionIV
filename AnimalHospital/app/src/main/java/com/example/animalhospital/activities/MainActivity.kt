package com.example.animalhospital.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.animalhospital.R
import com.example.animalhospital.models.Animal

class MainActivity : AppCompatActivity() {
    val animals = ArrayList<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun navigateToAnimalSignup(view: View) {
        val intent = Intent(this, AnimalSignupActivity::class.java)
        intent.putExtra("animals", animals)
        startActivity(intent)
    }
}