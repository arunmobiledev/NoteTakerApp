package com.prollery.notetakerapp.common.extensions

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.prollery.notetakerapp.R

fun EditText.disable() {
    isEnabled = false
    isFocusable = false
    isFocusableInTouchMode = false
    setBackgroundColor(context.getColor(R.color.disabled_txt))
}

fun EditText.enable() {
    isEnabled = true
    isFocusable = true
    isFocusableInTouchMode = true
    setBackgroundColor(context.getColor(R.color.enabled_txt))
}



