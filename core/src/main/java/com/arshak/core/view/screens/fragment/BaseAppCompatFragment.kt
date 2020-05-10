package com.arshak.core.view.screens.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.arshak.core.data.viewmodel.BaseAndroidViewModel
import com.arshak.core.di.NavigationModule
import com.arshak.core.navigation.NavigationManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import javax.inject.Inject
import kotlin.reflect.KClass

/**
 * Created by Arshak Avagyan on 1/24/20.
 * Project Name: FreeIPTV
 */

/**
 * For cases, when only ViewModel implementation is needed for Fragment Implementation
 * @param layoutResId layout inflation
 * @param viewModelClass get KClass (Kotlin class) as a parametet for further lazy initialization of specific ViewModel for current Activity.
 * @param VM for lazy implementation of ViewModel
 * */

abstract class BaseAppCompatFragment<VM : BaseAndroidViewModel>(
    @LayoutRes val layoutID: Int,
    viewModelClass: KClass<VM>
) : Fragment() {

    lateinit var mFragmentView: View

    open val activityViewModel: VM by sharedViewModel(viewModelClass)

    val mNavigationManager: NavigationManager by inject(named(NavigationModule.NAVIGATION_FRAGMENT)) {
        parametersOf(
            findNavController()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mFragmentView = inflater.inflate(layoutID, null)
        return mFragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeMandatoryLiveData()
        setupView()
    }

    protected open fun observeMandatoryLiveData() = with(activityViewModel) {
        navigationDestinatonLiveData.observe(viewLifecycleOwner, Observer {
            mNavigationManager.navigate(it)
        })
        navigationDirectionsLiveData.observe(viewLifecycleOwner, Observer {
            mNavigationManager.navigate(it)
        })
    }

    abstract fun setupView()
}