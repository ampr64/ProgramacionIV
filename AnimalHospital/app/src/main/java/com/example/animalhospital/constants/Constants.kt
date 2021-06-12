package com.example.animalhospital.constants

import android.graphics.Color
import android.graphics.Color.rgb

class Constants {
    companion object {
        const val KEY_ANIMALS = "animals"
        const val KEY_NEW_ANIMAL = "newAnimal"
        const val KEY_APPOINTMENTS = "appointments"
        const val KEY_NEW_APPOINTMENT = "newAppointment"
        const val KEY_APPOINTMENT_DATE = "appointmentDate"
        const val KEY_EXAMINATIONS = "examinations"
        const val KEY_NEW_EXAMINATION = "newExamination"
        const val KEY_VETERINARIANS = "veterinarians"
        val textColorSuccess = rgb(0, 153, 51)
        val backgroundColorSuccess = Color.rgb(0, 153, 51)
        const val textColorWithBackgroundSuccess = Color.WHITE
        const val textColorError = Color.RED
        const val textColorWithBackgroundError = Color.WHITE
        const val backgroundColorError = Color.RED
    }
}