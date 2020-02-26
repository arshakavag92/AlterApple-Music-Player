package com.arshak.freeiptv.screens.home.view.fragment

import com.arshak.core.view.screens.fragment.BaseFragment
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.FragmentSearchBinding
import com.arshak.freeiptv.screens.home.viewmodel.HomeViewModel

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */
class SearchFragment :
    BaseFragment<FragmentSearchBinding, HomeViewModel>(
        R.layout.fragment_search,
        HomeViewModel::class
    ) {

    override fun setBindingData() {
        fragmentBinding.viewmodel = activityViewModel
    }

    override fun setupView() {

    }
}