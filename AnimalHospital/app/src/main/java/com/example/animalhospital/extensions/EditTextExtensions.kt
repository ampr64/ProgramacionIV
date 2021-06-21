package com.example.animalhospital.extensions

import android.widget.EditText
import android.widget.TextView
import com.example.animalhospital.common.StatefulTextWatcher
import com.example.animalhospital.common.Result
import com.example.animalhospital.utils.Util

class EditTextExtensions {
    companion object {
        fun EditText.getStringValue(): String {
            return this.text.toString()
        }

        fun EditText.getIntValue(): Int {
            return this.getStringValue().toInt()
        }

        fun EditText.getIntValueOrDefault(): Int =
            if (this.getStringValue().isBlank()) 0 else this.getIntValue()

        fun EditText.watchNotBlank(
            fieldName: String? = null,
            outputTv: TextView? = null
        ): StatefulTextWatcher {
            return validateString(
                { it.isNotBlank() },
                Util.emptyFieldTextMessage(fieldName),
                outputTv
            )
        }

        fun EditText.watchMinLength(
            fieldName: String? = null,
            minLength: Int,
            outputTv: TextView? = null
        ): StatefulTextWatcher {
            return validateString(
                { it.length >= minLength },
                Util.emptyFieldTextMessage(fieldName),
                outputTv
            )
        }

        fun EditText.watchMaxLength(
            fieldName: String? = null,
            maxLength: Int,
            outputTv: TextView? = null
        ): StatefulTextWatcher {
            return validateString(
                { it.length <= maxLength },
                Util.emptyFieldTextMessage(fieldName),
                outputTv
            )
        }

        fun EditText.watchLengthInRange(
            fieldName: String? = null,
            minLength: Int,
            maxLength: Int,
            outputTv: TextView? = null
        ): StatefulTextWatcher {
            return validateString(
                { it.length in (minLength..maxLength) },
                Util.emptyFieldTextMessage(fieldName),
                outputTv
            )
        }

        fun EditText.watchMinValue(
            fieldName: String? = null,
            minValue: Int,
            outputTv: TextView? = null
        ): StatefulTextWatcher {
            return validateInteger(
                { it >= minValue },
                Util.emptyFieldTextMessage(fieldName),
                outputTv
            )
        }

        fun EditText.watchMaxValue(
            fieldName: String? = null,
            maxValue: Int,
            outputTv: TextView? = null
        ): StatefulTextWatcher {
            return validateInteger(
                { it <= maxValue },
                Util.emptyFieldTextMessage(fieldName),
                outputTv
            )
        }

        fun EditText.watchInRange(
            fieldName: String? = null,
            minValue: Int,
            maxValue: Int,
            outputTv: TextView? = null
        ): StatefulTextWatcher {
            return validateInteger(
                { it in (minValue..maxValue) },
                Util.mustBeInRangeMessage(fieldName, minValue, maxValue),
                outputTv
            )
        }

        private fun EditText.validateInteger(
            predicate: (Int) -> Boolean,
            errorMessage: String,
            outputTv: TextView? = null
        ): StatefulTextWatcher {
            return validateString(
                { it.isNotBlank() && predicate(it.toInt()) },
                errorMessage,
                outputTv
            )
        }

        private fun EditText.validateString(
            predicate: (String) -> Boolean,
            errorMessage: String,
            outputTv: TextView? = null
        ): StatefulTextWatcher {
            object : StatefulTextWatcher(this, outputTv) {
                override fun getStateResult(input: String): Result {
                    return if (!predicate(input)) {
                        Result.fail(errorMessage)
                    } else {
                        Result.ok()
                    }
                }
            }.let {
                this.addTextChangedListener(it)
                return it
            }
        }
    }
}