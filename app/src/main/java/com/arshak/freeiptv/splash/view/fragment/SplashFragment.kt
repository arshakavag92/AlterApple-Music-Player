package com.arshak.freeiptv.splash.view.fragment

import android.animation.Animator
import com.arshak.core.view.screens.BaseAppCompatFragment
import com.arshak.freeiptv.R
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Created by Arshak Avagyan on 2020-02-19.
 * Project Name: FreeIPTV
 */

class SplashFragment : BaseAppCompatFragment(R.layout.fragment_splash) {

    override fun setupView() {
        splashAnimationView.setAnimation(R.raw.music_equalizer)
        splashAnimationView.addAnimatorListener(object : Animator.AnimatorListener {

            override fun onAnimationRepeat(animator: Animator?) = Unit

            override fun onAnimationEnd(animator: Animator?) =
                mNavigationManager.navigate(R.id.action_splashFragment_to_appleAuthorisationActivity)

            override fun onAnimationCancel(animator: Animator?) = Unit

            override fun onAnimationStart(animator: Animator?) = Unit
        })
    }
}