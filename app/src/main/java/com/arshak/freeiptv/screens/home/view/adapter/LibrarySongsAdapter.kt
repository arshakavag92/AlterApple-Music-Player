package com.arshak.freeiptv.screens.home.view.adapter

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.arshak.core.data.local.model.SongsUIModel
import com.arshak.core.view.widget.recyclerview.UiModelDiffUtil
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.ItemSearchResultSongBinding

class LibrarySongsAdapter :
    ListAdapter<SongsUIModel, LibrarySongsAdapter.ItemViewHolder>(UiModelDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ItemViewHolder(val binding: ItemSearchResultSongBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SongsUIModel) = with(itemView) {
            binding.songattributes = item
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val binding = DataBindingUtil.inflate<ItemSearchResultSongBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_search_result_song,
                    parent,
                    false
                )
                return ItemViewHolder(binding)
            }
        }
    }
}