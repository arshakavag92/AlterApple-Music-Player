package com.arshak.freeiptv.screens.home.view.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.arshak.core.data.local.model.ArtistUIModel
import com.arshak.core.view.widget.recyclerview.BaseViewModelListAdapter
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.ItemSearchResultArtistBinding
import com.arshak.freeiptv.screens.home.viewmodel.MyMusicViewModel

class LibraryArtistsAdapter(private val activityViewModel: MyMusicViewModel) :
    BaseViewModelListAdapter<ArtistUIModel, LibraryArtistsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), activityViewModel)
    }

    class ItemViewHolder(val binding: ItemSearchResultArtistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ArtistUIModel, viewModel: MyMusicViewModel) = with(itemView) {
            binding.apply {
                artistuimodel = item
                viewmodel = viewModel
            }
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val binding = DataBindingUtil.inflate<ItemSearchResultArtistBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_search_result_artist,
                    parent,
                    false
                )
                return ItemViewHolder(binding)
            }
        }
    }
}