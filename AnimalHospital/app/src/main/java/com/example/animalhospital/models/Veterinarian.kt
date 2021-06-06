package com.example.animalhospital.models

import java.io.Serializable

class Veterinarian(val name: String, val canExamine: List<AnimalType>) : Serializable {
}