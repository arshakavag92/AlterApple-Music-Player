package com.arshak.freeiptv.authentication.view.activity

import android.content.Intent
import androidx.databinding.library.baseAdapters.BR.activity
import com.apple.android.sdk.authentication.AuthenticationManager
import com.arshak.core.view.screens.activity.BaseActivity
import com.arshak.freeiptv.R
import com.arshak.freeiptv.authentication.viewmodel.AuthorisationViewModel
import org.koin.android.ext.android.inject

/**
 * Created by Arshak Avagyan on 2020-02-13.
 * Project Name: FreeIPTV
 */
class AppleAuthorisationActivity :
    BaseActivity<ActivityAuthorisationBinding, AuthorisationViewModel>(
        R.layout.activity_authorisation,
        AuthorisationViewModel::class
    ) {

    val authenticationManager: AuthenticationManager by inject()


    @Override
    override fun setBindingData() {
        activityBinding.apply {
            activity = this@AppleAuthorisationActivity
            viewmodel = this@AppleAuthorisationActivity.viewModel
        }
    }

    override fun setupView() = Unit

    fun signInWithAppleCredentials() {
        val intent =
            authenticationManager.createIntentBuilder(getString(R.string.developer_token))
                .setHideStartScreen(true) //set this if you want to set custom params
                .build()
        startActivityForResult(intent, REQUEST_CODE_APPLE_MUSIC_AUTH)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        val REQUEST_CODE_APPLE_MUSIC_AUTH = 99
    }
}