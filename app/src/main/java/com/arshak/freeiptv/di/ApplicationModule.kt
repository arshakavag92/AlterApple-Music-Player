package com.arshak.freeiptv.di

import com.arshak.core.data.network.AppleHttpClient
import org.koin.dsl.module

/**
 * Created by Arshak Avagyan on 2019-11-28.
 * Project Name: FreeIPTV
 */
object ApplicationModule {

    private val retrofitModule = module {
        single { AppleHttpClient.httpClient }
    }

    val allModules = mutableListOf(retrofitModule)
}