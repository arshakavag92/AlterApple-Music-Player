package com.arshak.core.view.screens.activity

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.arshak.core.data.viewmodel.BaseAndroidViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

/**
 * Created by Arshak Avagyan on 1/24/20.
 * Project Name: FreeIPTV
 */

/**
 * For cases, when both DataBinding and ViewModel implementation is needed for Activity Implementation
 * @param layoutResId layout inflation
 * @param viewModelClass get KClass (Kotlin class) as a parametet for further lazy initialization of specific ViewModel for current Activity.
 * @param V is for DataBinding Implementation for current Activity
 * @param VM for lazy implementation of ViewModel
 * */

abstract class BaseActivity<V : ViewDataBinding, out VM : BaseAndroidViewModel>(
    @LayoutRes val layoutResId: Int, viewModelClass: KClass<VM>
) :
    AppCompatActivity() {

    protected lateinit var activityBinding: V

    protected open val viewModel: VM by viewModel(viewModelClass)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = DataBindingUtil.setContentView(this, layoutResId)
        activityBinding.lifecycleOwner = this@BaseActivity

        setupViewModel()
        setBindingData()
        setupView()
    }

    protected open fun setupViewModel() = Unit

    protected open fun setBindingData() = Unit

    abstract fun setupView(): Unit

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.onActivityResult(requestCode, resultCode, data)
    }
}