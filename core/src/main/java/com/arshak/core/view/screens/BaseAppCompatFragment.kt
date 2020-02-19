package com.arshak.core.view.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.arshak.core.R
import com.arshak.core.navigation.NavigationManager
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * Created by Arshak Avagyan on 1/24/20.
 * Project Name: FreeIPTV
 */

abstract class BaseAppCompatFragment(@LayoutRes val layoutID: Int) : Fragment() {

    lateinit var mFragmentView: View

    val mNavigationManager: NavigationManager by inject { parametersOf(findNavController()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mFragmentView = inflater.inflate(layoutID, null)
        mFragmentView
        return mFragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    abstract fun setupView()
}