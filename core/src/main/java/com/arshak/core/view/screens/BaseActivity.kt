package com.arshak.core.view.screens

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by Arshak Avagyan on 1/24/20.
 * Project Name: FreeIPTV
 */
abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var activityBinding: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = DataBindingUtil.setContentView(this, bindActivity())

        setBindingData()
        setupView()
    }

    @LayoutRes
    abstract fun bindActivity(): Int

    protected open fun setBindingData() = Unit

    abstract fun setupView(): Unit
}