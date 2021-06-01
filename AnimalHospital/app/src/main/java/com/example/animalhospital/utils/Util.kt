package com.example.animalhospital.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import android.widget.Toast
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

        fun displayResultMessage(
            outputElement: TextView,
            result: Result,
            duration: Int = 5000,
            successMessage: String? = null
        ) {
            val (message, bgColor) = parseResult(result, successMessage)

            outputElement.text = message
            outputElement.setBackgroundColor(bgColor)
        }

        fun showNotificationFor(tv: TextView, duration: Int, textColor: Int, backgroundColor: Int) {
            val currentBackground = (tv.background as ColorDrawable)?.color
            val currentTextColor = tv.currentTextColor

            tv.setTextColor(textColor)
            tv.setBackgroundColor(backgroundColor)

            tv.postDelayed(
                {
                    run {
                        tv.visibility = View.INVISIBLE
                        tv.setTextColor(currentTextColor)
                        tv.setBackgroundColor(currentBackground)
                    }
                },
                duration.toLong()
            )
        }

        private fun parseResult(result: Result, successMessage: String?): Pair<String, Int> =
            when (result.success) {
                true -> Pair(successMessage ?: "Success!", Color.GREEN)
                false -> Pair(result.error!!, Color.RED)
            }

        fun displayFormattedToast(
            context: Context,
            duration: Int,
            result: Result,
            successMessage: String? = null
        ) {
            val (message, bgColor) = parseResult(result, successMessage)

            val toast = Toast.makeText(context, message, duration)
            if (toast.view != null) {
                toast.view!!.setBackgroundColor(bgColor)
                toast.view!!.findViewById<TextView>(android.R.id.message).setTextColor(Color.WHITE)
            }

            toast.show()
        }

        fun hideTextViewAfter(textView: TextView, duration: Int) {
            textView.postDelayed(
                {
                    run { textView.visibility = View.INVISIBLE }
                },
                duration.toLong()
            )
        }
    }
}