package com.example.animalhospital.contracts

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.animalhospital.constants.Constants
import com.example.animalhospital.model.Examination
import com.example.animalhospital.common.ObjectResult

class NewExaminationContract : ActivityResultContract<Intent, ObjectResult<Examination?>>() {
    override fun createIntent(context: Context, input: Intent): Intent {
        return input
    }

    override fun parseResult(resultCode: Int, intent: Intent?): ObjectResult<Examination?> =
        when (resultCode) {
            Activity.RESULT_OK -> intent?.getSerializableExtra(Constants.KEY_NEW_EXAMINATION) as ObjectResult<Examination?>
            else -> ObjectResult.fail("Examination couldn't be created.")
        }
}