package com.arshak.freeiptv.screens.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.arshak.freeiptv.R
import com.arshak.freeiptv.databinding.ItemSeachHintBinding

class SearchHintAdapter :
    ListAdapter<String, SearchHintAdapter.StringViewHolder>(StringDiffCallback()) {

    lateinit var callBack: SearchCallBack

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder =
        StringViewHolder.from(parent, callBack)

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) =
        holder.bind(getItem(position))

    class StringViewHolder(
        private val binding: ItemSeachHintBinding,
        private val callback: SearchCallBack
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) = with(itemView) {
            binding.searchhint = item
            binding.callback = callback
        }

        //For inflating the layout in onCreateViewHolder()
        //TODO: Make sure your layout name is resolved
        companion object {
            fun from(parent: ViewGroup, callback: SearchCallBack): StringViewHolder {
                val binding: ItemSeachHintBinding =
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_seach_hint,
                        parent,
                        false
                    )
                return StringViewHolder(binding, callback)
            }
        }
    }

    class StringDiffCallback() : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem
    }

    interface SearchCallBack {
        fun onSearchClicked(term: String)
    }
}

