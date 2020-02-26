package com.arshak.freeiptv.media

import android.app.Application
import com.apple.android.sdk.authentication.TokenProvider
import com.arshak.core.utils.SecurePreferenceHelper
import com.arshak.freeiptv.R

/**
 * Copyright (C) 2018 Apple, Inc. All rights reserved.
 */
class AppleMusicTokenProvider(val context: Application) : TokenProvider {

    private val securePreferences = SecurePreferenceHelper.init(context)

    override fun getDeveloperToken(): String = context.getString(R.string.developer_token)

    override fun getUserToken(): String =
        securePreferences.get(SecurePreferenceHelper.Keys.KEY_APPLE_MUSIC_TOKEN, "")

    fun setUserToken(userToken: String) {
        securePreferences.put(SecurePreferenceHelper.Keys.KEY_APPLE_MUSIC_TOKEN, userToken)
    }

    fun isUserAuthorised() =
        securePreferences.sharedPreferences.contains(SecurePreferenceHelper.Keys.KEY_APPLE_MUSIC_TOKEN)

}