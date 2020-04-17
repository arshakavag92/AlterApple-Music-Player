package com.arshak.freeiptv.screens.home.view.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.arshak.core.data.network.model.LibraryPlaylistAttributesModel
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.ItemLibraryPlaylistBinding
import com.arshak.freeiptv.databinding.ItemSearchResultAlbumBinding

class LibraryPlayListAdapter :
    ListAdapter<LibraryPlaylistAttributesModel, ItemViewholder>(
        LibraryPlaylistDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder =
        ItemViewholder.from(parent)

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ItemViewholder(val binding: ItemLibraryPlaylistBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: LibraryPlaylistAttributesModel) = with(binding) {
        binding.playlistattribute = item
    }

    companion object {
        fun from(parent: ViewGroup): ItemViewholder {
            val binding = DataBindingUtil.inflate<ItemLibraryPlaylistBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_library_playlist,
                parent,
                false
            )
            return ItemViewholder(binding)
        }
    }
}

class LibraryPlaylistDiffCallback : DiffUtil.ItemCallback<LibraryPlaylistAttributesModel>() {
    override fun areItemsTheSame(
        oldItem: LibraryPlaylistAttributesModel,
        newItem: LibraryPlaylistAttributesModel
    ): Boolean {
        return oldItem.artwork?.url == newItem.artwork?.url
    }

    override fun areContentsTheSame(
        oldItem: LibraryPlaylistAttributesModel,
        newItem: LibraryPlaylistAttributesModel
    ): Boolean {
        return oldItem == newItem
    }
}