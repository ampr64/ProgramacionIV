package com.example.animalhospital.utils

import android.graphics.Color
import android.widget.TextView
import com.example.animalhospital.models.FieldState

class Util {
    companion object {
        fun emptyFieldTextMessage(fieldName: String): String {
            return "$fieldName cannot be empty"
        }

        fun minLengthMessage(fieldName: String, minLength: Int): String {
            return "$fieldName must be at least $minLength characters long"
        }

        fun maxLengthMessage(fieldName: String, maxLength: Int): String {
            return "$fieldName must be at least $maxLength characters long"
        }

        fun mustBeInRangeMessage(fieldName: String, minValue: Int, maxValue: Int): String {
            return "$fieldName must be between $minValue and $maxValue"
        }

        fun displayMessageIfError(fieldState: FieldState, outputElement: TextView) {
            clearMessage(outputElement)

            if (!fieldState.valid) {
                outputElement.text = fieldState.message
                outputElement.setTextColor(Color.RED)
            }
        }

        fun clearMessage(outputElement: TextView) {
            outputElement.text = ""
        }
    }
}