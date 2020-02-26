package com.arshak.core.data.network.model


import com.google.gson.annotations.SerializedName

data class SearchHintResponseModel(
    @SerializedName("results")
    val results: SearchHintResultsModel
)