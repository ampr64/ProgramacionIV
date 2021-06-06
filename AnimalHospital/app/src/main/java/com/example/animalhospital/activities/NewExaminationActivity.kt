package com.example.animalhospital.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import android.widget.TimePicker
import com.example.animalhospital.R
import com.example.animalhospital.adapters.AnimalAdapter
import com.example.animalhospital.adapters.VeterinarianAdapter
import com.example.animalhospital.constants.Constants
import com.example.animalhospital.models.Animal
import com.example.animalhospital.models.Veterinarian

class NewExaminationActivity : AppCompatActivity() {
    private lateinit var appointmentSp: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_examination)

        initializeFields()
    }

    private fun initializeFields() {
        populateSpinners()
    }

    private fun populateSpinners() {
    }
}