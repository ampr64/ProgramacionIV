package com.example.animalhospital.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Button
import android.widget.Spinner
import android.widget.TimePicker
import com.example.animalhospital.R
import com.example.animalhospital.adapters.AnimalAdapter
import com.example.animalhospital.adapters.VeterinarianAdapter
import com.example.animalhospital.constants.Constants
import com.example.animalhospital.models.Animal
import com.example.animalhospital.models.Appointment
import com.example.animalhospital.models.Veterinarian
import kotlin.collections.ArrayList

class NewAppointmentActivity : AppCompatActivity(), OnItemSelectedListener {
    private lateinit var animals: ArrayList<Animal>
    private lateinit var appointments: ArrayList<Appointment>
    private lateinit var veterinarians: ArrayList<Veterinarian>
    private var selectedAnimal: Animal? = null
    private var selectedDay: Int? = null
    private var selectedMonth: Int? = null
    private var selectedYear: Int? = null
    private lateinit var appointmentTime: TimePicker
    private lateinit var animalSp: Spinner
    private lateinit var veterinarianSp: Spinner
    private lateinit var setUpAppointmentButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_appointment)

        initializeFields()

        animalSp.onItemSelectedListener = this
    }

    private fun initializeFields() {
        appointmentTime = findViewById(R.id.newAppointment_tp_appointmentTime)
        animalSp = findViewById(R.id.newAppointment_sp_animalList)
        veterinarianSp = findViewById(R.id.newAppointment_sp_veterinarianList)
        setUpAppointmentButton = findViewById(R.id.newAppointment_bt_setUpAppointment)

        intent.extras?.let {
            animals = it.get(Constants.animalsKey) as ArrayList<Animal>
            appointments = it.get(Constants.appointmentsKey) as ArrayList<Appointment>
            veterinarians = it.get(Constants.veterinariansKey) as ArrayList<Veterinarian>
            val (selectedYear, selectedMonth, selectedDay) = it.get("selectedDate") as Triple<Int, Int, Int>
            this.selectedYear = selectedYear
            this.selectedMonth = selectedMonth
            this.selectedDay = selectedDay
            populateAnimalSpinner()
        } ?: return
    }

    private fun populateAnimalSpinner() {
        animalSp.adapter = AnimalAdapter(this, animals)
    }

    private fun hasDailyLimitRemaining(veterinarian: Veterinarian): Boolean {
        return veterinarian.hasDailyLimit()
                && getTodayAppointmentCount(veterinarian) < veterinarian.dailyLimit!!
    }

    private fun getTodayAppointmentCount(veterinarian: Veterinarian): Int {
        return appointments.count { a ->
            a.day == selectedDay
                    && a.month == selectedMonth
                    && a.year == selectedYear
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position != 0) {
            selectedAnimal = animals[position - 1]
            handleOnSelectedAnimalChanged()
        }
    }

    private fun handleOnSelectedAnimalChanged() {
        populateVeterinarianSpinner()
    }

    private fun populateVeterinarianSpinner() {
        val filteredVets = selectedAnimal?.let {
            veterinarians.filter { vet -> vet.canExamine(it) && hasDailyLimitRemaining(vet) }
        } ?: ArrayList()

        veterinarianSp.adapter = VeterinarianAdapter(this, filteredVets)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}