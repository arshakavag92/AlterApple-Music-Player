package com.arshak.freeiptv.screens.authentication.view.activity

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.apple.android.sdk.authentication.AuthenticationManager
import com.arshak.core.extensions.toast
import com.arshak.core.view.screens.activity.BaseActivity
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.ActivityAuthorisationBinding
import com.arshak.freeiptv.screens.authentication.viewmodel.AuthorisationViewModel
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

    private val authenticationManager: AuthenticationManager by inject()

    @Override
    override fun setBindingData() {
        activityBinding.apply {
            activity = this@AppleAuthorisationActivity
            viewmodel = this@AppleAuthorisationActivity.viewModel
        }
    }

    override fun setupViewModel() {
        viewModel.asyncMessageHandlerLiveData.observe(this, Observer {
            toast(it, Toast.LENGTH_SHORT)
        })
    }

    override fun setupView() = Unit

    fun signInWithAppleCredentials() {
        val intent =
            authenticationManager.createIntentBuilder(getString(R.string.developer_token))
                .setHideStartScreen(true)
                .build()
        startActivityForResult(intent, REQUEST_CODE_APPLE_MUSIC_AUTH)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        const val REQUEST_CODE_APPLE_MUSIC_AUTH = 99
    }
}