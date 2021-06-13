package com.example.animalhospital.common

import android.text.Editable
import android.text.TextWatcher

open class OptionalTextWatcher : TextWatcher {
    protected var beforeTextChanged: ((s: CharSequence?, start: Int, count: Int, after: Int) -> Unit)? = null
    protected var onTextChanged: ((s: CharSequence?, start: Int, before: Int, count: Int) -> Unit)? = null
    protected var afterTextChanged: ((Editable?) -> Unit)? = null

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