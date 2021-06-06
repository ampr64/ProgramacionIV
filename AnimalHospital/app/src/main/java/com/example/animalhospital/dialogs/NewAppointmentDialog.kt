package com.example.animalhospital.dialogs

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class AppointmentDialog : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val (hour, minute) = now()

        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
    }

    private fun now(): Pair<Int, Int> {
        val now = Calendar.getInstance()

        return Pair(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MILLISECOND))
    }
}