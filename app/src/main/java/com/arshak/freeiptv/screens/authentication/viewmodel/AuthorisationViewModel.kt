package com.arshak.freeiptv.screens.authentication.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Intent
import com.apple.android.sdk.authentication.TokenResult
import com.arshak.core.data.network.setup.AppleHttpClient
import com.arshak.core.data.network.setup.RequestHeaders
import com.arshak.core.data.viewmodel.BaseAndroidViewModel
import com.arshak.freeiptv.R
import com.arshak.freeiptv.media.AppleMusicTokenProvider
import com.arshak.freeiptv.screens.authentication.data.repository.AuthorisationRepository
import com.arshak.freeiptv.screens.authentication.view.activity.AppleAuthorisationActivity

/**
 * Created by Arshak Avagyan on 2020-02-17.
 * Project Name: FreeIPTV
 */

class AuthorisationViewModel(
    context: Application,
    private val authorisationRepository: AuthorisationRepository,
    private val appleMusicTokenProvider: AppleMusicTokenProvider,
    private val appleHttpClient: AppleHttpClient
) : BaseAndroidViewModel(context) {

    private fun handleAuthorisationResult(data: Intent?) {
        val tokenResult: TokenResult = authorisationRepository.handleResponseResult(data)
        when {
            tokenResult.isError.not() -> handleAuthorisationSuccess(tokenResult)
            else -> handleAuthorisationError(tokenResult)
        }
    }

    private fun handleAuthorisationSuccess(tokenResult: TokenResult) {
        appleMusicTokenProvider.userToken = tokenResult.musicUserToken
        updateHttpClientHeaders()
        navigate(R.id.action_appleAuthorisationFragment_to_homeFragment)
    }

    private fun handleAuthorisationError(tokenResult: TokenResult) {
        val error = tokenResult.error
        asyncMessageHandlerLiveData.value = error.name
    }

    fun updateHttpClientHeaders() {
        val requestHeaders = mutableSetOf<Pair<String, String>>().apply {
            add(Pair(RequestHeaders.USER_TOKEN, appleMusicTokenProvider.userToken))
            add(
                Pair(
                    RequestHeaders.AUTHORISATION,
                    "Bearer ${context.getString(R.string.developer_token)}"
                )
            )
        }
        appleHttpClient.addRequestHeaders(requestHeaders)
    }

    fun getUserStoreFront() =
        executeBackendCall { authorisationRepository.getUserStoreFront() }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AppleAuthorisationActivity.REQUEST_CODE_APPLE_MUSIC_AUTH && resultCode == Activity.RESULT_OK) {
            handleAuthorisationResult(data)
        }
    }
}