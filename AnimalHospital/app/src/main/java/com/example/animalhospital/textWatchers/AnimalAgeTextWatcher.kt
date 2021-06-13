package com.example.animalhospital.textWatchers

import android.widget.TextView
import com.example.animalhospital.common.StatefulTextWatcher
import com.example.animalhospital.utils.Util
import com.example.animalhospital.models.Result

class AnimalAgeTextWatcher(outputElement: TextView): StatefulTextWatcher(outputElement) {
    private val minAge = 0
    private val maxAge = 99

    override fun getStateResult(value: CharSequence): Result {
        if (value.isBlank()) {
            return Result.fail(Util.emptyFieldTextMessage("Age"))
        }

        val numberValue = value.toString().toDouble()
        if (numberValue < minAge || numberValue > maxAge) {
            return Result.fail(Util.mustBeInRangeMessage("Age", minAge, maxAge))
        }

        return Result.ok()
    }
}