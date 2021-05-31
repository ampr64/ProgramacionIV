package com.example.tp1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TemperatureConverterActivity : AppCompatActivity() {
    private val celsiusSymbol = "°C"
    private val fahrenheitSymbol = "°F"
    private lateinit var input: EditText
    private lateinit var output: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature_converter)

        initializeFields()

        input.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun afterTextChanged(s: Editable?) {
                if (input.text.any()) {
                    output.text = ("${output.text} $fahrenheitSymbol")
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val inputString = input.text.toString().removeSuffix(celsiusSymbol)
                if (inputString.any()) {
                    val result = celsiusToFahrenheit(inputString.toDouble())
                    output.text = result.toString();
                }
                else {
                    output.text = ""
                }
            }
        })
    }

    private fun initializeFields() {
        input = findViewById<EditText>(R.id.inputCelsius)
        output = findViewById<TextView>(R.id.outputFahrenheit)
    }

    private fun celsiusToFahrenheit(celsius: Double): Double {
        return celsius * 1.8 + 32
    }
}