package com.example.animalhospital.textWatchers

import android.widget.TextView
import com.example.animalhospital.utils.Util
import com.example.animalhospital.models.FieldState

class AnimalNameTextWatcher(outputElement: TextView): FieldTextWatcher(outputElement) {
    private val nameMaxLength = 30

    override fun getFieldState(value: CharSequence): FieldState {
        if (value.isBlank()) {
            return FieldState.error(Util.emptyFieldTextMessage("Name"))
        }
        if (value.length > nameMaxLength) {
            return FieldState.error(Util.maxLengthMessage("Name", nameMaxLength))
        }

        return FieldState.success()
    }
}