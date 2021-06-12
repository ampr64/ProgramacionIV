package com.example.animalhospital.contracts

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.animalhospital.constants.Constants
import com.example.animalhospital.models.Appointment
import com.example.animalhospital.models.ObjectResult

class NewAppointmentContract : ActivityResultContract<Intent, ObjectResult<Appointment?>>() {
    override fun createIntent(context: Context, input: Intent): Intent {
        return input
    }

    override fun parseResult(resultCode: Int, intent: Intent?): ObjectResult<Appointment?> =
        when (resultCode) {
            Activity.RESULT_OK -> intent?.getSerializableExtra(Constants.KEY_NEW_APPOINTMENT) as ObjectResult<Appointment?>
            else -> ObjectResult.fail("Appointment couldn't be created.")
        }
}