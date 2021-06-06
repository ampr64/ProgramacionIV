package com.example.animalhospital.models

import java.io.Serializable

class Veterinarian(
    val name: String,
    val permittedAnimalTypes: List<AnimalType>,
    val dailyLimit: Int? = null
) : Serializable {
    fun canExamine(animal: Animal): Boolean {
        return permittedAnimalTypes.contains(animal.type)
    }

    fun hasDailyLimit(): Boolean {
        return dailyLimit != null
    }
}