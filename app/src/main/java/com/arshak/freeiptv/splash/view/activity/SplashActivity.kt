package com.arshak.freeiptv.splash.view.activity

import android.animation.Animator
import com.arshak.core.view.screens.activity.BaseAppCompatActivity
import com.arshak.freeiptv.R
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Created by Arshak Avagyan on 2020-01-31.
 * Project Name: FreeIPTV
 */

class SplashActivity : BaseAppCompatActivity(R.layout.activity_splash) {

    override fun setupView() {
        splashAnimationView.setAnimation(R.raw.music_equalizer)
        splashAnimationView.addAnimatorListener(object : Animator.AnimatorListener {

            override fun onAnimationRepeat(animator: Animator?) {

            }

            override fun onAnimationEnd(animator: Animator?) {

            }

            override fun onAnimationCancel(animator: Animator?) {

            }

            override fun onAnimationStart(animator: Animator?) {

            }

        })
    }
}