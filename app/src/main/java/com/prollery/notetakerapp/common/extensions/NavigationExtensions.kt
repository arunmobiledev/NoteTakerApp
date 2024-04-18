package com.prollery.notetakerapp.common.extensions

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController

fun NavController.navigateSafely(@IdRes resId: Int, args: Bundle?) {
    this.navigate(resId, args)
}

fun NavController.navigateSafely(@IdRes resId: Int) {
    this.navigate(resId)
}