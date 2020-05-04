package com.arshak.freeiptv.screens.home.view.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ListAdapter
import com.arshak.core.data.local.model.AlbumUIModel
import com.arshak.core.data.network.model.AlbumRelationshipEnum
import com.arshak.core.data.network.model.LibrarySearchResultsModel
import com.arshak.core.data.network.model.LibraryTimeTypesEnum
import com.arshak.core.data.network.model.Output
import com.arshak.freeiptv.R
import com.arshak.freeiptv.screens.home.view.adapter.RecentPlayedAdapter
import com.arshak.freeiptv.screens.home.widget.OnAlbumDetailsClickListener
import com.arshak.freeiptv.utils.DTOConverter

class LibraryAlbumsFragment : BaseLibraryDetailsFragment() {

    override var libraryDetailType: LibraryTimeTypesEnum = LibraryTimeTypesEnum.ALBUMS
    override var mDetailsTitleID: Int = R.string.albums
    override var mDetailsAdapter: ListAdapter<*, *> = RecentPlayedAdapter().apply {
        onAlbumDetailsClickListener = object : OnAlbumDetailsClickListener {
            override fun onAlbumDetailsClicked(albumUIModel: AlbumUIModel) {
                val direction =
                    LibraryAlbumDetailsFragmentDirections.openLibraryAlbumDetailsFragment(albumUIModel)
                mNavigationManager.navigate(direction)
            }
        }
    }

    override fun loadLibraryList() = activityViewModel.libraryAlbums().observe(this, Observer {
        when (it) {
            is Output.Success -> handleDetailsResult(DTOConverter.libraryAlbumsUIConverter(it.output.data))
            is Output.Error -> Unit
        }
    })

    override fun handleSearchResult(response: LibrarySearchResultsModel) =
        handleDetailsResult(DTOConverter.libraryAlbumsUIConverter(response.albums?.data))
}