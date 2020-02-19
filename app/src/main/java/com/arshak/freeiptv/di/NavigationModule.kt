package com.arshak.freeiptv.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.arshak.core.navigation.NavigationManager
import org.koin.dsl.module

/**
 * Created by Arshak Avagyan on 2020-02-19.
 * Project Name: FreeIPTV
 */

object NavigationModule {

    val navigationModule = module {
        factory { (viewResourceId: Int, activity: AppCompatActivity) ->
            NavigationManager(
                viewResourceId,
                activity
            )
        }

        factory { (fragment: Fragment) ->
            NavigationManager(fragment)
        }
    }
}