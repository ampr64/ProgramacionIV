package com.example.animalhospital.textWatchers

import android.widget.TextView
import com.example.animalhospital.common.FieldTextWatcher
import com.example.animalhospital.utils.Util
import com.example.animalhospital.models.Result

class AnimalBreedTextWatcher(outputElement: TextView): FieldTextWatcher(outputElement) {
    private val breedMaxLength = 30

    override fun getFieldState(value: CharSequence): Result {
        if (value.isBlank()){
            return Result.fail(Util.emptyFieldTextMessage("Breed"))
        }
        if (value.length > breedMaxLength) {
            return Result.fail(Util.maxLengthMessage("Breed", breedMaxLength))
        }

        return Result.ok()
    }
}