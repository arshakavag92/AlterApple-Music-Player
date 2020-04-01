package com.arshak.freeiptv.screens.main.view

import com.arshak.core.view.screens.activity.BaseAppCompatActivity
import com.arshak.freeiptv.R

/**
 * Created by Arshak Avagyan on 2020-02-19.
 * Project Name: FreeIPTV
 */
class MainNavigationActivity :
    BaseAppCompatActivity(R.layout.activity_main_navigation, R.id.nav_host_fragment) {
    override fun setupView() = Unit

    override fun onBackPressed() {
//        if (mNavigationManager.mCurrentDestinationId == R.id.homeFragment) {
//            return
//        }
        super.onBackPressed()
    }
}