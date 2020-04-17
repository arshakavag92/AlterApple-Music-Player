package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Created by Arshak Avagyan on 2/25/20.
 * Project Name: FreeIPTV
 */
@Keep
data class SearchRequestModel(
    @SerializedName("term")
    val term: String,
    @SerializedName("localisation")
    val localisation: String = "",
    @SerializedName("limit")
    val limit: Int? = null,
    @SerializedName("offset")
    val offset: String? = "",
    @SerializedName("types")
    val types: List<String>? = emptyList()
)