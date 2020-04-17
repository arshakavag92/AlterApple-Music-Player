package com.arshak.freeiptv.screens.home.view.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.arshak.core.data.network.model.*
import com.arshak.core.extensions.hideKeyboard
import com.arshak.core.view.screens.fragment.BaseFragment
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.FragmentSearchBinding
import com.arshak.freeiptv.screens.home.view.adapter.SearchHintAdapter
import com.arshak.freeiptv.screens.home.view.adapter.SearchResultAdapter
import com.arshak.freeiptv.screens.home.view.adapter.SearchResultItemCallback
import com.arshak.freeiptv.screens.home.viewmodel.HomeViewModel
import com.arshak.freeiptv.utils.DTOConverter
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.ext.android.get

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
            override fun onSearchClicked(term: String) {
                this@SearchFragment.hideKeyboard()
                activityViewModel.searchForItems(term)
            }
        }
    }

    private val mSearchResultAdapter: SearchResultAdapter = SearchResultAdapter(get()).apply {
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
                        is Output.Success -> handleSearchResultResponse(it.output)
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

    private fun handleSearchResultResponse(searchResponseModel: SearchResponseModel) {
        val data = DTOConverter.searchResultsToSearchUIModel(searchResponseModel, this.context!!)
        setupSearchResultList(data)
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

    override fun setupView(): Unit = with(fragmentBinding) {
        searchFragmentRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchFragment.context)
            adapter = mSearchHintAdapter
        }
        searchView.apply {
            isIconified = false
            setOnCloseListener {
                mSearchHintAdapter.submitList(mutableListOf())
                mSearchResultAdapter.submitList(mutableListOf())
                false
            }
        }
    }
}