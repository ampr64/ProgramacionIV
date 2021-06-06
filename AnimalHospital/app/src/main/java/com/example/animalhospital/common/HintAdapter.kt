package com.example.animalhospital.common

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

open class HintAdapter(context: Context, objects: Collection<String>, hint: String) : ArrayAdapter<String>(
    context,
    android.R.layout.simple_spinner_dropdown_item,
    arrayOf(hint).plus(objects)
) {
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = super.getDropDownView(position, convertView, parent) as TextView

        if (isHintItem(position)) {
            view.setTextColor(Color.LTGRAY)
        }

        return view
    }

    override fun isEnabled(position: Int): Boolean {
        return !isHintItem(position) && super.isEnabled(position);
    }

    private fun isHintItem(position: Int): Boolean {
        return position == 0
    }
}