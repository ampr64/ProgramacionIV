package com.example.animalhospital.textWatchers

import android.widget.TextView
import com.example.animalhospital.Util
import com.example.animalhospital.models.FieldState

class AnimalAgeTextWatcher(outputElement: TextView): FieldTextWatcher(outputElement) {
    private val minAge = 0
    private val maxAge = 99

    override fun getFieldState(value: CharSequence): FieldState {
        if (value.isBlank()) {
            return FieldState.error(Util.emptyFieldTextMessage("Age"))
        }

        val numberValue = value.toString().toDouble()
        if (numberValue < minAge || numberValue > maxAge) {
            return FieldState.error(Util.mustBeInRangeMessage("Age", minAge, maxAge))
        }

        return FieldState.success()
    }
}