package com.arshak.freeiptv.screens.home.view.fragment

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.arshak.core.di.NavigationModule
import com.arshak.core.navigation.NavigationManager
import com.arshak.core.view.screens.fragment.BaseFragment
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.FragmentHomeBinding
import com.arshak.freeiptv.screens.home.viewmodel.HomeViewModel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(
        R.layout.fragment_home,
        HomeViewModel::class
    ) {

    private val mNestedNavigationManager: NavigationManager by inject(named(NavigationModule.NAVIGATION_NESTED)) {
        parametersOf(
            R.id.homeHostFragment, requireActivity()
        )
    }
//    private val mNestedNavigationManager: NavigationManager by inject(named(NavigationModule.NAVIGATION_FRAGMENT)) {
//        parametersOf(requireActivity().findNavController(R.id.homeHostFragment))
//    }

    override fun setBindingData() {
        fragmentBinding.viewmodel = activityViewModel
    }

    override fun setupView() {
        fragmentBinding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.my_music -> navigateWithFadeAnimation(R.id.to_myMusicFragment)
                R.id.search -> navigateWithFadeAnimation(R.id.to_searchFragment)
                else -> Unit
            }
            true
        }
    }

    override fun observeMandatoryLiveData() {
        activityViewModel.navigationDestinatonLiveData.observe(viewLifecycleOwner, Observer {
            navigateWithFadeAnimation(it)
        })
    }

    private fun navigateWithFadeAnimation(@IdRes navigationId: Int) =
        mNestedNavigationManager.navigateWithAnim(
            navigationId,
            R.anim.fade_in,
            R.anim.fade_out
        )
}