package com.arshak.freeiptv.screens.splash.view.fragment

import android.animation.Animator
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.arshak.core.data.local.cache.TemporaryData
import com.arshak.core.data.network.model.Output
import com.arshak.core.view.screens.fragment.BaseAppCompatFragment
import com.arshak.freeiptv.R
import com.arshak.freeiptv.media.AppleMusicTokenProvider
import com.arshak.freeiptv.screens.authentication.viewmodel.AuthorisationViewModel
import kotlinx.android.synthetic.main.fragment_splash.*
import org.koin.android.ext.android.inject

/**
 * Created by Arshak Avagyan on 2020-02-19.
 * Project Name: FreeIPTV
 */

class SplashFragment : BaseAppCompatFragment<AuthorisationViewModel>(
    R.layout.fragment_splash,
    AuthorisationViewModel::class
) {
    private val appleMusicTokenProvider: AppleMusicTokenProvider by inject()

    override fun setupView() {
        splashAnimationView.addAnimatorListener(object : Animator.AnimatorListener {

            override fun onAnimationRepeat(animator: Animator?) = Unit

            override fun onAnimationEnd(animator: Animator?) = checkForFunctionalRequirements()

            override fun onAnimationCancel(animator: Animator?) = Unit

            override fun onAnimationStart(animator: Animator?) = Unit
        })
    }

    fun checkForFunctionalRequirements() {
        when {
            appleMusicTokenProvider.isUserAuthorised() -> getUserStoreFront()
            else -> mNavigationManager.navigate(R.id.action_splashFragment_to_appleAuthorisationActivity)
        }
    }

    private fun getUserStoreFront() {
        activityViewModel.updateHttpClientHeaders()
        activityViewModel.getUserStoreFront().observe(this@SplashFragment, Observer {
            when (it) {
                is Output.Success -> {
                    TemporaryData.storeFront =
                        it.output.data.first().id
                    mNavigationManager.navigate(R.id.to_homeFragment)
                }
                is Output.Error -> Unit
            }
        })
    }
}