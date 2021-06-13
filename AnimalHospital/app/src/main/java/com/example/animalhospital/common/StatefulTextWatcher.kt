package com.example.animalhospital.common

import android.text.Editable
import android.widget.EditText
import android.widget.TextView
import com.example.animalhospital.extensions.EditTextExtensions.Companion.getStringValue
import com.example.animalhospital.models.Result
import com.example.animalhospital.utils.Util

abstract class StatefulTextWatcher(
    private val inputEt: EditText,
    private val outputTv: TextView? = null
) : OptionalTextWatcher() {
    private var result: Result? = null

    override fun afterTextChanged(s: Editable?) {
        result = getStateResult(inputEt.getStringValue())
        outputTv?.also {
            Util.displayMessageIfError(result!!, outputTv)
        }
    }

    fun isValidState(): Boolean {
        return result?.success ?: false
    }

    protected abstract fun getStateResult(input: String): Result
}