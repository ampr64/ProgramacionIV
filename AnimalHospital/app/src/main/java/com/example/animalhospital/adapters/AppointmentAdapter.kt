package com.example.animalhospital.adapters

import android.content.Context
import com.example.animalhospital.common.HintAdapter
import com.example.animalhospital.models.Appointment

class AppointmentAdapter(context: Context, appointmentList: ArrayList<Appointment>) :
    HintAdapter(
        context,
        appointmentList.map { x -> "$x.hour - $x.animal.name" },
        "Select an appointment"
    ) {
}