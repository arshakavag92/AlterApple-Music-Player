package com.arshak.core.navigation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.arshak.core.R

/**
 * Created by Arshak Avagyan on 2020-02-19.
 * Project Name: FreeIPTV
 */
class NavigationManager {

    constructor(@IdRes navigationViewID: Int, activity: AppCompatActivity) {
        mNavigationViewID = navigationViewID
        activity.findNavController(navigationViewID)
    }

    constructor(navController: NavController) {
        mNavController = navController
    }

    @IdRes
    var mNavigationViewID: Int = View.NO_ID

    private lateinit var mNavController: NavController

    fun navigate(@IdRes actionID: Int, bundle: Bundle? = null) {
        val navOptions: NavOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_from_right)
            .setExitAnim(R.anim.slide_out_to_left)
            .setPopEnterAnim(R.anim.slide_in_from_left)
            .setPopExitAnim(R.anim.slide_out_to_right)
            .build()

        try {
            mNavController.navigate(actionID, bundle, navOptions)
        } catch (e: Exception) {
            Log.d("Exception", e.message!!)
        }
    }

    fun navigateWithAnim(
        @IdRes actionID: Int,
        @AnimRes enterAnim: Int,
        @AnimRes exitAnim: Int,
        @AnimRes popEnterAnim: Int = enterAnim,
        @AnimRes popExitAnim: Int = exitAnim,
        bundle: Bundle? = null
    ) {
        val navOptions: NavOptions = NavOptions.Builder()
            .setEnterAnim(enterAnim)
            .setExitAnim(exitAnim)
            .setPopEnterAnim(popEnterAnim)
            .setPopExitAnim(popExitAnim)
            .build()

        try {
            mNavController.navigate(actionID, bundle, navOptions)
        } catch (e: Exception) {
            Log.d("Exception", e.message!!)
        }
    }

    fun popBackTo(@IdRes id: Int) =
        mNavController.popBackStack(id, false)

}