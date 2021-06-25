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
import com.example.animalhospital.common.StatefulTextWatcher
import com.example.animalhospital.constants.Constants
import com.example.animalhospital.extensions.EditTextExtensions.Companion.watchNotBlank
import com.example.animalhospital.model.Animal
import com.example.animalhospital.model.Appointment
import com.example.animalhospital.common.ObjectResult
import com.example.animalhospital.model.Veterinarian
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
    private lateinit var reasonTv: TextView
    private lateinit var reasonWatcher: StatefulTextWatcher
    private lateinit var animalSp: Spinner
    private lateinit var veterinarianSp: Spinner
    private lateinit var setUpAppointmentButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_appointment)

        initializeFields()
        setListeners()

        appointmentTp.setIs24HourView(true)

        setSetUpAppointmentState()
    }

    private fun initializeFields() {
        appointmentTp = findViewById(R.id.newAppointment_tp_appointmentTime)
        reasonEt = findViewById(R.id.newAppointment_et_appointmentReason)
        reasonTv = findViewById(R.id.newAppointment_tv_appointmentReason)
        animalSp = findViewById(R.id.newAppointment_sp_animalList)
        veterinarianSp = findViewById(R.id.newAppointment_sp_veterinarianList)
        setUpAppointmentButton = findViewById(R.id.newAppointment_bt_setUpAppointment)

        intent.extras?.apply {
            animals = get(Constants.KEY_ANIMALS) as ArrayList<Animal>
            populateAnimalSpinner()
            populateVeterinarianSpinner()

            appointments = get(Constants.KEY_APPOINTMENTS) as ArrayList<Appointment>
            veterinarians = get(Constants.KEY_VETERINARIANS) as ArrayList<Veterinarian>

            val (year, month, day) = get(Constants.KEY_APPOINTMENT_DATE) as Triple<Int, Int, Int>
            selectedDate = LocalDate.of(year, month, day)
        }
    }

    private fun setListeners() {
        animalSp.onItemSelectedListener = this
        veterinarianSp.onItemSelectedListener = this

        reasonWatcher = reasonEt.watchNotBlank("Reason", reasonTv)

        setUpAppointmentButton.setOnClickListener {
            handleOnSetAppointmentClick()
        }
    }

    override fun onUserInteraction() {
        super.onUserInteraction()

        setSetUpAppointmentState()
    }

    private fun setSetUpAppointmentState() {
        setUpAppointmentButton.isEnabled = canSetUpNewAppointment()
    }

    private fun canSetUpNewAppointment(): Boolean {
        return areSlotsLeft()
                && reasonWatcher.isValidState()
                && selectedAnimal != null
                && selectedVeterinarian != null
    }

    private fun populateAnimalSpinner() {
        animalSp.adapter = AnimalAdapter(this, animals)
    }

    private fun handleOnSetAppointmentClick() {
        val newAppointment = getAppointmentFromView()
        val result = ObjectResult.ok(newAppointment)

        intent?.apply {
            putExtra(Constants.KEY_NEW_APPOINTMENT, result)
            setResult(Activity.RESULT_OK, this)
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

    private fun areSlotsLeft(): Boolean {
        val todayAppointmentsCount = appointments.count { a ->
            a.dateTime.toLocalDate().equals(selectedDate)
        }

        return todayAppointmentsCount < MAX_DAILY_APPOINTMENTS
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    companion object {
        private const val MAX_DAILY_APPOINTMENTS = 5
    }
}