package com.example.animalhospital.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.example.animalhospital.R
import com.example.animalhospital.constants.Constants
import com.example.animalhospital.contracts.NewAppointmentContract
import com.example.animalhospital.contracts.NewExaminationContract
import com.example.animalhospital.contracts.SignupAnimalContract
import com.example.animalhospital.models.*
import com.example.animalhospital.utils.Util
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val animals: ArrayList<Animal> = arrayListOf(
        Animal("Mooney", AnimalType.DOG, "Mix", 4),
        Animal("Afro", AnimalType.CAT, "Tabby", 3)
    )
    private val appointments = ArrayList<Appointment>()
    private val examinations = ArrayList<Examination>()
    private val veterinarians = arrayListOf(
        Veterinarian("Peter", arrayListOf(AnimalType.DOG), 3),
        Veterinarian("John", arrayListOf(AnimalType.BUNNY, AnimalType.CAT), 2)
    )
    private lateinit var signupLauncher: ActivityResultLauncher<Intent>
    private lateinit var newAppointmentLauncher: ActivityResultLauncher<Intent>
    private lateinit var newExaminationLauncher: ActivityResultLauncher<Intent>
    private lateinit var appointmentCalendarView: CalendarView
    private lateinit var resultTv: TextView
    private lateinit var signUpNavigationButton: Button
    private lateinit var newExaminationButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeFields()
        setListeners()
        disableCalendarPastDates()

        signupLauncher = registerForActivityResult(
            SignupAnimalContract()
        ) { result ->
            handleOnSignupResult(result)
        }

        newAppointmentLauncher = registerForActivityResult(
            NewAppointmentContract()
        ) { result ->
            handleOnNewAppointmentResult(result)
        }

        newExaminationLauncher = registerForActivityResult(
            NewExaminationContract()
        ) { result ->
            handleOnNewExaminationResult(result)
        }
    }

    private fun initializeFields() {
        appointmentCalendarView = findViewById(R.id.main_cv_appointmentCalendar)
        resultTv = findViewById(R.id.main_tv_result)
        signUpNavigationButton = findViewById(R.id.main_btn_signUp)
        newExaminationButton = findViewById(R.id.main_btn_newExamination)
    }

    private fun setListeners() {
        signUpNavigationButton.setOnClickListener {
            handleOnAnimalSignupClick()
        }

        newExaminationButton.setOnClickListener {
            handleOnNewExaminationClick()
        }

        appointmentCalendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            handleOnCalendarDateChange(year, month, dayOfMonth)
        }
    }

    private fun disableCalendarPastDates() {
        appointmentCalendarView.minDate = Calendar.getInstance().timeInMillis
    }

    private fun handleOnAnimalSignupClick() {
        Intent(this, AnimalSignupActivity::class.java).also {
            signupLauncher.launch(it)
        }
    }

    private fun handleOnSignupResult(result: ObjectResult<Animal?>) {
        var successMessage: String? = null
        if (result.success) {
            (result.obj)?.also {
                animals.add(it)
                successMessage = "${it.name} has been signed up successfully!"
            }
        }

        Util.displayResultMessage(resultTv, result, successMessage = successMessage)
    }

    private fun handleOnNewExaminationClick() {
        Intent(this, NewExaminationActivity::class.java).let {
            it.putExtra(Constants.KEY_APPOINTMENTS, appointments)

            newExaminationLauncher.launch(it)
        }
    }

    private fun handleOnNewExaminationResult(result: ObjectResult<Examination?>) {
        if (result.success) {
            (result.obj)?.also {
                examinations.add(it)
            }
        }

        Util.displayResultMessage(resultTv, result)
    }

    private fun handleOnCalendarDateChange(year: Int, month: Int, dayOfMonth: Int) {
        Intent(applicationContext, NewAppointmentActivity::class.java).apply {
            putExtra(Constants.KEY_APPOINTMENT_DATE, Triple(year, month + 1, dayOfMonth))
            putExtra(Constants.KEY_ANIMALS, animals)
            putExtra(Constants.KEY_APPOINTMENTS, appointments)
            putExtra(Constants.KEY_VETERINARIANS, veterinarians)

            newAppointmentLauncher.launch(this)
        }
    }

    private fun handleOnNewAppointmentResult(result: ObjectResult<Appointment?>) {
        if (result.success) {
            (result.obj)?.also {
                appointments.add(it)
            }
        }

        Util.displayResultMessage(resultTv, result)
    }
}