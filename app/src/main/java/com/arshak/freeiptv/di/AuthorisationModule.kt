package com.arshak.freeiptv.di

import android.app.Application
import com.apple.android.sdk.authentication.AuthenticationFactory
import com.apple.android.sdk.authentication.AuthenticationManager
import com.arshak.freeiptv.authentication.data.repository.AuthorisationRepository
import com.arshak.freeiptv.authentication.viewmodel.AuthorisationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Arshak Avagyan on 2020-02-17.
 * Project Name: FreeIPTV
 */
object AuthorisationModule {

    val module = module {
        single {
            provideAuthenticationMagager(get())
        }

        factory {
            provideAuthroisationRepository(get(), get())
        }

        viewModel {
            provideAuthorisationViewModel(get(), get())
        }
    }

    private fun provideAuthenticationMagager(context: Application): AuthenticationManager =
        AuthenticationFactory.createAuthenticationManager(context)

    private fun provideAuthroisationRepository(
        context: Application,
        authenticationManager: AuthenticationManager
    ) = AuthorisationRepository(context, authenticationManager)

    private fun provideAuthorisationViewModel(
        context: Application,
        authorisationRepository: AuthorisationRepository
    ) = AuthorisationViewModel(context, authorisationRepository)
}