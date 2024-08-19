package com.prollery.notetakerapp.common.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.getDefaultTime() : String {
    val format = SimpleDateFormat("dd-MM-yyyy_HH:mm:ss", Locale.getDefault())
    return format.format(this)
}

fun getDate(timeStampInMillis: Long): String {
    val date = Date()
    date.time = timeStampInMillis
    return SimpleDateFormat("MMM d, yyyy", Locale.getDefault()).format(date)
}

fun getDateTime(timeStampInMillis: Long): String {
    val date = Date()
    date.time = timeStampInMillis
    return SimpleDateFormat("hh:mm aa MMM d, yyyy", Locale.getDefault()).format(date)
}