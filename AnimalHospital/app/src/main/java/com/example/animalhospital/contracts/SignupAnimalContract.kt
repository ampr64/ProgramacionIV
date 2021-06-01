package com.example.animalhospital.contracts

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.animalhospital.activities.AnimalSignupActivity
import com.example.animalhospital.models.Animal
import com.example.animalhospital.models.ObjectResult

class SignupAnimalContract : ActivityResultContract<Intent, ObjectResult<Animal?>>() {
    override fun createIntent(context: Context, input: Intent): Intent {
        return Intent(context, AnimalSignupActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): ObjectResult<Animal?> =
        when (resultCode) {
            Activity.RESULT_OK -> intent?.getSerializableExtra("animal") as ObjectResult<Animal?>
            else -> ObjectResult.fail("Pup couldn't be signed up.")
        }
}