package com.arshak.core.view.screens.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.arshak.core.data.viewmodel.BaseAndroidViewModel
import kotlin.reflect.KClass

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */

/**
 * For cases, when both DataBinding and ViewModel implementation is needed for Fragment Implementation
 * @param layoutResId layout inflation
 * @param viewModelClass get KClass (Kotlin class) as a parametet for further lazy initialization of specific ViewModel for current Activity.
 * @param V is for DataBinding Implementation for current Activity
 * @param VM for lazy implementation of ViewModel
 * */

abstract class BaseFragment<V : ViewDataBinding, out VM : BaseAndroidViewModel>(
    @LayoutRes val layoutResId: Int, viewModelClass: KClass<VM>
) : BaseAppCompatFragment<VM>(layoutResId, viewModelClass) {

    protected lateinit var fragmentBinding: V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = DataBindingUtil.inflate(inflater, layoutResId, null, false)
        fragmentBinding.apply {
            setBindingData()
            lifecycleOwner = this@BaseFragment
        }
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewModel()
        setupView()
    }

    protected open fun setupViewModel() = Unit

    protected open fun setBindingData() = Unit

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityViewModel.onActivityResult(requestCode, resultCode, data)
    }
}