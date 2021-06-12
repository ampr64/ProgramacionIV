package com.example.animalhospital.activities

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.animalhospital.R
import com.example.animalhospital.adapters.AppointmentAdapter
import com.example.animalhospital.constants.Constants
import com.example.animalhospital.models.Appointment
import com.example.animalhospital.models.Examination
import com.example.animalhospital.models.ObjectResult

class NewExaminationActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var appointments: ArrayList<Appointment>
    private var selectedAppointment: Appointment? = null
    private lateinit var appointmentSp: Spinner
    private lateinit var diagnosisEt: EditText
    private lateinit var diagnosisTv: TextView
    private lateinit var medicineEt: EditText
    private lateinit var medicineTv: TextView
    private lateinit var treatmentEt: EditText
    private lateinit var treatmentTv: TextView
    private lateinit var restDaysEt: EditText
    private lateinit var restDaysTv: TextView
    private lateinit var saveButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_examination)

        initializeFields()

        appointmentSp.onItemSelectedListener = this

        saveButton.setOnClickListener {
            handleOnSaveButtonClick()
        }
    }

    private fun handleOnSaveButtonClick() {
        val newExamination = getExaminationFromView()
        val result = ObjectResult.ok(newExamination)

        intent?.apply {
            putExtra(Constants.newExaminationKey, result)
            setResult(Activity.RESULT_OK, this)
        }

        finish()
    }

    private fun getExaminationFromView(): Examination {
        val restDays = if (restDaysEt.text.isBlank()) 0 else restDaysEt.text.toString().toInt()

        return Examination(
            diagnosisEt.text.toString(),
            selectedAppointment!!,
            medicineEt.text.toString(),
            treatmentEt.text.toString(),
            restDays
        )
    }

    private fun initializeFields() {
        intent.extras?.apply {
            appointments = get(Constants.appointmentsKey) as ArrayList<Appointment>
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
        getAppointments().also {
            appointmentSp.adapter = AppointmentAdapter(this, it)
        }
    }

    private fun getAppointments(): ArrayList<Appointment> {
        return appointments.filter { a -> a.isExaminationPending && a.isToday() } as ArrayList<Appointment>
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            appointmentSp.id -> handleOnAppointmentSelected(position)
        }
    }

    private fun handleOnAppointmentSelected(position: Int) {
        if (position > 0) {
            getAppointments().let {
                selectedAppointment = it[position - 1]
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}