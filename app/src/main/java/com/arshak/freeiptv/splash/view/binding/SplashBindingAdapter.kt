package com.arshak.freeiptv.splash.view.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RawRes
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.arshak.core.utils.GlideApp

/**
 * Created by Arshak Avagyan on 2020-02-01.
 * Project Name: FreeIPTV
 */

object SplashBindingAdapter {

    @BindingAdapter("app:animationSource")
    @JvmStatic
    fun animationSource(animationView: LottieAnimationView, @RawRes resource: Int) {
        animationView.setAnimation(resource)
    }

    @BindingAdapter("bind:imageResource")
    @JvmStatic
    fun imageUrl(
        imageView: ImageView,
        resource: String
    ) =
        GlideApp.with(imageView).load(resource).into(imageView)

    @BindingAdapter("bind:someText")
    @JvmStatic
    fun textContent(textView: TextView, text: String) {
        textView.text = text
    }

}