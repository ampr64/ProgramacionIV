package com.example.animalhospital.textWatchers

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import com.example.animalhospital.Util
import com.example.animalhospital.models.FieldState

abstract class FieldTextWatcher(protected val outputElement: TextView) : TextWatcher {
    private var fieldState: FieldState? = null

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        fieldState = getFieldState(s!!)
        Util.displayMessageIfError(fieldState!!, outputElement)
    }

    fun isValidState(): Boolean {
        return fieldState?.valid ?: false
    }

    protected abstract fun getFieldState(value: CharSequence): FieldState
}