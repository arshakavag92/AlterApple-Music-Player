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
import com.arshak.freeiptv.screens.home.view.adapter.AlbumSongsAdapter
import com.arshak.freeiptv.screens.home.viewmodel.MyMusicViewModel
import com.arshak.freeiptv.utils.DTOConverter
import kotlinx.android.synthetic.main.toolbar_main_details.view.*

class AlbumDetailsFragment : BaseFragment<FragmentAlbumDetailsBinding, MyMusicViewModel>(
    R.layout.fragment_album_details,
    MyMusicViewModel::class
) {
    lateinit var mAlbumDetailsModel: AlbumUIModel

    val arguments by navArgs<AlbumDetailsFragmentArgs>()
    val albumSongsAdapter = AlbumSongsAdapter()

    override fun getSafeArgumentsFromBundle() {
        mAlbumDetailsModel = arguments.alumuimodel
    }

    override fun loadData() = loadAlbumTracks()

    override fun setupView() = with(fragmentBinding) {
        toolbar.backButton.setOnClickListener { mNavigationManager.goBack() }
        albumTracksRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = albumSongsAdapter
        }
        alnumuimodel = mAlbumDetailsModel
    }

    private fun loadAlbumTracks() =
        activityViewModel.getAlbumDetailsRelationship(
            mAlbumDetailsModel.id
        ).observe(this@AlbumDetailsFragment, Observer {
            when (it) {
                is Output.Success -> {
                    val tracks = it.output.data.first().relationships?.tracks!!
                    setupAlbumDetails(tracks)
                    setupAlbumTracks(tracks)
                }
                is Output.Error -> Unit
            }
        })

    private fun setupAlbumDetails(tracks: SongsResponseModel) {
        val albumLength =
            tracks.data.map { it.attributes!!.durationInMillis }.sum().toMinutes()
        mAlbumDetailsModel.trackCountAndLength.set(
            "${tracks.data.size} songs, $albumLength minutes" + "\n" +
                    "Release Date: ${mAlbumDetailsModel.releaseDate?.toFormattedDate()}" + "\n" +
                    "Copyright: ${mAlbumDetailsModel.copyright}."
        )
    }

    private fun setupAlbumTracks(songsResponseModel: SongsResponseModel) {
        val songs = DTOConverter.songsUIConverter(songsResponseModel.data)
        albumSongsAdapter.submitList(songs)
    }
}