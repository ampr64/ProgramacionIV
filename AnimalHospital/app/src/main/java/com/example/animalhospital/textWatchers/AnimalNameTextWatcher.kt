package com.example.animalhospital.textWatchers

import android.widget.TextView
import com.example.animalhospital.common.StatefulTextWatcher
import com.example.animalhospital.utils.Util
import com.example.animalhospital.models.Result

class AnimalNameTextWatcher(outputElement: TextView): StatefulTextWatcher(outputElement) {
    private val nameMaxLength = 30

    override fun getStateResult(value: CharSequence): Result {
        if (value.isBlank()) {
            return Result.fail(Util.emptyFieldTextMessage("Name"))
        }
        if (value.length > nameMaxLength) {
            return Result.fail(Util.maxLengthMessage("Name", nameMaxLength))
        }

        return Result.ok()
    }
}