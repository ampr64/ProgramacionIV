package com.example.tp1

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PalindromeCheckerActivity : AppCompatActivity() {
    private lateinit var inputText: EditText
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palindrome)

        initializeFields()

        inputText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun afterTextChanged(s: Editable?) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (inputText.length() > 2) {
                    val result = isPalindrome(inputText.text.toString())
                    displayResultMessage(result)
                }
                else {
                    clearResultMessage()
                }
            }
        })
    }

    private fun initializeFields() {
        inputText = findViewById<EditText>(R.id.inputText)
        resultText = findViewById<TextView>(R.id.resultText)
    }

    private fun isPalindrome(text: String): Boolean {
        val comparisonText = text.trim().toLowerCase()
        return comparisonText == comparisonText.reversed()
    }

    private fun displayResultMessage(result: Boolean) {
        val (message, messageColor) = getColoredMessage(result)
        resultText.text = message
        resultText.setTextColor(messageColor)
    }

    private fun getColoredMessage(result: Boolean): Pair<String, Int> {
        if (result) {
            return "You found a palindrome!" to Color.GREEN
        }
        return "Not a palindrome :(" to Color.RED
    }

    private fun clearResultMessage() {
        val resultText = findViewById<TextView>(R.id.resultText)
        resultText.text = ""
    }
}