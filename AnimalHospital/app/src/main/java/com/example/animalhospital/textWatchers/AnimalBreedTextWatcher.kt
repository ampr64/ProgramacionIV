package com.example.animalhospital.textWatchers

import android.widget.TextView
import com.example.animalhospital.Util
import com.example.animalhospital.models.FieldState

class AnimalBreedTextWatcher(outputElement: TextView): FieldTextWatcher(outputElement) {
    private val breedMaxLength = 30

    override fun getFieldState(value: CharSequence): FieldState {
        if (value.isBlank()){
            return FieldState.error(Util.emptyFieldTextMessage("Breed"))
        }
        if (value.length > breedMaxLength) {
            return FieldState.error(Util.maxLengthMessage("Breed", breedMaxLength))
        }

        return FieldState.success()
    }
}