package com.arshak.freeiptv.screens.main.view

import android.view.View
import androidx.annotation.IdRes
import com.arshak.core.di.NavigationModule
import com.arshak.core.navigation.NavigationManager
import com.arshak.core.view.screens.activity.BaseActivity
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.ActivityMainNavigationBinding
import com.arshak.freeiptv.screens.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main_navigation.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

/**
 * Created by Arshak Avagyan on 2020-02-19.
 * Project Name: FreeIPTV
 */
class MainNavigationActivity :
    BaseActivity<ActivityMainNavigationBinding, HomeViewModel>(
        R.layout.activity_main_navigation,
        HomeViewModel::class

    ) {

    private val mNavigationManager: NavigationManager by inject(named(NavigationModule.NAVIGATION_ACTIVITY)) {
        parametersOf(R.id.nav_host_fragment, this)
    }

    override fun setupView() {
        mNavigationManager.onNavigationChangeListener {
            when (it.parent?.id) {
                R.id.nav_home -> bottom_navigation.visibility = View.VISIBLE
                else -> bottom_navigation.visibility = View.GONE
            }
        }
        activityBinding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.my_music -> navigateWithFadeAnimation(R.id.to_myMusicFragment, true)
                R.id.search -> navigateWithFadeAnimation(R.id.to_searchFragment, true)
                else -> Unit
            }
            true
        }
    }

    private fun navigateWithFadeAnimation(@IdRes navigationId: Int, singleTop: Boolean = false) =
        mNavigationManager.navigateWithAnim(
            navigationId,
            R.anim.fade_in,
            R.anim.fade_out,
            singleTop = singleTop
        )
}