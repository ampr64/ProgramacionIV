package com.example.animalhospital.models

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

class Appointment(
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