package com.kwang0.hackinssa.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.kwang0.hackinssa.R
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream


object GlideHelper {

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

    val THUMBNAIL_SIZE = 48

    @Throws(FileNotFoundException::class, IOException::class)
    fun getThumbnail(context: Context, uri: Uri): Bitmap? {
        var input: InputStream? = context.getContentResolver()?.openInputStream(uri)
        val onlyBoundsOptions = BitmapFactory.Options()
        onlyBoundsOptions.inJustDecodeBounds = true
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions)
        input?.close()
        if (onlyBoundsOptions.outWidth == -1 || onlyBoundsOptions.outHeight == -1) {
            return null
        }
        val originalSize = if (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) onlyBoundsOptions.outHeight else onlyBoundsOptions.outWidth
        val ratio = if (originalSize > THUMBNAIL_SIZE) originalSize.toDouble() / THUMBNAIL_SIZE else 1.0
        val bitmapOptions = BitmapFactory.Options()
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio)
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888
        input = context.getContentResolver()?.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions)
        input?.close()
        return bitmap
    }

    private fun getPowerOfTwoForSampleRatio(ratio: Double): Int {
        val k = Integer.highestOneBit(Math.floor(ratio).toInt())
        return if (k == 0) 1 else k
    }

}