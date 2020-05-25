package com.kwang0.hackinssa.helper

import android.net.Uri
import android.widget.ImageView
import com.kwang0.hackinssa.R
import com.kwang0.hackinssa.presentation.ui.adapters.CountryAdapter
import com.squareup.picasso.Picasso

object PicassoHelper {
    fun loadImg(path: String?, target: ImageView) {
        Picasso.get()
                .load(path)
                .placeholder(R.drawable.ic_place_holder)
                .into(target)
    }
    fun loadImg(uri: Uri?, target: ImageView) {
        Picasso.get()
                .load(uri)
                .placeholder(R.drawable.ic_place_holder)
                .into(target)
    }
}