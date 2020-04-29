package com.arshak.freeiptv.screens.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arshak.core.data.local.model.AlbumUIModel
import com.arshak.core.view.widget.recyclerview.BaseUIModelListAdapter
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.ItemSearchResultAlbumBinding
import com.arshak.freeiptv.screens.home.widget.OnAlbumDetailsClickListener

class RecentPlayedAdapter :
    BaseUIModelListAdapter<AlbumUIModel, RecentPlayedAdapter.ItemViewHolder>() {

    var onAlbumDetailsClickListener: OnAlbumDetailsClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder.from(parent, onAlbumDetailsClickListener)

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ItemViewHolder(
        val binding: ItemSearchResultAlbumBinding,
        private val onAlbumDetailsClickListener: OnAlbumDetailsClickListener?
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AlbumUIModel) = with(itemView) {
            binding.albumattributes = item
            binding.onalbumclickedlistener = onAlbumDetailsClickListener
        }

        companion object {
            fun from(
                parent: ViewGroup,
                onAlbumDetailsClickListener: OnAlbumDetailsClickListener?
            ): ItemViewHolder {
                val binding = DataBindingUtil.inflate<ItemSearchResultAlbumBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_search_result_album,
                    parent,
                    false
                )
                return ItemViewHolder(binding, onAlbumDetailsClickListener)
            }
        }
    }
}