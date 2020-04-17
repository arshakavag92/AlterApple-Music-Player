package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class StationAttributesModel(
    @SerializedName("artwork")
    val artwork: ArtworkModel,
    @SerializedName("durationInMillis")
    val durationInMillis: Long,
    @SerializedName("editorialNotes")
    val editorialNotes: EditorialNotesModel,
    @SerializedName("episodeNumber")
    val episodeNumber: Int,
    @SerializedName("isLive")
    val isLive: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)