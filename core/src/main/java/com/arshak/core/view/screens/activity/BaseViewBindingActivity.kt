package com.arshak.core.view.screens.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by Arshak Avagyan on 2020-02-18.
 * Project Name: FreeIPTV
 */

/**
 * For cases, when only ViewBinding is needed
 *
 */
abstract class BaseViewBindingActivity<V : ViewDataBinding>(@LayoutRes val layoutResId: Int) :
    AppCompatActivity() {

    protected lateinit var activityBinding: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = DataBindingUtil.setContentView(this, layoutResId)
    }

    abstract fun setupView()
}