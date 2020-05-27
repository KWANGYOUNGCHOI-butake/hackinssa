package com.kwang0.hackinssa.presentation.ui.extensions

import android.R
import android.view.View
import com.kwang0.hackinssa.helper.hideKeyboard


class FocusChangeListener: View.OnFocusChangeListener {
    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (!hasFocus) {
            v?.context?.hideKeyboard(v)
        }
    }
}