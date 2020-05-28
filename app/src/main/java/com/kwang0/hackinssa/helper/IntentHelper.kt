package com.kwang0.hackinssa.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.kwang0.hackinssa.presentation.ui.activities.countryselect.CountrySelectActivity
import com.kwang0.hackinssa.presentation.ui.activities.friendadd.FriendAddActivity
import com.kwang0.hackinssa.presentation.ui.activities.main.MainActivity

object IntentHelper {

    const val IMG_REQUEST_CODE = 1000
    const val COUNTRY_REQUEST_CODE = 2000

    fun activityIntent(context: Context?, cls: Class<*>) {
        val intent = Intent(context, cls)
        context?.startActivity(intent)
    }

    fun activityResultIntent(activity: Activity?, cls: Class<*>, requestCode: Int) {
        val intent = Intent(activity, cls)
        activity?.startActivityForResult(intent, requestCode)
    }

    fun activityClearIntent(context: Context?, cls: Class<*>) {
        val intent = Intent(context, cls)
        context?.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
    }

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

    fun galleryIntent(activity: Activity?) {
        val intent = Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activity?.startActivityForResult(Intent.createChooser(intent, "Select Image"), IMG_REQUEST_CODE)
    }

}