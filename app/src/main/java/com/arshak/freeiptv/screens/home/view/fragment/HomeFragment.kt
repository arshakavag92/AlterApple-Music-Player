package com.arshak.freeiptv.screens.home.view.fragment

import androidx.lifecycle.Observer
import com.arshak.core.data.network.model.Output
import com.arshak.core.view.screens.fragment.BaseFragment
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.FragmentHomeBinding
import com.arshak.freeiptv.screens.home.viewmodel.HomeViewModel

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home, HomeViewModel::class) {

    override fun setBindingData() {
        fragmentBinding.viewmodel = activityViewModel
    }

    override fun setupView() {
        activityViewModel.searchResultLiveData.observe(this, Observer {
            when (it) {
                is Output.Success -> {
                }
                is Output.Error -> Unit
            }
        })
    }
}