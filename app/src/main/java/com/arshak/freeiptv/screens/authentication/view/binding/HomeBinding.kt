package com.arshak.freeiptv.screens.authentication.view.binding

import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arshak.core.data.network.model.*
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.ItemSearchResultAlbumBinding
import com.arshak.freeiptv.databinding.ItemSearchResultArtistBinding
import com.arshak.freeiptv.databinding.ItemSearchResultSongBinding
import com.arshak.freeiptv.screens.authentication.view.widget.listener.OnSearchClearListener
import com.arshak.freeiptv.screens.authentication.view.widget.listener.SearchQueryListener
import com.arshak.freeiptv.screens.authentication.view.widget.listener.TextChangeListener
import com.arshak.freeiptv.screens.home.view.adapter.SearchHintAdapter
import com.arshak.freeiptv.utils.GlideApp
import com.arshak.freeiptv.utils.GlideUrlWithQueryParameter
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
    @BindingAdapter("historyHintAdapter")
    fun historyHintAdapter(recyclerView: RecyclerView, adapter: SearchHintAdapter) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(recyclerView.context)
            this.adapter = adapter
        }
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
    fun albumArtwork(imageView: ImageView, artworkModel: ArtworkModel) {
        GlideApp.with(imageView.context).load(
            GlideUrlWithQueryParameter(
                artworkModel.url,
                artworkModel.width,
                artworkModel.height
            )
        ).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("subResults")
    fun songResults(container: LinearLayout, response: SearchResponseItemUiModel<*>) {
        val data = response.data
        when (data.first().type) {
            SearchItemTypeEnum.SONGS.type -> inflateSongs(
                container,
                data as List<ResourceModel<SongAttributesModel>>
            )
            SearchItemTypeEnum.ALBUMS.type -> inflateAlbums(
                container,
                data as List<ResourceModel<AlbumAttributesModel>>
            )
            SearchItemTypeEnum.ARTISTS.type -> inflateArtists(
                container,
                data as List<ResourceModel<ArtistModel.Attributes>>
            )
        }
    }

    private fun inflateSongs(
        containter: LinearLayout,
        songs: List<ResourceModel<SongAttributesModel>>
    ) {
        val context = containter.context
        val inflater = LayoutInflater.from(context)
        songs.forEach {
            val binding = DataBindingUtil.inflate<ItemSearchResultSongBinding>(
                inflater,
                R.layout.item_search_result_song,
                containter,
                true
            )
            binding.songattributes = it.attributes
        }
    }

    private fun inflateAlbums(
        container: LinearLayout,
        albums: List<ResourceModel<AlbumAttributesModel>>
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
            binding.albumattributes = it.attributes
        }
    }

    private fun inflateArtists(
        container: LinearLayout,
        artists: List<ResourceModel<ArtistModel.Attributes>>
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
            binding.artistattributes = it.attributes
        }
    }

}