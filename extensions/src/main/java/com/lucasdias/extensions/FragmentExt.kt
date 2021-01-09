package com.lucasdias.extensions

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun <T : View> Fragment.bind(@IdRes id: Int): Lazy<T?> {
    return lazy { view?.findViewById<T>(id) }
}

fun Fragment.findNavController(): NavController =
    NavHostFragment.findNavController(this)
