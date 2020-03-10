package com.arshak.freeiptv.screens.home.viewmodel

import android.app.Application
import android.view.MenuItem
import androidx.lifecycle.*
import com.arshak.core.data.network.model.*
import com.arshak.core.data.viewmodel.BaseAndroidViewModel
import com.arshak.freeiptv.screens.authentication.view.widget.listener.OnSearchClearListener
import com.arshak.freeiptv.screens.authentication.view.widget.listener.SearchQueryListener
import com.arshak.freeiptv.screens.authentication.view.widget.listener.TextChangeListener
import com.arshak.freeiptv.screens.home.data.network.HomeRepository
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */
class HomeViewModel(context: Application, private val homeRepository: HomeRepository) :
    BaseAndroidViewModel(context),
    BottomNavigationView.OnNavigationItemSelectedListener,
    TextChangeListener, SearchQueryListener, OnSearchClearListener {

    var searchResultLiveData = MediatorLiveData<Output<SearchResponseModel>>()
    var searchHintResultLiveData = MediatorLiveData<Output<SearchHintResponseModel>>()

    lateinit var searchQueryLiveData: LiveData<Output<SearchResponseModel>>
    lateinit var searchHintQueryLiveData: LiveData<Output<SearchHintResponseModel>>

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        return false
    }

    fun searchForItems(term: String) {
        searchQueryLiveData = executeBackendCall {
            homeRepository.getSearchResult(
                SearchRequestModel(
                    term = term
                )
            )
        }
        searchResultLiveData.removeSource(searchQueryLiveData)
        searchResultLiveData.addSource(searchQueryLiveData) {
            searchResultLiveData.postValue(it)
        }
    }

    private fun seachHintForItems(searchString: String) {
        searchHintQueryLiveData = executeBackendCall {
            homeRepository.getSearchHints(
                SearchHintRequestModel(term = searchString)
            )
        }
        searchHintResultLiveData.removeSource(searchHintQueryLiveData)
        searchHintResultLiveData.addSource(searchHintQueryLiveData) {
            searchHintResultLiveData.postValue(it)
        }
    }

    override fun onTextChanged(text: String) = searchForItems(text)

    override fun onTextSubmitted(newText: String) = seachHintForItems(newText)

    override fun onClearClicked() = Unit
}