package com.example.animalhospital.textWatchers

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import com.example.animalhospital.utils.Util
import com.example.animalhospital.models.Result

abstract class FieldTextWatcher(protected val outputElement: TextView) : TextWatcher {
    private var result: Result? = null

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        result = getFieldState(s!!)
        Util.displayMessageIfError(result!!, outputElement)
    }

    fun isValidState(): Boolean {
        return result?.success ?: false
    }

    protected abstract fun getFieldState(value: CharSequence): Result
}