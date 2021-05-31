package com.example.animalhospital.models

class Animal(val name: String, val type: AnimalType, val breed: String, var age: Int) {
}

enum class AnimalType(private val displayName: String) {
    CAT("Cat"),
    DOG("Dog"),
    BUNNY("Bunny");

    override fun toString(): String {
        return displayName
    }
}