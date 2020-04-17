package com.arshak.core.view.screens.activity

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.arshak.core.di.NavigationModule
import com.arshak.core.navigation.NavigationManager
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

/**
 * Created by Arshak Avagyan on 2020-02-18.
 * Project Name: FreeIPTV
 */

/**
 * For cases, when no Data Binding or ViewModel needed in provided activity, just an AppCompatActivity abstraction
 * @see AppCompatActivity
 * @param layoutResId used for more modern Layout inflation, no need to use
 *
 * */

abstract class BaseAppCompatActivity(
    @LayoutRes layoutResId: Int,
    @IdRes navigationViewId: Int = View.NO_ID
) :
    AppCompatActivity(layoutResId) {

    open val mNavigationManager: NavigationManager by inject(named(NavigationModule.NAVIGATION_ACTIVITY)) {
        parametersOf(
            navigationViewId, this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    abstract fun setupView(): Unit
}