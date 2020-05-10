package com.arshak.freeiptv.screens.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arshak.core.data.local.model.AlbumUIModel
import com.arshak.core.view.widget.recyclerview.BaseUIModelListAdapter
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.ItemArtistAlbumBinding
import com.arshak.freeiptv.screens.home.widget.OnAlbumDetailsClickListener

class MoreAlbumsAdapter :
    BaseUIModelListAdapter<AlbumUIModel, MoreAlbumsAdapter.ItemViewHolder>() {

    lateinit var onAlbumDetailsClickListener: OnAlbumDetailsClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(getItem(position), onAlbumDetailsClickListener)

    class ItemViewHolder(
        val binding: ItemArtistAlbumBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: AlbumUIModel,
            onAlbumDetailsClickListener: OnAlbumDetailsClickListener?
        ) = with(itemView) {
            binding.albumuimodel = item
            binding.onalbumclickedlistener = onAlbumDetailsClickListener
        }

        companion object {
            fun from(
                parent: ViewGroup
            ): ItemViewHolder {
                val binding = DataBindingUtil.inflate<ItemArtistAlbumBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_artist_album,
                    parent,
                    false
                )
                return ItemViewHolder(binding)
            }
        }
    }
}