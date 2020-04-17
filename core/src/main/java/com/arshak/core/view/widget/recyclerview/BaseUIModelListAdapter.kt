package com.arshak.core.view.widget.recyclerview

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arshak.core.data.local.model.BaseUIModel

abstract class BaseUIModelListAdapter<M : BaseUIModel, V : RecyclerView.ViewHolder> :
    ListAdapter<M, V>(UiModelDiffUtil())