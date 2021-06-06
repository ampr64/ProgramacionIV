package com.example.animalhospital.constants

import android.graphics.Color
import android.graphics.Color.rgb

class Constants {
    companion object {
        const val animalsKey = "animals"
        const val newAnimalKey = "newAnimal"
        const val newAppointmentKey = "newAppointment"
        const val veterinariansKey = "veterinarians"
        val textColorSuccess = rgb(0, 153, 51)
        val backgroundColorSuccess = Color.rgb(0, 153, 51)
        const val textColorWithBackgroundSuccess = Color.WHITE
        const val textColorError = Color.RED
        const val textColorWithBackgroundError = Color.WHITE
        const val backgroundColorError = Color.RED
    }
}