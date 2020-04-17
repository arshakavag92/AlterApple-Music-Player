package com.arshak.freeiptv.screens.home.view.fragment

import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.arshak.core.data.network.model.PlaylistsResponseModel
import com.arshak.core.data.network.model.LibraryTimeTypesEnum
import com.arshak.core.data.network.model.Output
import com.arshak.core.view.screens.fragment.BaseFragment
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.FragmentUserLibraryBinding
import com.arshak.freeiptv.screens.home.view.adapter.LibraryPlayListAdapter
import com.arshak.freeiptv.screens.home.viewmodel.MyMusicViewModel
import kotlinx.android.synthetic.main.layout_include_toolbar.view.*

class LibraryPlaylistFragment : BaseFragment<FragmentUserLibraryBinding, MyMusicViewModel>(
    R.layout.fragment_user_library,
    MyMusicViewModel::class
) {

    private val mPlaylistsAdapter = LibraryPlayListAdapter()

    override fun loadData() = loadLibraryPlaylists()

    override fun setupView() {
        fragmentBinding.apply {
            includeToolbar.toolbarTextView.text = getString(R.string.playlists)
            albumsRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@LibraryPlaylistFragment.context)
                adapter = mPlaylistsAdapter
            }
            searchEditText.doAfterTextChanged {
                when (it.isNullOrEmpty()) {
                    true -> loadLibraryPlaylists()
                    else -> searchInLibraryForPlaylists(it.toString().trim())
                }
            }
        }
    }

    private fun loadLibraryPlaylists() {
        activityViewModel.libraryPlaylist().observe(this, Observer {
            when (it) {
                is Output.Success -> showLibraryPlaylists(it.output)
                is Output.Error -> Unit
            }
        })
    }

    private fun searchInLibraryForPlaylists(term: String) {
        activityViewModel.searchInLibrary(term, listOf(LibraryTimeTypesEnum.PLAYLISTS.type))
            .observe(viewLifecycleOwner,
                Observer {
                    when (it) {
                        is Output.Success -> showLibraryPlaylists(it.output.results.playlists)
                        is Output.Error -> Unit
                    }
                })
    }

    private fun showLibraryPlaylists(response: PlaylistsResponseModel?) {
        val albumAttributes =
            response?.data
                ?.filter { resource -> resource.type == LibraryTimeTypesEnum.PLAYLISTS.type }
                ?.map { resource -> resource.attributes!! } ?: emptyList()
        mPlaylistsAdapter.submitList(albumAttributes)
    }
}