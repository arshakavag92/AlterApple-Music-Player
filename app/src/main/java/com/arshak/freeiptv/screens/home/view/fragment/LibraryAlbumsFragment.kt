package com.arshak.freeiptv.screens.home.view.fragment

import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.arshak.core.data.network.model.AlbumResponseModel
import com.arshak.core.data.network.model.LibraryTimeTypes
import com.arshak.core.data.network.model.Output
import com.arshak.core.view.screens.fragment.BaseFragment
import com.arshak.freeiptv.R

import com.arshak.freeiptv.databinding.FragmentUserLibraryBinding
import com.arshak.freeiptv.screens.home.view.adapter.RecentPlayedAdapter
import com.arshak.freeiptv.screens.home.viewmodel.MyMusicViewModel
import kotlinx.android.synthetic.main.layout_include_toolbar.view.*

class LibraryAlbumsFragment : BaseFragment<FragmentUserLibraryBinding, MyMusicViewModel>(
    R.layout.fragment_user_library,
    MyMusicViewModel::class
) {

    private val recentPlayedAdapter = RecentPlayedAdapter()
    override fun loadData() = loadLibraryAlbums()

    private fun loadLibraryAlbums() = activityViewModel.libraryAlbmus().observe(this, Observer {
        when (it) {
            is Output.Success -> showLibraryAlbums(it.output)
            is Output.Error -> Unit
        }
    })

    private fun showLibraryAlbums(response: AlbumResponseModel?) {
        val albumAttributes =
            response?.data
                ?.filter { resource -> resource.type == LibraryTimeTypes.ALBUMS.type }
                ?.map { resource -> resource.attributes!! } ?: emptyList()
        recentPlayedAdapter.submitList(albumAttributes)
    }

    private fun searchInLibraryForAlbums(term: String) =
        activityViewModel.searchInLibraryAlbums(term, listOf(LibraryTimeTypes.ALBUMS.type))
            .observe(viewLifecycleOwner,
                Observer {
                    when (it) {
                        is Output.Success -> showLibraryAlbums(it.output.results.albums)
                        is Output.Error -> Unit
                    }
                })

    override fun setupView() {
        fragmentBinding.apply {
            includeToolbar.toolbarTextView.text = getString(R.string.albums)
            albumsRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@LibraryAlbumsFragment.context)
                adapter = recentPlayedAdapter
            }
            searchEditText.doAfterTextChanged {
                when (it.isNullOrEmpty()) {
                    true -> loadLibraryAlbums()
                    else -> searchInLibraryForAlbums(it.toString().trim())
                }
            }
        }
    }
}