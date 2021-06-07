package com.example.animalhospital.models

import java.io.Serializable

class Appointment(
    val hour: Int,
    val day: Int,
    val month: Int,
    val year: Int,
    val animal: Animal,
    val veterinarian: Veterinarian,
    val reason: String
) : Serializable {
}