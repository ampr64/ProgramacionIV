package com.example.animalhospital.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.animalhospital.R
import com.example.animalhospital.adapters.AppointmentAdapter
import com.example.animalhospital.constants.Constants
import com.example.animalhospital.models.Appointment
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class NewExaminationActivity : AppCompatActivity() {
    private lateinit var appointments: ArrayList<Appointment>
    private lateinit var appointmentSp: Spinner
    private lateinit var diagnosisEt: EditText
    private lateinit var diagnosisTv: TextView
    private lateinit var medicineEt: EditText
    private lateinit var medicineTv: TextView
    private lateinit var treatmentEt: EditText
    private lateinit var treatmentTv: TextView
    private lateinit var restDaysEt: EditText
    private lateinit var restDaysTv: TextView
    private lateinit var saveButton: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_examination)

        initializeFields()
    }

    private fun initializeFields() {
        intent.extras?.let {
            appointments = it.get(Constants.appointmentsKey) as ArrayList<Appointment>
            appointmentSp = findViewById(R.id.newExamination_sp_appointment)

            populateAppointmentSpinner()
        }

        diagnosisEt = findViewById(R.id.newExamination_et_diagnosis)
        diagnosisTv = findViewById(R.id.newExamination_tv_diagnosis)
        medicineEt = findViewById(R.id.newExamination_et_medicine)
        medicineTv = findViewById(R.id.newExamination_tv_medicine)
        treatmentEt = findViewById(R.id.newExamination_et_treatment)
        treatmentTv = findViewById(R.id.newExamination_tv_treatment)
        restDaysEt = findViewById(R.id.newExamination_et_rest_days)
        restDaysTv = findViewById(R.id.newExamination_tv_rest_days)
        saveButton = findViewById(R.id.newExamination_bt_save)
    }

    private fun populateAppointmentSpinner() {
        val filteredAppointments = getAppointments()
        appointmentSp.adapter = AppointmentAdapter(this, filteredAppointments)
    }

    private fun getAppointments(): ArrayList<Appointment> {
        return appointments.filter { x -> x.isExaminationPending && isToday(x) } as ArrayList<Appointment>
    }

    @SuppressLint("NewApi")
    private fun isToday(appointment: Appointment): Boolean {
        val today = LocalDate.now()
        val appointmentDate = LocalDate.of(appointment.year, appointment.month, appointment.day)

        return ChronoUnit.DAYS.between(today, appointmentDate).toInt() == 0
    }
}