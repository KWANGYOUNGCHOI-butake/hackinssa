package com.kwang0.hackinssa.helper

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

// hide keyboard for fragment
fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

// hide keyboard for activity
fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

// hide keyboard for context
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}