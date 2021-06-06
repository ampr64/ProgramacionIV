package com.example.animalhospital.adapters

import android.content.Context
import com.example.animalhospital.common.HintAdapter
import com.example.animalhospital.models.Animal

class AnimalAdapter(context: Context, animalList: ArrayList<Animal>) :
    HintAdapter(
        context,
        animalList.map { animal -> animal.name }, "Select an animal"
    ) {
}