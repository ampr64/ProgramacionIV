package com.example.animalhospital.models

class ModelState {
    val fieldStates = mutableListOf<FieldState>()

    fun isValid(): Boolean {
        return !fieldStates.any { fieldState -> !fieldState.valid }
    }
}