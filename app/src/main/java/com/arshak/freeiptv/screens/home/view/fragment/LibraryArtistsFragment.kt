package com.arshak.freeiptv.screens.home.view.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ListAdapter
import com.arshak.core.data.network.model.*
import com.arshak.freeiptv.utils.DTOConverter
import com.arshak.freeiptv.R
import com.arshak.freeiptv.screens.home.view.adapter.LibraryArtistsAdapter
import org.koin.android.ext.android.get

class LibraryArtistsFragment : BaseLibraryDetailsFragment() {

    override var libraryDetailType: LibraryTimeTypesEnum = LibraryTimeTypesEnum.ARTISTS
    override var mDetailsTitleID: Int = R.string.artists

    override var mDetailsAdapter: ListAdapter<*, *> = LibraryArtistsAdapter(get())

    override fun loadLibraryList() = activityViewModel.libraryArtists().observe(this, Observer {
        when (it) {
            is Output.Success -> handleDetailsResult(DTOConverter.libraryArtistsUIConvert(it.output.data))
            is Output.Error -> Unit
        }
    })

    override fun handleSearchResult(response: LibrarySearchResultsModel) =
        handleDetailsResult(DTOConverter.libraryArtistsUIConvert(response.artists?.data))
}