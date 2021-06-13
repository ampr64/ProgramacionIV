package com.example.animalhospital.extensions

import android.widget.EditText
import android.widget.TextView
import com.example.animalhospital.common.StatefulTextWatcher
import com.example.animalhospital.models.Result
import com.example.animalhospital.utils.Util

class EditTextExtensions {
    companion object {
        fun EditText.getStringValue(): String {
            return this.text.toString()
        }

        fun EditText.getIntValue(): Int {
            return this.getStringValue().toInt()
        }

        fun EditText.validateNotBlank(fieldName: String, outputTv: TextView? = null) {
            this.addTextChangedListener(object: StatefulTextWatcher(this, outputTv) {
                override fun getStateResult(value: String): Result {
                    if (value.isNullOrBlank()) {
                        return Result.fail(Util.emptyFieldTextMessage(fieldName))
                    }

                    return Result.ok()
                }
            })
        }

        fun EditText.validateNotBlank(fieldName: String, outputTv: TextView? = null) {
            this.addTextChangedListener(object: StatefulTextWatcher(this, outputTv) {
                override fun getStateResult(value: String): Result {
                    if (value.isNullOrBlank()) {
                        return Result.fail(Util.emptyFieldTextMessage(fieldName))
                    }

                    return Result.ok()
                }
            })
        }
    }
}