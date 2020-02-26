package com.arshak.freeiptv.di

import com.arshak.core.di.NavigationModule

/**
 * Created by Arshak Avagyan on 2019-11-28.
 * Project Name: FreeIPTV
 */
object ApplicationModule {

    val allModules =
        mutableListOf(
            RetrofitModule.value,
            AuthorisationModule.value,
            NavigationModule.value,
            HomeModule.value,
            UtilsModule.value
        )
}