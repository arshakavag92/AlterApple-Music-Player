package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Created by Arshak Avagyan on 2/25/20.
 * Project Name: FreeIPTV
 */
@Keep
data class SearchResponseModel(
    @SerializedName("results")
    val results: SearchResultsModel
) : ResponseRootModel<Nothing>()