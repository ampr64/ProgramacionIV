package com.example.animalhospital.adapters

import android.content.Context
import com.example.animalhospital.common.HintAdapter
import com.example.animalhospital.models.Veterinarian

class VeterinarianAdapter(context: Context, veterinarianList: ArrayList<Veterinarian>) :
    HintAdapter(
        context, veterinarianList.map { vet -> vet.name }, "Select a veterinarian"
    ) {
}