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
import kotlinx.android.synthetic.main.layout_include_toolbar.view.*

class LibraryPlaylistFragment : BaseLibraryDetailsFragment() {
    override var mDetailsAdapter: ListAdapter<*, *> = LibraryPlayListAdapter()

    override var libraryDetailType: LibraryTimeTypesEnum
        get() = TODO("Not yet implemented")
        set(value) {}
    override var mDetailsTitleID: Int
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun loadLibraryList() {
        TODO("Not yet implemented")
    }

    override fun handleSearchResult(response: LibrarySearchResultsModel) {
        TODO("Not yet implemented")
    }

}