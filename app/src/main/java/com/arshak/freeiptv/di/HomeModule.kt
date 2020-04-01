package com.arshak.freeiptv.di

import com.arshak.core.data.network.setup.AppleHttpClient
import com.arshak.freeiptv.screens.authentication.data.repository.MusicApi
import com.arshak.freeiptv.screens.home.data.network.HomeRepository
import com.arshak.freeiptv.screens.home.data.network.MusicRepository
import com.arshak.freeiptv.screens.home.viewmodel.HomeViewModel
import com.arshak.freeiptv.screens.home.viewmodel.MyMusicViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */
object HomeModule {
    val value = module {
        factory { HomeRepository(get()) }
        factory { MusicRepository(get()) }
        viewModel { MyMusicViewModel(get(), get()) }
        viewModel { HomeViewModel(get(), get()) }
    }
}