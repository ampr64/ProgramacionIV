package com.example.animalhospital.models

import java.io.Serializable

class Examination(
    val diagnosis: String,
    val medicine: String,
    val treatment: String,
    val restDays: Int) : Serializable {
}