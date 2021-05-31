package com.example.animalhospital.models

class FieldState private constructor(val valid: Boolean, val message: String? = null) {
    companion object {
        fun success(): FieldState {
            return FieldState(true)
        }

        fun error(message: String): FieldState {
            return FieldState(false, message)
        }
    }
}