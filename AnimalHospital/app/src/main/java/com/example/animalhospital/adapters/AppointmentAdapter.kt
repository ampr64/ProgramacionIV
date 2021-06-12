package com.example.animalhospital.adapters

import android.content.Context
import com.example.animalhospital.common.HintAdapter
import com.example.animalhospital.models.Appointment
import java.time.format.DateTimeFormatter

class AppointmentAdapter(
    context: Context,
    appointmentList: ArrayList<Appointment>,
) :
    HintAdapter(
        context,
        appointmentList.map { a ->
            "${a.dateTime.format(DateTimeFormatter.ofPattern("MMM dd, HH:mm"))}" +
                    " - ${a.animal.name}: ${a.reason.lowercase()}"
        },
        "Select an appointment"
    ) {
}