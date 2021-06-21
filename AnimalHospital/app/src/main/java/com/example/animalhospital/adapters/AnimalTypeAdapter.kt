package com.example.animalhospital.adapters

import android.content.Context
import android.widget.ArrayAdapter
import com.example.animalhospital.model.AnimalType

class AnimalTypeAdapter(context: Context) : ArrayAdapter<AnimalType>(
        context,
        android.R.layout.simple_spinner_dropdown_item,
        AnimalType.values()
) {
}