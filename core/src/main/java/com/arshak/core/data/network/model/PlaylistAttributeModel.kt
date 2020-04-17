package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PlaylistAttributeModel(
    @SerializedName("artwork")
    val artwork: ArtworkModel,
    @SerializedName("curatorName")
    val curatorName: String,
    @SerializedName("description")
    val description: EditorialNotesModel,
    @SerializedName("lastModifiedDate")
    val lastModifiedDate: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("playParams")
    val playParams: PlayParamsModel,
    @SerializedName("playlistType")
    val playlistType: String,
    @SerializedName("url")
    val url: String
)