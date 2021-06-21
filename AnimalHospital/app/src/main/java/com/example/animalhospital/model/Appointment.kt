package com.example.animalhospital.model

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

data class Appointment(
        val dateTime: LocalDateTime,
        val animal: Animal,
        val veterinarian: Veterinarian,
        val reason: String
) : Serializable {
    var isExaminationPending = true

    fun isToday(): Boolean {
        return dateTime.toLocalDate() == LocalDate.now()
    }
}