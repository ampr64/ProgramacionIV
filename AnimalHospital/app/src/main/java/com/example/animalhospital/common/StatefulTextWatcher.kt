package com.example.animalhospital.common

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import com.example.animalhospital.extensions.EditTextExtensions.Companion.getStringValue
import com.example.animalhospital.common.Result
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

open class OptionalTextWatcher : TextWatcher {
    private val beforeTextChanged: ((s: CharSequence?, start: Int, count: Int, after: Int) -> Unit)? = null
    private val onTextChanged: ((s: CharSequence?, start: Int, before: Int, count: Int) -> Unit)? = null
    private val afterTextChanged: ((Editable?) -> Unit)? = null

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        beforeTextChanged
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChanged
    }

    override fun afterTextChanged(s: Editable?) {
        afterTextChanged
    }
}