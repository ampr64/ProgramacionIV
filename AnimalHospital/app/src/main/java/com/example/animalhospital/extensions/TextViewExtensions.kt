package com.example.animalhospital.extensions

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.TextView

class TextViewExtensions {
    companion object {
        fun TextView.clearText() {
            this.text = ""
        }

        fun TextView.showNotification(
            message: String,
            textColor: Int,
            backgroundColor: Int,
            duration: Int = 3000
        ) {
            val previousText = this.text
            val previousTextColor = this.currentTextColor
            val previousBgColor = (this.background as ColorDrawable?)?.color ?: Color.WHITE

            this.text = message
            this.setTextColor(textColor)
            this.setBackgroundColor(backgroundColor)

            this.postDelayed(
                {
                    run {
                        this.text = previousText
                        this.setTextColor(previousTextColor)
                        this.setBackgroundColor(previousBgColor)
                    }
                },
                duration.toLong()
            )
        }
    }
}