package com.arshak.core.di

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.arshak.core.navigation.NavigationManager
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by Arshak Avagyan on 2020-02-19.
 * Project Name: FreeIPTV
 */

object NavigationModule {

    val value = module {
        factory(named(NAVIGATION_ACTIVITY)) { (viewResourceId: Int, activity: AppCompatActivity) ->
            NavigationManager(
                viewResourceId,
                activity
            )
        }

        factory(named(NAVIGATION_FRAGMENT)) { (navController: NavController) ->
            NavigationManager(navController)
        }

        factory(named(NAVIGATION_NESTED)) { (viewResourceId: Int, activity: FragmentActivity) ->
            NavigationManager(
                viewResourceId,
                activity as AppCompatActivity
            )
        }
    }

    const val NAVIGATION_ACTIVITY = "NAVIGATION_ACTIVITY"
    const val NAVIGATION_FRAGMENT = "NAVIGATION_FRAGMENT"
    const val NAVIGATION_NESTED = "NAVIGATION_NESTED"
}