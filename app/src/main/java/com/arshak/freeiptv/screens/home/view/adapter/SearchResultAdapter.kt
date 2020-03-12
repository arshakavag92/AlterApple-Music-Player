package com.arshak.freeiptv.screens.home.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.arshak.core.data.network.model.SearchResponseItemUiModel
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.ItemSearchSubcategoryBinding

class SearchResultAdapter :
    ListAdapter<SearchResponseItemUiModel<*>, SearchResultAdapter.SearchResultUiModelViewHolder>(
        SearchResultUiModelDiffCallback()
    ) {

    lateinit var callback: SearchResultItemCallback

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchResultUiModelViewHolder = SearchResultUiModelViewHolder.from(parent)

    override fun onBindViewHolder(holder: SearchResultUiModelViewHolder, position: Int) =
        holder.bind(getItem(position))

    class SearchResultUiModelViewHolder constructor(val binding: ItemSearchSubcategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchResponseItemUiModel<*>) = with(itemView) {
            binding.model = item
        }

        companion object {
            fun from(parent: ViewGroup): SearchResultUiModelViewHolder {
                val binding = DataBindingUtil.inflate<ItemSearchSubcategoryBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_search_subcategory,
                    parent,
                    false
                )
                return SearchResultUiModelViewHolder(binding)
            }
        }
    }
}

class SearchResultUiModelDiffCallback() : DiffUtil.ItemCallback<SearchResponseItemUiModel<*>>() {
    override fun areItemsTheSame(
        oldItem: SearchResponseItemUiModel<*>,
        newItem: SearchResponseItemUiModel<*>
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: SearchResponseItemUiModel<*>,
        newItem: SearchResponseItemUiModel<*>
    ): Boolean = oldItem == newItem
}

interface SearchResultItemCallback {
    fun onItemClick()
    fun onOptionsClicked()
    fun onMoreClicked()
}
