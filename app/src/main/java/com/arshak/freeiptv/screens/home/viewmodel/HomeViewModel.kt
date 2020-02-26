package com.arshak.freeiptv.screens.home.viewmodel

import android.app.Application
import android.text.Editable
import android.view.MenuItem
import androidx.arch.core.util.Function
import androidx.lifecycle.*
import com.arshak.core.data.local.cache.TemporaryData
import com.arshak.core.data.network.model.*
import com.arshak.core.data.viewmodel.BaseAndroidViewModel
import com.arshak.core.extensions.SingleLiveEvent
import com.arshak.freeiptv.screens.authentication.view.widget.listener.SearchQueryListener
import com.arshak.freeiptv.screens.authentication.view.widget.listener.TextChangeListener
import com.arshak.freeiptv.screens.home.data.network.HomeRepository
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */
class HomeViewModel(context: Application, val homeRepository: HomeRepository) :
    BaseAndroidViewModel(context), BottomNavigationView.OnNavigationItemSelectedListener,
    TextChangeListener,
    SearchQueryListener {

    var searchResultLiveData = MediatorLiveData<Output<SearchResponseModel>>()

    var searchHintesultLiveData = MediatorLiveData<Output<SearchHintResultsModel>>()

    lateinit var seachQueryLiveData: LiveData<Output<SearchResponseModel>>

    lateinit var searchHintQueryLiveData: LiveData<Output<SearchHintResponseModel>>

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        return false
    }

    private fun searchForItems(term: String) {
        seachQueryLiveData =
            executeBackendCall {
                homeRepository.getSearchResult(
                    SearchRequestModel(
                        term = term
                    )
                )
            }

        searchResultLiveData.addSource(seachQueryLiveData) {
            searchResultLiveData.value = it
        }
    }

    override fun onTextChanged(text: String) {
        searchForItems(text)
    }

    override fun onTextSubmitted(newText: String) {
        searchForItems(newText)
    }
}