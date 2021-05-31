package com.example.animalhospital.utils

import android.graphics.Color
import android.widget.TextView
import com.example.animalhospital.models.Result

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

        fun displayMessageIfError(result: Result, outputElement: TextView) {
            clearMessage(outputElement)

            if (!result.success) {
                outputElement.text = result.error
                outputElement.setTextColor(Color.RED)
            }
        }

        fun clearMessage(outputElement: TextView) {
            outputElement.text = ""
        }
    }
}