package com.arshak.freeiptv.main

import androidx.multidex.MultiDexApplication
import com.arshak.freeiptv.di.ApplicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Arshak Avagyan on 2019-11-28.
 * Project Name: FreeIPTV
 */
class FreeIPTVApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() =
        startKoin {
            androidLogger()
            androidContext(this@FreeIPTVApplication)
            modules(ApplicationModule.allModules)
        }

}