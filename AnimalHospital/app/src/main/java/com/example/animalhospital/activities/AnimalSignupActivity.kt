package com.example.animalhospital.activities

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.animalhospital.R
import com.example.animalhospital.adapters.AnimalTypeAdapter
import com.example.animalhospital.common.StatefulTextWatcher
import com.example.animalhospital.constants.Constants
import com.example.animalhospital.extensions.EditTextExtensions.Companion.watchInRange
import com.example.animalhospital.extensions.EditTextExtensions.Companion.watchNotBlank
import com.example.animalhospital.models.Animal
import com.example.animalhospital.models.AnimalType
import com.example.animalhospital.models.ObjectResult

class AnimalSignupActivity : AppCompatActivity() {
    private lateinit var nameEt: EditText
    private lateinit var nameTv: TextView
    private lateinit var breedEt: EditText
    private lateinit var breedTv: TextView
    private lateinit var ageEt: EditText
    private lateinit var ageTv: TextView
    private lateinit var textWatchers: ArrayList<StatefulTextWatcher>
    private lateinit var animalTypeSp: Spinner
    private lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_signup)

        initializeFields()
        populateAnimalTypeSpinner()
        setListeners()
        setSignupButtonState()
    }

    private fun initializeFields() {
        nameEt = findViewById(R.id.animalSignup_et_animalName)
        nameTv = findViewById(R.id.animalSignup_tv_animalName)
        breedEt = findViewById(R.id.animalSignup_et_animalBreed)
        breedTv = findViewById(R.id.animalSignup_tv_animalBreed)
        ageEt = findViewById(R.id.animalSignup_et_animalAge)
        ageTv = findViewById(R.id.animalSignup_tv_animalAge)
        animalTypeSp = findViewById(R.id.animalSignup_spn_animalType)
        signUpButton = findViewById(R.id.animalSignup_btn_signUp)
    }

    private fun populateAnimalTypeSpinner() {
        val animalTypeAdapter = AnimalTypeAdapter(this)

        animalTypeSp.adapter = animalTypeAdapter
    }

    private fun setListeners() {
        val nameWatcher = nameEt.watchNotBlank("Name", nameTv)
        val breedWatcher = breedEt.watchNotBlank("Breed", breedTv)
        val ageWatcher = ageEt.watchInRange("Age", MIN_AGE, MAX_AGE, ageTv)

        textWatchers = arrayListOf(nameWatcher, breedWatcher, ageWatcher)

        signUpButton.setOnClickListener {
            handleOnSignupClick()
        }
    }

    override fun onUserInteraction() {
        super.onUserInteraction()

        setSignupButtonState()
    }

    private fun setSignupButtonState() {
        signUpButton.isEnabled = canSubmit()
    }

    private fun canSubmit(): Boolean {
        return textWatchers.all { tw -> tw.isValidState() }
    }

    private fun handleOnSignupClick() {
        val newAnimal = getAnimalFromForm()
        val result = ObjectResult.ok(newAnimal)
        intent?.let {
            it.putExtra(Constants.KEY_NEW_ANIMAL, result)
            setResult(Activity.RESULT_OK, it)
        }

        finish()
    }

    private fun getAnimalFromForm(): Animal {
        return Animal(
            nameEt.text.toString(),
            AnimalType.getByDisplayName(animalTypeSp.selectedItem.toString()),
            breedEt.text.toString(),
            ageEt.text.toString().toInt()
        )
    }

    companion object {
        const val MIN_AGE = 0
        const val MAX_AGE = 50
    }
}