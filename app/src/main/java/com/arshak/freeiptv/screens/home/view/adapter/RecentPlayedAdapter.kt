package com.arshak.freeiptv.screens.home.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arshak.core.data.network.model.AlbumAttributesModel
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.ItemSearchResultAlbumBinding

class RecentPlayedAdapter :
    ListAdapter<AlbumAttributesModel, RecentPlayedAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: RecentPlayedAdapter.ItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ItemViewHolder(val binding: ItemSearchResultAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AlbumAttributesModel) = with(itemView) {
            binding.albumattributes = item
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val binding = DataBindingUtil.inflate<ItemSearchResultAlbumBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_search_result_album,
                    parent,
                    false
                )
                return ItemViewHolder(binding)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<AlbumAttributesModel>() {
    override fun areItemsTheSame(
        oldItem: AlbumAttributesModel,
        newItem: AlbumAttributesModel
    ): Boolean =
        oldItem.url == newItem.url

    override fun areContentsTheSame(
        oldItem: AlbumAttributesModel,
        newItem: AlbumAttributesModel
    ): Boolean = oldItem == newItem
}