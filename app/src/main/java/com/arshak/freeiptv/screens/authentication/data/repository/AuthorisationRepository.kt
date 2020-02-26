package com.arshak.freeiptv.screens.authentication.data.repository

import android.app.Application
import android.content.Intent
import com.apple.android.sdk.authentication.AuthenticationManager
import com.apple.android.sdk.authentication.TokenResult
import com.arshak.core.data.local.cache.TemporaryData
import com.arshak.core.data.network.model.SearchRequestModel
import com.arshak.core.data.repository.BaseRepository


/**
 * Created by Arshak Avagyan on 2020-02-17.
 * Project Name: FreeIPTV
 */

class AuthorisationRepository(
    val context: Application,
    private val authenticationManager: AuthenticationManager,
    private val musicApi: MusicApi
) : BaseRepository() {

    fun handleResponseResult(data: Intent?): TokenResult =
        authenticationManager.handleTokenResult(data)

    suspend fun getUserStoreFront() = musicApi.getUserStoreFront()

}