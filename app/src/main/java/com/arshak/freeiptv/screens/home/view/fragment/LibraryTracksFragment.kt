package com.arshak.freeiptv.screens.home.view.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ListAdapter
import com.arshak.core.data.network.model.LibrarySearchResultsModel
import com.arshak.core.data.network.model.LibraryTimeTypesEnum
import com.arshak.core.data.network.model.Output
import com.arshak.freeiptv.R
import com.arshak.freeiptv.screens.home.view.adapter.LibrarySongsAdapter
import com.arshak.freeiptv.utils.DTOConverter

class LibraryTracksFragment : BaseLibraryDetailsFragment() {

    override var libraryDetailType: LibraryTimeTypesEnum = LibraryTimeTypesEnum.SONGS
    override var mDetailsTitleID: Int = R.string.tracks
    override var mDetailsAdapter: ListAdapter<*, *> = LibrarySongsAdapter()

    override fun loadLibraryList() = activityViewModel.librarySongs().observe(this, Observer {
        when (it) {
            is Output.Success -> handleDetailsResult(DTOConverter.songsUIConverter(it.output.data))
            is Output.Error -> Unit
        }
    })

    override fun handleSearchResult(response: LibrarySearchResultsModel) =
        handleDetailsResult(DTOConverter.songsUIConverter(response.songs?.data))
}