package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.arshak.core.databinding.common.recyclerview.ListItemViewModel
import com.google.gson.annotations.SerializedName

@Keep
data class SearchHintResultsModel(
    @SerializedName("terms")
    val terms: List<String>
) : ListItemViewModel()