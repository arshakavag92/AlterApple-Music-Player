package com.arshak.freeiptv.screens.home.view.fragment

import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.arshak.core.data.local.model.AlbumUIModel
import com.arshak.core.data.network.model.HistoryResponseModel
import com.arshak.core.data.network.model.Output
import com.arshak.core.data.network.model.SearchItemTypeEnum
import com.arshak.core.view.screens.fragment.BaseFragment
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.FragmentMyMusicBinding
import com.arshak.freeiptv.screens.home.view.adapter.RecentPlayedAdapter
import com.arshak.freeiptv.screens.home.viewmodel.MyMusicViewModel
import com.arshak.freeiptv.screens.home.widget.OnAlbumDetailsClickListener
import com.arshak.freeiptv.utils.DTOConverter

/**
 * Created by Arshak Avagyan on 3/12/20.
 * Project Name: FreeIPTV
 */
class MyMusicFragment :
    BaseFragment<FragmentMyMusicBinding, MyMusicViewModel>(
        R.layout.fragment_my_music,
        MyMusicViewModel::class
    ) {

    private val mRecentPlayedAdapter = RecentPlayedAdapter().apply {
        onAlbumDetailsClickListener = object : OnAlbumDetailsClickListener {
            override fun onAlbumDetailsClicked(albummodel: AlbumUIModel) {
                val direction = AlbumDetailsFragmentDirections.openAlbumDetailsFragment(albummodel)
                mNavigationManager.navigate(direction)
            }
        }
    }

    override fun setBindingData() {
        fragmentBinding.viewmodel = activityViewModel
    }

    override fun loadData() {
        activityViewModel.history().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Output.Success -> showRecentListenedItems(it.output)
                is Output.Error -> Unit
            }
        })
    }

    private fun showRecentListenedItems(response: HistoryResponseModel) {
        val albums = response.data.filter { resource -> resource.type == SearchItemTypeEnum.ALBUMS.type }
        mRecentPlayedAdapter.submitList(DTOConverter.libraryAlbumsUIConverter(albums))
    }

    override fun setupView() {
        fragmentBinding.recentHistoryRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MyMusicFragment.context)
            adapter = mRecentPlayedAdapter
        }
    }
}