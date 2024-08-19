package com.prollery.notetakerapp.common.extensions

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Context.shortToast(msg : String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(msg : String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun View.shortToast(msg : String) {
    Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show()
}

fun View.longToast(msg : String) {
    Toast.makeText(this.context, msg, Toast.LENGTH_LONG).show()
}

fun Fragment.shortToast(msg : String) {
    Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show()
}

fun Fragment.longToast(msg : String) {
    Toast.makeText(this.context, msg, Toast.LENGTH_LONG).show()
}

fun View.shortSnack(msg : String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT).setAction("Action", null).show()
}

fun View.longSnack(msg : String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show()
}

fun Fragment.shortSnack(msg : String) {
    Snackbar.make(this.requireView(), msg, Snackbar.LENGTH_SHORT).setAction("Action", null).show()
}

fun Fragment.longSnack(msg : String) {
    Snackbar.make(this.requireView(), msg, Snackbar.LENGTH_LONG).setAction("Action", null).show()
}
