@file:Suppress("UNCHECKED_CAST")

package com.arshak.freeiptv.screens.authentication.view.binding

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.IdRes
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.arshak.core.data.local.model.AlbumUIModel
import com.arshak.core.data.local.model.ArtistUIModel
import com.arshak.core.data.local.model.SongsUIModel
import com.arshak.core.data.network.model.*
import com.arshak.core.data.viewmodel.BaseAndroidViewModel
import com.arshak.freeiptv.utils.DTOConverter
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.ItemSearchResultAlbumBinding
import com.arshak.freeiptv.databinding.ItemSearchResultArtistBinding
import com.arshak.freeiptv.databinding.ItemSearchResultSongBinding
import com.arshak.freeiptv.screens.authentication.view.widget.listener.OnSearchClearListener
import com.arshak.freeiptv.screens.authentication.view.widget.listener.SearchQueryListener
import com.arshak.freeiptv.screens.authentication.view.widget.listener.TextChangeListener
import com.arshak.freeiptv.screens.home.viewmodel.MyMusicViewModel
import com.arshak.freeiptv.utils.GlideApp
import com.arshak.freeiptv.utils.GlideUrlWithQueryParameter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.bottomnavigation.BottomNavigationView


/**
 * Created by Arshak Avagyan on 2/25/20.
 * Project Name: FreeIPTV
 */

object HomeBinding {

    @JvmStatic
    @BindingAdapter("onNavigationItemSelected")
    fun setOnNavigationItemSelected(
        view: BottomNavigationView, listener: BottomNavigationView.OnNavigationItemSelectedListener?
    ) {
        view.setOnNavigationItemSelectedListener(listener)
    }

    @JvmStatic
    @BindingAdapter("selectedItemPosition")
    fun setSelectedItemPosition(
        view: BottomNavigationView, position: Int
    ) {
        view.selectedItemId = position
    }

    @JvmStatic
    @BindingAdapter(value = ["afterTextChanged", "onTextSubmitted"])
    fun afterTextChanged(
        searchView: SearchView,
        textChangeListener: TextChangeListener,
        querySubmitListener: SearchQueryListener
    ) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                textChangeListener.onTextChanged(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                querySubmitListener.onTextSubmitted(newText)
                return true
            }
        })
    }


    @JvmStatic
    @BindingAdapter("clickDestination", "viewmodel")
    fun navigateOnClick(view: View, @IdRes destinationId: Int, viewModel: BaseAndroidViewModel) =
        view.setOnClickListener { viewModel.navigate(destinationId) }

    @JvmStatic
    @BindingAdapter("artistID", "viewmodel")
    fun artistArtwork(imageView: ImageView, artistId: String, viewmodel: MyMusicViewModel) {
        viewmodel.getLibraryArtistDetails(artistId).observeForever(Observer {
            when (it) {
                is Output.Success -> albumArtwork(
                    imageView,
                    it.output.data.first().attributes?.artwork!!
                )
                is Output.Error -> Unit
            }
        })
    }


    @JvmStatic
    @BindingAdapter("onClearClicked")
    fun onSearchCloseListener(
        searchView: SearchView,
        onSearchClearListener: OnSearchClearListener
    ) {
        searchView.setOnCloseListener {
            onSearchClearListener.onClearClicked()
            true
        }
    }

    @JvmStatic
    @BindingAdapter("itemDecoration")
    fun itemDecoration(recyclerView: RecyclerView, itemDecoration: RecyclerView.ItemDecoration) {
        while (recyclerView.itemDecorationCount > 0) {
            recyclerView.removeItemDecorationAt(0);
        }
        recyclerView.addItemDecoration(itemDecoration)
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(imageView: ImageView, imageUrl: String) {
        GlideApp.with(imageView.context).load(imageUrl).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("artwork")
    fun albumArtwork(imageView: ImageView, artworkModel: ArtworkModel?) {
        if (artworkModel != null) {
            GlideApp.with(imageView.context).load(
                GlideUrlWithQueryParameter(
                    artworkModel.url,
                    artworkModel.width,
                    artworkModel.height
                )
            )
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.image_placeholder)
                .into(imageView)
        } else {
            GlideApp.with(imageView.context).load(R.drawable.image_placeholder).into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("subResults", "musicviewmodel")
    fun songResults(
        container: LinearLayout,
        response: SearchResponseItemUiModel<*>,
        musicViewModel: MyMusicViewModel
    ) {
        val data = response.data
        when (data.first().type) {
            SearchItemTypeEnum.SONGS.type -> inflateSongs(
                container,
                DTOConverter.librarySongsUIConverter(data as List<ResourceModel<SongAttributesModel>>)!!
            )
            
            SearchItemTypeEnum.ALBUMS.type -> inflateAlbums(
                container,
                DTOConverter.libraryAlbumsUIConverter(data as List<ResourceModel<AlbumAttributesModel>>)!!
            )

            SearchItemTypeEnum.ARTISTS.type -> inflateArtists(
                container, musicViewModel,
                DTOConverter.libraryArtistsUIConvert(data as List<ResourceModel<ArtistAttributesModel>>)!!
            )
        }
    }

    private fun inflateSongs(
        container: LinearLayout,
        songs: List<SongsUIModel>
    ) {
        val context = container.context
        val inflater = LayoutInflater.from(context)
        songs.forEach {
            val binding = DataBindingUtil.inflate<ItemSearchResultSongBinding>(
                inflater,
                R.layout.item_search_result_song,
                container,
                true
            )
            binding.songattributes = it
        }
    }

    private fun inflateAlbums(
        container: LinearLayout,
        albums: List<AlbumUIModel>
    ) {
        val context = container.context
        val inflater = LayoutInflater.from(context)
        albums.forEach {
            val binding = DataBindingUtil.inflate<ItemSearchResultAlbumBinding>(
                inflater,
                R.layout.item_search_result_album,
                container,
                true
            )
            binding.albumattributes = it
        }
    }

    private fun inflateArtists(
        container: LinearLayout,
        myMusicViewModel: MyMusicViewModel,
        artists: List<ArtistUIModel>
    ) {
        val context = container.context
        val inflater = LayoutInflater.from(context)
        artists.forEach {
            val binding = DataBindingUtil.inflate<ItemSearchResultArtistBinding>(
                inflater,
                R.layout.item_search_result_artist,
                container,
                true
            )
            binding.artistuimodel = it
            binding.viewmodel = myMusicViewModel
        }
    }
}