package com.example.animalhospital.models

class Animal(val name: String, val type: AnimalType, val breed: String, var age: Int) {
}

enum class AnimalType(private val displayName: String) {
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