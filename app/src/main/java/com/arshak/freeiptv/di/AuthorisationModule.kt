package com.arshak.freeiptv.di

import android.app.Application
import com.apple.android.sdk.authentication.AuthenticationFactory
import com.apple.android.sdk.authentication.AuthenticationManager
import com.arshak.core.data.network.setup.AppleHttpClient
import com.arshak.freeiptv.media.AppleMusicTokenProvider
import com.arshak.freeiptv.screens.authentication.data.repository.AuthorisationRepository
import com.arshak.freeiptv.screens.authentication.data.repository.MusicApi
import com.arshak.freeiptv.screens.authentication.viewmodel.AuthorisationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Arshak Avagyan on 2020-02-17.
 * Project Name: FreeIPTV
 */
object AuthorisationModule {

    val value = module {
        single { AuthenticationFactory.createAuthenticationManager(get()) }
        factory { get<AppleHttpClient>().createService(MusicApi::class.java) }
        factory { AuthorisationRepository(get(), get(), get()) }
        viewModel { AuthorisationViewModel(get(), get(), get(), get()) }
    }
}