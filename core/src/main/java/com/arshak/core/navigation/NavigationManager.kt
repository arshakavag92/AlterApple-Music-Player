package com.arshak.core.navigation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.*
import androidx.navigation.fragment.FragmentNavigator
import com.arshak.core.R

/**
 * Created by Arshak Avagyan on 2020-02-19.
 * Project Name: FreeIPTV
 */
class NavigationManager {

    constructor(@IdRes navigationViewID: Int, activity: AppCompatActivity) {
        mNavigationViewID = navigationViewID
        mNavController = activity.findNavController(navigationViewID)
    }

    constructor(navController: NavController) {
        mNavController = navController
    }

    @IdRes
    var mNavigationViewID: Int = View.NO_ID

    private val mDefaultNavOptions: NavOptions
        get() = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_from_right)
            .setExitAnim(R.anim.slide_out_to_left)
            .setPopEnterAnim(R.anim.slide_in_from_left)
            .setPopExitAnim(R.anim.slide_out_to_right)
            .build()

    private var mNavController: NavController

    val mCurrentDestinationId: Int
        get() = mNavController.currentDestination!!.id

    fun navigate(actionID: Int, bundle: Bundle? = null) {
        try {
            mNavController.navigate(actionID, bundle, mDefaultNavOptions)
        } catch (e: Exception) {
            Log.d("Exception", e.message!!)
        }
    }

    fun navigate(directions: NavDirections) {
        try {
            mNavController.navigate(directions, mDefaultNavOptions)
        } catch (e: Exception) {
            Log.d("Exception", e.message!!)
        }
    }

    fun navigateWithAnim(
        actionID: Int,
        enterAnim: Int,
        exitAnim: Int,
        popEnterAnim: Int = enterAnim,
        popExitAnim: Int = exitAnim,
        bundle: Bundle? = null,
        singleTop: Boolean = false
    ) {
        val navOptions: NavOptions = NavOptions.Builder()
            .setEnterAnim(enterAnim)
            .setExitAnim(exitAnim)
            .setPopEnterAnim(popEnterAnim)
            .setPopExitAnim(popExitAnim)
            .setLaunchSingleTop(singleTop)
            .build()

        try {
            mNavController.navigate(actionID, bundle, navOptions)
        } catch (e: Exception) {
            Log.d("Exception", e.message!!)
        }
    }

    fun onNavigationChangeListener(listener: (destination: NavDestination) -> Unit) {
        mNavController.addOnDestinationChangedListener { controller, _, _ ->
            listener(controller.currentDestination!!)
        }
    }

    fun popBackTo(@IdRes id: Int) =
        mNavController.popBackStack(id, false)

    fun goBack() = mNavController.popBackStack()

}