package com.arshak.freeiptv.authentication.viewmodel

import android.app.Application
import android.content.Intent
import com.arshak.core.data.viewmodel.BaseAndroidViewModel
import com.arshak.freeiptv.authentication.data.repository.AuthorisationRepository
import com.arshak.freeiptv.authentication.view.activity.AppleAuthorisationActivity

/**
 * Created by Arshak Avagyan on 2020-02-17.
 * Project Name: FreeIPTV
 */

class AuthorisationViewModel(
    context: Application,
    val authorisationRepository: AuthorisationRepository
) : BaseAndroidViewModel(context) {


    private fun handleAuthorisationResult(data: Intent?) {

    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AppleAuthorisationActivity.REQUEST_CODE_APPLE_MUSIC_AUTH) {
            handleAuthorisationResult(data)
        }
    }
}