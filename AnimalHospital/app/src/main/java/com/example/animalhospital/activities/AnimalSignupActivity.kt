package com.example.animalhospital.activities

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.animalhospital.R
import com.example.animalhospital.models.AnimalType
import com.example.animalhospital.textWatchers.AnimalAgeTextWatcher
import com.example.animalhospital.textWatchers.AnimalBreedTextWatcher
import com.example.animalhospital.textWatchers.AnimalNameTextWatcher
import com.example.animalhospital.textWatchers.FieldTextWatcher

class AnimalSignupActivity : AppCompatActivity() {
    private lateinit var nameEt: EditText
    private lateinit var nameTv: TextView
    private lateinit var nameTextWatcher: FieldTextWatcher
    private lateinit var breedEt: EditText
    private lateinit var breedTv: TextView
    private lateinit var breedTextWatcher: FieldTextWatcher
    private lateinit var ageEt: EditText
    private lateinit var ageTv: TextView
    private lateinit var ageTextWatcher: FieldTextWatcher
    private lateinit var animalTypeSp: Spinner
    private lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_signup)

        initializeFields()
        attachTextWatchers()
    }

    private fun initializeFields() {
        nameEt = findViewById<EditText>(R.id.et_animalName)
        nameTv = findViewById<TextView>(R.id.tv_animalName)
        nameTextWatcher = AnimalNameTextWatcher(nameTv)
        breedEt = findViewById<EditText>(R.id.et_animalBreed)
        breedTv = findViewById<TextView>(R.id.tv_animalBreed)
        breedTextWatcher = AnimalBreedTextWatcher(breedTv)
        ageEt = findViewById<EditText>(R.id.et_animalAge)
        ageTv = findViewById<TextView>(R.id.tv_animalAge)
        ageTextWatcher = AnimalAgeTextWatcher(ageTv)
        animalTypeSp = findViewById<Spinner>(R.id.spn_animalType)
        signUpButton = findViewById<Button>(R.id.btn_signUp)

        populateAnimalTypeSpinner()
    }

    private fun populateAnimalTypeSpinner() {
        val animalTypeAdapter = ArrayAdapter<AnimalType>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            AnimalType.values())

        animalTypeSp.adapter = animalTypeAdapter
    }

    private fun attachTextWatchers() {
        nameEt.addTextChangedListener(nameTextWatcher)
        breedEt.addTextChangedListener(breedTextWatcher)
        ageEt.addTextChangedListener(ageTextWatcher)
    }

    override fun onUserInteraction() {
        super.onUserInteraction()

        setSignupButtonState()
    }

    private fun setSignupButtonState() {
        signUpButton.isEnabled = canSubmit()
    }

    private fun canSubmit(): Boolean {
        return nameTextWatcher.isValidState()
            && breedTextWatcher.isValidState()
            && ageTextWatcher.isValidState()
    }

    private fun onSubmit() {

    }
}