package com.example.animalhospital.model

import java.io.Serializable

data class Animal(
        val name: String,
        val type: AnimalType,
        val breed: String,
        var age: Int
) : Serializable {
}

enum class AnimalType(private val displayName: String) : Serializable {
    CAT("Cat"),
    DOG("Dog"),
    BUNNY("Bunny");

    companion object {
        fun getByDisplayName(displayName: String): AnimalType {
            return AnimalType.valueOf(displayName.uppercase())
        }
    }

    override fun toString(): String {
        return displayName
    }
}