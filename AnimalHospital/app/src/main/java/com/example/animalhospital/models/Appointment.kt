package com.example.animalhospital.models

import java.io.Serializable

class Appointment(
    val time: Long,
    val animal: Animal,
    val veterinarian: Veterinarian,
    val reason: String
) : Serializable {
}