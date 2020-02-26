package com.arshak.freeiptv.di

import com.arshak.core.data.network.setup.AppleHttpClient
import org.koin.dsl.module

/**
 * Created by Arshak Avagyan on 2020-02-20.
 * Project Name: FreeIPTV
 */
object RetrofitModule {
    val value = module {
        single { AppleHttpClient(get()) }
    }
}