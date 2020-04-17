package com.arshak.freeiptv.screens.home.view.fragment

import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.arshak.core.data.network.model.*
import com.arshak.core.view.screens.fragment.BaseFragment
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.FragmentUserLibraryBinding
import com.arshak.freeiptv.screens.home.viewmodel.MyMusicViewModel
import kotlinx.android.synthetic.main.layout_include_toolbar.view.*

abstract class BaseLibraryDetailsFragment :
    BaseFragment<FragmentUserLibraryBinding, MyMusicViewModel>(
        R.layout.fragment_user_library,
        MyMusicViewModel::class
    ) {

    abstract var mDetailsAdapter: ListAdapter<*, *>
    abstract var libraryDetailType: LibraryTimeTypesEnum
    abstract var mDetailsTitleID: Int

    override fun loadData() = loadLibraryList()

    override fun setupView(): Unit = with(fragmentBinding) {
        includeToolbar.toolbarTextView.text = getString(mDetailsTitleID)
        includeToolbar.toolbarTextView.setOnClickListener { mNavigationManager.goBack() }
        albumsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@BaseLibraryDetailsFragment.context)
            adapter = mDetailsAdapter
        }
        searchEditText.doAfterTextChanged {
            when (it.isNullOrEmpty()) {
                true -> loadLibraryList()
                else -> searchInLibraryForTerm(it.toString().trim())
            }
        }
    }

    private fun searchInLibraryForTerm(term: String) {
        activityViewModel.searchInLibrary(term, listOf(libraryDetailType.type))
            .observe(this, Observer {
                when (it) {
                    is Output.Success -> handleSearchResult(it.output.results)
                    is Output.Error -> Unit
                }
            })
    }

    abstract fun loadLibraryList()

    abstract fun handleSearchResult(response: LibrarySearchResultsModel)

    protected fun <T> handleDetailsResult(response: List<T>?) {
        mDetailsAdapter.submitList(response as List<Nothing>?)
    }
}