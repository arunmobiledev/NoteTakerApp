package com.prollery.notetakerapp.common.extensions

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment

open class AppData {
    companion object {
        val USERNAME = Pair("USERNAME", "")
    }
}

private const val PREF_NAME = "MY_DATA"

fun getSharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
}

fun Context.putPrefString(key : Pair<String, Any>, value : String) {
    getSharedPreferences(this).edit().putString(key.first, value).apply()
}

fun Context.getPrefString(key : Pair<String, Any>) : String {
    return (getSharedPreferences(this).getString(key.first, key.second.toString()) ?: key.second).toString()
}

fun Fragment.putPrefString(key : Pair<String, Any>, value : String) {
    requireContext().putPrefString(key, value)
}

fun Fragment.getPrefString(key : Pair<String, Any>) : String {
    return requireContext().getPrefString(key)
}