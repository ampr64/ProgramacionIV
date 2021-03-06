package com.example.animalhospital.utils

import android.graphics.Color
import android.widget.TextView
import com.example.animalhospital.constants.Constants
import com.example.animalhospital.extensions.TextViewExtensions.Companion.clearText
import com.example.animalhospital.extensions.TextViewExtensions.Companion.showNotification
import com.example.animalhospital.common.Result

class Util {
    companion object {
        private const val DEFAULT_FIELD_NAME = "Field"

        private fun fieldNameOrDefault(fieldName: String?): String =
                if (fieldName.isNullOrEmpty()) DEFAULT_FIELD_NAME else fieldName

        fun emptyFieldTextMessage(fieldName: String? = null): String {
            return "${fieldNameOrDefault(fieldName)} cannot be empty"
        }

        fun minLengthMessage(fieldName: String?, minLength: Int): String {
            return "${fieldNameOrDefault(fieldName)} must be at least $minLength characters long"
        }

        fun maxLengthMessage(fieldName: String?, maxLength: Int): String {
            return "${fieldNameOrDefault(fieldName)} must be at least $maxLength characters long"
        }

        fun mustBeInRangeMessage(fieldName: String?, minValue: Int, maxValue: Int): String {
            return "${fieldNameOrDefault(fieldName)} must be between $minValue and $maxValue"
        }

        fun displayMessageIfError(result: Result, outputElement: TextView) {
            outputElement.clearText()

            if (!result.success) {
                outputElement.text = result.error
                outputElement.setTextColor(Color.RED)
            }
        }

        fun displayResultMessage(
                outputElement: TextView,
                result: Result,
                duration: Int = 3000,
                successMessage: String? = null
        ) {
            val (message, bgColor, textColor) = parseResult(result, successMessage)

            outputElement.showNotification(message, textColor, bgColor, duration)
        }

        private fun parseResult(result: Result, successMessage: String?)
                : Triple<String, Int, Int> = when (result.success) {
            true -> Triple(
                    successMessage ?: "Success!",
                    Constants.backgroundColorSuccess,
                    Color.WHITE
            )
            false -> Triple(result.error!!, Color.RED, Color.WHITE)
        }
    }
}