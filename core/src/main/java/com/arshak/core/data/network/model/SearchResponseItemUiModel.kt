package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Created by Arshak Avagyan on 3/9/20.
 * Project Name: FreeIPTV
 */
@Keep
data class SearchResponseItemUiModel<T>(
    @SerializedName("type")
    val type: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("data")
    val data: List<ResourceModel<T>>
)