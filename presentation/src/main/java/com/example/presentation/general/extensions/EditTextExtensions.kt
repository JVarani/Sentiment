package com.example.presentation.general.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun EditText.hideKeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(applicationWindowToken, 0)
}