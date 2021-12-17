package br.com.mmdevelopment.cleannotes.core

import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

fun Date.format(): String {
    return SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault()).format(this)
}

// Return String value for TextInput and set text to received value
var TextInputLayout.text: String
    get() = editText?.text.toString()
    set(value) {
        editText?.setText(value)
    }