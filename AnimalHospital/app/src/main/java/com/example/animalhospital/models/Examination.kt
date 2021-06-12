package com.example.animalhospital.models

import java.io.Serializable

class Examination(
    val diagnosis: String,
    val appointment: Appointment,
    val medicine: String? = null,
    val treatment: String? = null,
    val restDays: Int = 0
) : Serializable {
}