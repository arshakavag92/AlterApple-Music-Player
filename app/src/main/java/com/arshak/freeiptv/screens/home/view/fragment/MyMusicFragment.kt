package com.arshak.freeiptv.screens.home.view.fragment

import com.arshak.core.view.screens.fragment.BaseFragment
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.FragmentMyMusicBinding
import com.arshak.freeiptv.screens.home.viewmodel.HomeViewModel

/**
 * Created by Arshak Avagyan on 3/12/20.
 * Project Name: FreeIPTV
 */
class MyMusicFragment :
    BaseFragment<FragmentMyMusicBinding, HomeViewModel>(
        R.layout.fragment_my_music,
        HomeViewModel::class
    ) {
    override fun setupView() = Unit
}