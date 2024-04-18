package com.prollery.notetakerapp.common.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.getDefaultTime() : String {
    val format = SimpleDateFormat("dd-MM-yyyy_HH:mm:ss", Locale.getDefault())
    return format.format(this)
}