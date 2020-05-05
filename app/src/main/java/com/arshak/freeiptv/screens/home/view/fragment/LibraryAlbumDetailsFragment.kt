package com.arshak.freeiptv.screens.home.view.fragment

import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.arshak.core.data.local.model.AlbumUIModel
import com.arshak.core.data.network.model.AlbumRelationshipEnum
import com.arshak.core.data.network.model.Output
import com.arshak.core.data.network.model.SongsResponseModel
import com.arshak.core.extensions.KotlinExtensions.toFormattedDate
import com.arshak.core.extensions.KotlinExtensions.toMinutes
import com.arshak.core.view.screens.fragment.BaseFragment
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.FragmentAlbumDetailsBinding
import com.arshak.freeiptv.databinding.FragmentLibraryAlbumDetailsBinding
import com.arshak.freeiptv.screens.home.view.adapter.AlbumSongsAdapter
import com.arshak.freeiptv.screens.home.view.adapter.LibraryAlbumSongsAdapter
import com.arshak.freeiptv.screens.home.view.adapter.LibrarySongsAdapter
import com.arshak.freeiptv.screens.home.viewmodel.MyMusicViewModel
import com.arshak.freeiptv.utils.DTOConverter
import kotlinx.android.synthetic.main.toolbar_main_details.view.*

class LibraryAlbumDetailsFragment :
    BaseFragment<FragmentLibraryAlbumDetailsBinding, MyMusicViewModel>(
        R.layout.fragment_library_album_details,
        MyMusicViewModel::class
    ) {

    lateinit var mAlbumDetailsModel: AlbumUIModel
    private val arguments by navArgs<AlbumDetailsFragmentArgs>()
    private val mLibrarySongsAdapter = LibraryAlbumSongsAdapter()

    override fun getSafeArgumentsFromBundle() {
        mAlbumDetailsModel = arguments.alumuimodel
    }

    override fun loadData() = loadLibraryAlbumDetails()

    override fun setupView() = with(fragmentBinding) {
        toolbar.backButton.setOnClickListener { mNavigationManager.goBack() }
        albumTracksRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mLibrarySongsAdapter
        }
        alnumuimodel = mAlbumDetailsModel
    }

    private fun loadLibraryAlbumDetails() =
        activityViewModel.getLibraryAlbumDetails(
            mAlbumDetailsModel.id,
            AlbumRelationshipEnum.TRACKS.value
        ).observe(viewLifecycleOwner,
            Observer {
                when (it) {
                    is Output.Success -> setupAlbumTracks(it.output)
                    is Output.Error -> Unit
                }
            })

    private fun setupAlbumTracks(librarySongsResponseModel: SongsResponseModel) {
        val songs = DTOConverter.songsUIConverter(librarySongsResponseModel.data)
        mLibrarySongsAdapter.submitList(songs)
    }
}