package com.kwang0.hackinssa.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.util.*


object GlideHelper {
    const val BASE_IMG_URL_250_PX = "https://github.com/hjnilsson/country-flags/blob/master/png250px/"

    fun loadImg(context: Context, path: String?, target: ImageView) {
        Glide.with(context)
                .asBitmap()
                .load(path)
                .placeholder(R.drawable.ic_place_holder)
                .circleCrop()
                .into(target)
    }
    fun loadImg(context: Context, uri: Uri?, target: ImageView) {
        Glide.with(context)
                .asBitmap()
                .load(uri)
                .placeholder(R.drawable.ic_place_holder)
                .circleCrop()
                .into(target)
    }
    fun loadImg(context: Context, file: File?, target: ImageView) {
        Glide.with(context)
                .asBitmap()
                .load(file)
                .placeholder(R.drawable.ic_place_holder)
                .circleCrop()
                .into(target)
    }

    // get Country Flag from BASE_IMG_URL_250_PX
    fun countryFlag(code: String): String {
        return (BASE_IMG_URL_250_PX + code.toLowerCase(Locale.ROOT) + ".png?raw=true")
    }
}