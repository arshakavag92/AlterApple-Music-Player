package com.arshak.core.view.widget.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.arshak.core.data.local.model.ArtistUIModel
import com.arshak.core.data.local.model.BaseUIModel

class UiModelDiffUtil<T : BaseUIModel> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(
        oldItem: T,
        newItem: T
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: T,
        newItem: T
    ): Boolean = oldItem == newItem
}