package com.arshak.freeiptv.screens.authentication.view.fragment

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer
import com.apple.android.sdk.authentication.AuthenticationManager
import com.arshak.core.extensions.toast
import com.arshak.core.view.screens.fragment.BaseFragment
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.FragmentAuthorisationBinding
import com.arshak.freeiptv.screens.authentication.viewmodel.AuthorisationViewModel
import org.koin.android.ext.android.inject

/**
 * Created by Arshak Avagyan on 2/25/20.
 * Project Name: FreeIPTV
 */
class AppleAuthorisationFragment :
    BaseFragment<FragmentAuthorisationBinding, AuthorisationViewModel>(
        R.layout.fragment_authorisation, AuthorisationViewModel::class
    ) {

    private val authenticationManager: AuthenticationManager by inject()

    @Override
    override fun setBindingData() {
        fragmentBinding.apply {
            activity = this@AppleAuthorisationFragment
            viewmodel = this@AppleAuthorisationFragment.activityViewModel
        }
    }

    @Override
    override fun setupViewModel() {
        activityViewModel.navigationDestinatonLiveData.observe(this, Observer {
            mNavigationManager.navigate(it)
        })
    }

    @Override
    override fun setupView() = Unit

    fun signInWithAppleCredentials() {
        val intent =
            authenticationManager.createIntentBuilder(getString(R.string.developer_token))
                .setHideStartScreen(true)
                .build()
        startActivityForResult(intent, REQUEST_CODE_APPLE_MUSIC_AUTH)
    }

    companion object {
        const val REQUEST_CODE_APPLE_MUSIC_AUTH = 99
    }
}