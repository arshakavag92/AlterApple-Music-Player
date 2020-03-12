package com.arshak.freeiptv.screens.home.view.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.arshak.core.data.network.model.Output
import com.arshak.core.data.network.model.SearchItemTypeEnum
import com.arshak.core.data.network.model.SearchResponseItemUiModel
import com.arshak.core.data.network.model.SearchResponseModel
import com.arshak.core.view.screens.fragment.BaseFragment
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.FragmentSearchBinding
import com.arshak.freeiptv.screens.home.view.adapter.SearchHintAdapter
import com.arshak.freeiptv.screens.home.view.adapter.SearchResultAdapter
import com.arshak.freeiptv.screens.home.view.adapter.SearchResultItemCallback
import com.arshak.freeiptv.screens.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */
class SearchFragment :
    BaseFragment<FragmentSearchBinding, HomeViewModel>(
        R.layout.fragment_search,
        HomeViewModel::class
    ) {

    private val mSearchHintAdapter: SearchHintAdapter = SearchHintAdapter().apply {
        callBack = object : SearchHintAdapter.SearchCallBack {
            override fun onSearchClicked(term: String) = activityViewModel.searchForItems(term)
        }
    }

    private val mSearchResultAdapter: SearchResultAdapter = SearchResultAdapter().apply {
        callback = object : SearchResultItemCallback {
            override fun onItemClick() = Unit

            override fun onOptionsClicked() = Unit

            override fun onMoreClicked() = Unit
        }
    }

    override fun setupViewModel() {
        activityViewModel.apply {
            searchResultLiveData.observe(this@SearchFragment.viewLifecycleOwner,
                Observer {
                    when (it) {
                        is Output.Success -> {
                            val data: MutableList<SearchResponseItemUiModel<*>> = mutableListOf()
                            val response = it.output.results
                            data.apply {
                                add(
                                    SearchResponseItemUiModel(
                                        href = response.songs.href,
                                        type = SearchItemTypeEnum.SONGS.type,
                                        name = getString(R.string.tracks),
                                        data = response.songs.data,
                                        id = response.songs.href
                                    )
                                )
                                add(
                                    SearchResponseItemUiModel(
                                        href = response.artists.href,
                                        type = SearchItemTypeEnum.ARTISTS.type,
                                        name = getString(R.string.artists),
                                        data = response.artists.data,
                                        id = response.artists.href
                                    )
                                )
                                // TODO albums
                                add(
                                    SearchResponseItemUiModel(
                                        href = response.albums.href,
                                        type = SearchItemTypeEnum.ALBUMS.type,
                                        name = getString(R.string.albums),
                                        data = response.albums.data,
                                        id = response.albums.href
                                    )
                                )
                            }
                            setupSearchResultList(data)
                        }
                        is Output.Error -> Unit
                    }
                })

            searchHintResultLiveData.observe(this@SearchFragment.viewLifecycleOwner,
                Observer {
                    when (it) {
                        is Output.Success -> setupHintResultList(it.output.results.terms.toMutableList())
                        is Output.Error -> Unit
                    }
                })
        }
    }

    private fun setupHintResultList(searchHintData: MutableList<String>) =
        mSearchHintAdapter.apply {
            if (searchFragmentRecyclerView.adapter != this) {
                searchFragmentRecyclerView.adapter = this
            }
        }.submitList(searchHintData)

    private fun setupSearchResultList(data: MutableList<SearchResponseItemUiModel<*>>) =
        mSearchResultAdapter.apply {
            if (searchFragmentRecyclerView.adapter != this) {
                searchFragmentRecyclerView.adapter = this
            }
        }.submitList(data)

    override fun setBindingData() {
        fragmentBinding.viewmodel = activityViewModel
    }

    override fun setupView() {
        fragmentBinding.searchFragmentRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchFragment.context)
            adapter = mSearchHintAdapter
        }
    }
}