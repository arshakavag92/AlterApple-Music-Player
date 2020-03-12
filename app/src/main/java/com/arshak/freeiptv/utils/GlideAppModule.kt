package com.arshak.freeiptv.utils

import android.content.Context
import com.arshak.core.data.network.setup.AppleHttpClient
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.OkHttpClient
import org.koin.core.context.GlobalContext.get
import java.io.InputStream
import java.util.concurrent.TimeUnit


/**
 * Created by Arshak Avagyan on 2020-02-06.
 * Project Name: FreeIPTV
 */

@GlideModule
class GlideAppModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val client = get().koin.get<AppleHttpClient>().getTrustedHttpClient()
        val factory = OkHttpUrlLoader.Factory(client)
        glide.registry.replace(
            GlideUrl::class.java,
            InputStream::class.java, factory
        )
    }
}