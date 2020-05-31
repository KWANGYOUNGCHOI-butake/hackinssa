package com.kwang0.hackinssa.helper

import android.text.Editable

// change String to Editable
fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)