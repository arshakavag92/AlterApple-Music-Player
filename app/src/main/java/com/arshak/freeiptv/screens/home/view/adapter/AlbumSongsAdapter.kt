package com.arshak.freeiptv.screens.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arshak.core.data.local.model.SongsUIModel
import com.arshak.core.view.widget.recyclerview.BaseUIModelListAdapter
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.ItemAlbumTracksBinding

class AlbumSongsAdapter :
    BaseUIModelListAdapter<SongsUIModel, AlbumSongsAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ItemViewHolder(val binding: ItemAlbumTracksBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SongsUIModel) = with(itemView) {
            binding.songuimodel = item
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val binding = DataBindingUtil.inflate<ItemAlbumTracksBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_album_tracks,
                    parent,
                    false
                )
                return ItemViewHolder(binding)
            }
        }
    }
}