package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class StoreFrontAttributesModel(
    @SerializedName("defaultLanguageTag")
    val defaultLanguageTag: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("explicitContentPolicy")
    val explicitContentPolicy: String = "",
    @SerializedName("supportedLanguageTags")
    val supportedLanguageTags: List<String> = emptyList()
)