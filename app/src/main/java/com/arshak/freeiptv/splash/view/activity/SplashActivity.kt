package com.arshak.freeiptv.splash.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.arshak.core.view.screens.BaseActivity
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.ActivitySplashBinding
import com.arshak.freeiptv.splash.data.DataModel

/**
 * Created by Arshak Avagyan on 2020-01-31.
 * Project Name: FreeIPTV
 */

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun bindActivity(): Int = R.layout.activity_splash

    override fun setBindingData() = Unit

    override fun setupView() = Unit

}