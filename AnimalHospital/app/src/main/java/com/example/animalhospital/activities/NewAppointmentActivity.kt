package com.example.animalhospital.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TimePicker
import com.example.animalhospital.R
import com.example.animalhospital.adapters.AnimalAdapter
import com.example.animalhospital.adapters.VeterinarianAdapter
import com.example.animalhospital.constants.Constants
import com.example.animalhospital.models.Animal
import com.example.animalhospital.models.Veterinarian

class NewAppointmentActivity : AppCompatActivity() {
    private lateinit var animals: ArrayList<Animal>
    private lateinit var veterinarians: ArrayList<Veterinarian>
    private lateinit var appointmentTime: TimePicker
    private lateinit var animalSp: Spinner
    private lateinit var veterinarianSp: Spinner
    private lateinit var setUpAppointmentButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_appointment)

        initializeFields()
    }

    private fun initializeFields() {
        appointmentTime = findViewById(R.id.newAppointment_tp_appointmentTime)
        animalSp = findViewById(R.id.newAppointment_sp_animalList)
        veterinarianSp = findViewById(R.id.newAppointment_sp_veterinarianList)
        setUpAppointmentButton = findViewById(R.id.newAppointment_bt_setUpAppointment)

        intent.extras?.let {
            animals = it.get(Constants.animalsKey) as ArrayList<Animal>
            veterinarians = it.get(Constants.veterinariansKey) as ArrayList<Veterinarian>
            populateSpinners()
        }

    }

    private fun populateSpinners() {
        animalSp.adapter = AnimalAdapter(this, animals)
        veterinarianSp.adapter = VeterinarianAdapter(this, veterinarians)
    }
}