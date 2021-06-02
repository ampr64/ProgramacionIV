package com.example.animalhospital.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.example.animalhospital.R
import com.example.animalhospital.contracts.SignupAnimalContract
import com.example.animalhospital.models.Animal
import com.example.animalhospital.models.ObjectResult
import com.example.animalhospital.utils.Util

class MainActivity : AppCompatActivity() {
    private val animals = ArrayList<Animal>()
    private lateinit var signupLauncher: ActivityResultLauncher<Intent>
    private lateinit var resultTv: TextView
    private lateinit var signUpNavigationButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeFields()
        setListeners()

        signupLauncher = registerForActivityResult(
            SignupAnimalContract()) {
                result -> handleOnSignupResult(result)
        }
    }

    private fun initializeFields() {
        resultTv = findViewById(R.id.main_tv_result)
        signUpNavigationButton = findViewById(R.id.main_btn_signUp)
    }

    private fun setListeners() {
        signUpNavigationButton.setOnClickListener {
            handleOnAnimalSignupClick()
        }
    }

    private fun handleOnAnimalSignupClick() {
        val intent = Intent(this, AnimalSignupActivity::class.java)
        signupLauncher.launch(intent)
    }

    private fun handleOnSignupResult(signupResult: ObjectResult<Animal?>) {
        var successMessage: String? = null
        if (signupResult.success) {
            val newAnimal = signupResult.obj as Animal
            animals.add(newAnimal)
            successMessage = "${newAnimal.name} has been signed up successfully!"
        }

        Util.displayResultMessage(resultTv, signupResult, successMessage = successMessage)
    }
}