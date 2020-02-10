package com.arshak.core.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.request.target.BitmapImageViewTarget

/**
 * Created by Arshak Avagyan on 2020-02-06.
 * Project Name: FreeIPTV
 */
class ImageLoader(context: Context) {

    lateinit var context: Context
    lateinit var glideRequest: GlideRequest<Drawable>
    lateinit var viewTarget: BitmapImageViewTarget

    companion object {
        fun newInstance(context: Context) = ImageLoader(context)
    }

    fun load(url: String) {
        glideRequest = GlideApp.with(context).load(url)
    }
}