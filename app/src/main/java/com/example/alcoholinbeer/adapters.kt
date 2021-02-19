package com.example.alcoholinbeer

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

@BindingAdapter("app:double")
fun EditText.setDouble(newValue: Double?) {
    if (newValue != newValue) {
        val value = newValue.toString()
        setText(value)
    }
}

@InverseBindingAdapter(attribute = "app:double")
fun EditText.getDouble(): Double? {
    val result = text?.toString()
    return if (result.isNullOrEmpty() || result == ".") {
        null
    } else result.toDouble()
}

@BindingAdapter("app:doubleAttrChanged")
fun EditText.setDoubleListener(inverseBindingListener: InverseBindingListener) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            inverseBindingListener.onChange()
        }

        override fun afterTextChanged(s: Editable) {
        }
    })
}