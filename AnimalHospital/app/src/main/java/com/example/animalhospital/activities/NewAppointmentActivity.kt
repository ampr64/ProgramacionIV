package com.example.animalhospital.activities

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import com.example.animalhospital.R
import com.example.animalhospital.adapters.AnimalAdapter
import com.example.animalhospital.adapters.VeterinarianAdapter
import com.example.animalhospital.constants.Constants
import com.example.animalhospital.models.Animal
import com.example.animalhospital.models.Appointment
import com.example.animalhospital.models.ObjectResult
import com.example.animalhospital.models.Veterinarian

class NewAppointmentActivity : AppCompatActivity(), OnItemSelectedListener {
    private lateinit var animals: ArrayList<Animal>
    private lateinit var appointments: ArrayList<Appointment>
    private lateinit var veterinarians: ArrayList<Veterinarian>
    private var selectedAnimal: Animal? = null
    private var selectedVeterinarian: Veterinarian? = null
    private var selectedDay: Int? = null
    private var selectedMonth: Int? = null
    private var selectedYear: Int? = null
    private lateinit var appointmentTp: TimePicker
    private lateinit var reasonEt: EditText
    private lateinit var animalSp: Spinner
    private lateinit var veterinarianSp: Spinner
    private lateinit var setUpAppointmentButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_appointment)

        initializeFields()

        appointmentTp.setIs24HourView(true)

        animalSp.onItemSelectedListener = this
        veterinarianSp.onItemSelectedListener = this
        setUpAppointmentButton.setOnClickListener {
            handleOnSetAppointmentClick()
        }
    }

    private fun handleOnSetAppointmentClick() {
        val newAppointment = getAppointmentFromView()
        val result = ObjectResult.ok(newAppointment)

        intent?.let {
            it.putExtra(Constants.newAppointmentKey, result)
            setResult(Activity.RESULT_OK, it)
        }

        finish()
    }

    private fun getAppointmentFromView(): Appointment {
        val hour = appointmentTp.hour

        return Appointment(
            hour,
            selectedDay!!,
            selectedMonth!!,
            selectedYear!!,
            selectedAnimal!!,
            selectedVeterinarian!!,
            reasonEt.text.toString()
        )
    }

    private fun initializeFields() {
        appointmentTp = findViewById(R.id.newAppointment_tp_appointmentTime)
        reasonEt = findViewById(R.id.newAppointment_et_appointmentReason)
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
        }
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
        when (parent?.id) {
            animalSp.id -> handleOnAnimalSelected(position)
            veterinarianSp.id -> handleOnVeterinarianSelected(position)
        }
    }

    private fun handleOnAnimalSelected(position: Int) {
        if (position != 0) {
            selectedAnimal = animals[position - 1]
            populateVeterinarianSpinner()
        }
    }

    private fun handleOnVeterinarianSelected(position: Int) {
        if (position != 0) {
            selectedVeterinarian = veterinarians[position - 1]
        }
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