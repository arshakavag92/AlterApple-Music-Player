package com.arshak.freeiptv.screens.home.view.fragment

import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.arshak.core.data.network.model.LibrarySearchResultsModel
import com.arshak.core.data.network.model.PlaylistsResponseModel
import com.arshak.core.data.network.model.LibraryTimeTypesEnum
import com.arshak.core.data.network.model.Output
import com.arshak.core.view.screens.fragment.BaseFragment
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.FragmentUserLibraryBinding
import com.arshak.freeiptv.screens.home.view.adapter.LibraryPlayListAdapter
import com.arshak.freeiptv.screens.home.viewmodel.MyMusicViewModel
import com.arshak.freeiptv.utils.DTOConverter
import kotlinx.android.synthetic.main.layout_include_toolbar.view.*

class LibraryPlaylistFragment : BaseLibraryDetailsFragment() {

    override var mDetailsAdapter: ListAdapter<*, *> = LibraryPlayListAdapter()
    override var libraryDetailType: LibraryTimeTypesEnum = LibraryTimeTypesEnum.PLAYLISTS
    override var mDetailsTitleID: Int = R.string.playlists

    override fun loadLibraryList() = activityViewModel.libraryPlaylist().observe(this, Observer {
        when (it) {
            is Output.Success -> handleDetailsResult(DTOConverter.libraryPlaylistToUIConverter(it.output.data))
            is Output.Error -> Unit
        }
    })

    override fun handleSearchResult(response: LibrarySearchResultsModel) =
        handleDetailsResult(DTOConverter.libraryPlaylistToUIConverter(response.playlists?.data))

}