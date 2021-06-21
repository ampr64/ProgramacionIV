package com.example.animalhospital.model

import java.io.Serializable

class Veterinarian(
    val name: String,
    val permittedAnimalTypes: ArrayList<AnimalType>,
    val dailyLimit: Int? = null
) : Serializable {
    fun canExamine(animal: Animal): Boolean {
        return animal.type in permittedAnimalTypes
    }

    fun hasDailyLimit(): Boolean {
        return dailyLimit != null
    }
}