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
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class NewAppointmentActivity : AppCompatActivity(), OnItemSelectedListener {
    private lateinit var animals: ArrayList<Animal>
    private lateinit var appointments: ArrayList<Appointment>
    private lateinit var veterinarians: ArrayList<Veterinarian>
    private var selectedAnimal: Animal? = null
    private var selectedVeterinarian: Veterinarian? = null
    private var selectedDate: LocalDate? = null
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

        setUpAppointmentButton.isEnabled = canSetUpNewAppointment()
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
        return Appointment(
            getDateTime(),
            selectedAnimal!!,
            selectedVeterinarian!!,
            reasonEt.text.toString()
        )
    }

    private fun getDateTime(): LocalDateTime {
        return LocalDateTime.of(
            selectedDate,
            LocalTime.of(appointmentTp.hour, appointmentTp.minute)
        )
    }

    private fun initializeFields() {
        appointmentTp = findViewById(R.id.newAppointment_tp_appointmentTime)
        reasonEt = findViewById(R.id.newAppointment_et_appointmentReason)
        animalSp = findViewById(R.id.newAppointment_sp_animalList)
        veterinarianSp = findViewById(R.id.newAppointment_sp_veterinarianList)
        setUpAppointmentButton = findViewById(R.id.newAppointment_bt_setUpAppointment)

        intent.extras?.apply {
            animals = get(Constants.animalsKey) as ArrayList<Animal>
            appointments = get(Constants.appointmentsKey) as ArrayList<Appointment>
            veterinarians = get(Constants.veterinariansKey) as ArrayList<Veterinarian>

            val (year, month, day) = get("selectedDate") as Triple<Int, Int, Int>
            selectedDate = LocalDate.of(year, month, day)

            populateAnimalSpinner()
        }
    }

    private fun populateAnimalSpinner() {
        animalSp.adapter = AnimalAdapter(this, animals)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position > 0) {
            when (parent?.id) {
                animalSp.id -> handleOnAnimalSelected(position)
                veterinarianSp.id -> handleOnVeterinarianSelected(position)
            }
        }
    }

    private fun handleOnAnimalSelected(position: Int) {
        selectedAnimal = animals[position - 1]
        populateVeterinarianSpinner()
    }

    private fun handleOnVeterinarianSelected(position: Int) {
        selectedVeterinarian = veterinarians[position - 1]
    }

    private fun populateVeterinarianSpinner() {
        val filteredVets = selectedAnimal?.let {
            veterinarians.filter { vet -> vet.canExamine(it) && hasDailyLimitRemaining(vet) }
        } ?: ArrayList()

        veterinarianSp.adapter = VeterinarianAdapter(this, filteredVets)
    }

    private fun hasDailyLimitRemaining(veterinarian: Veterinarian): Boolean {
        return !veterinarian.hasDailyLimit()
                || getVetDateAppointmentCount(veterinarian) < veterinarian.dailyLimit!!
    }

    private fun getVetDateAppointmentCount(veterinarian: Veterinarian): Int {
        return appointments.count { a ->
            a.veterinarian == veterinarian && a.dateTime.toLocalDate().equals(selectedDate)
        }
    }

    private fun canSetUpNewAppointment(): Boolean {
        return getDateTotalAppointmentCount() < maxDailyAppointments
    }

    private fun getDateTotalAppointmentCount(): Int {
        return appointments.count { a ->
            a.dateTime.toLocalDate().equals(selectedDate)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    companion object {
        const val maxDailyAppointments = 5
    }
}