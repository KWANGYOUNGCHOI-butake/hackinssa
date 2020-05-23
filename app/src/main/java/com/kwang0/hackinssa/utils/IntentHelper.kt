package com.kwang0.hackinssa.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

object IntentHelper {
    fun emailIntent(context: Context?, recipient: String?) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))

        try {
            context?.startActivity(Intent.createChooser(intent, "Choose Email Client..."))
        }
        catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun phoneIntent(context: Context?, phoneNumber: String?) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:" + phoneNumber)

        try {
            context?.startActivity(intent)
        }
        catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }
}