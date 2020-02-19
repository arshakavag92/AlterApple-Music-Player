package com.arshak.freeiptv.authentication.data.repository

import android.app.Application
import com.apple.android.sdk.authentication.AuthenticationManager
import com.arshak.core.data.repository.BaseRepository
import com.arshak.freeiptv.R

/**
 * Created by Arshak Avagyan on 2020-02-17.
 * Project Name: FreeIPTV
 */

class AuthorisationRepository(
    val context: Application,
    val authenticationManager: AuthenticationManager
) : BaseRepository()