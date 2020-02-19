package com.arshak.core.navigation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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

    constructor(fragment: Fragment) {
        mNavController = fragment.findNavController()
    }

    @IdRes
    var mNavigationViewID: Int = View.NO_ID

    lateinit var mNavController: NavController


    fun navigate(@IdRes id: Int, bundle: Bundle? = null) {

        val navOptions: NavOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_from_right)
            .setExitAnim(R.anim.slide_out_to_left)
            .setPopEnterAnim(R.anim.slide_in_from_left)
            .setPopExitAnim(R.anim.slide_out_to_right)
            .build()

        try {
            mNavController.navigate(id, bundle, navOptions)
        } catch (e: Exception) {
            Log.d("Exception", e.message!!)
        }
    }

    fun popBackTo(@IdRes id: Int) =
        mNavController.popBackStack(id, false)

}