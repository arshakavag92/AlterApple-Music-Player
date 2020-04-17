package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LibrarySearchResponseModel(
    @SerializedName("results")
    val results: LibrarySearchResultsModel
) : ResponseRootModel<Nothing>()