package com.arshak.freeiptv.di

import com.arshak.core.utils.SecurePreferenceHelper
import com.arshak.freeiptv.media.AppleMusicTokenProvider
import org.koin.dsl.module

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */
object UtilsModule {

    val value = module {
        single { SecurePreferenceHelper.init(get()) }
        single { AppleMusicTokenProvider(get()) }
    }
}