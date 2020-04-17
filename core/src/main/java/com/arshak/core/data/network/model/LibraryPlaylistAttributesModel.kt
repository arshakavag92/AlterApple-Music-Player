package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LibraryPlaylistAttributesModel(
    @SerializedName("artwork")
    val artwork: ArtworkModel?,
    @SerializedName("description")
    val description: DescriptionModel?,
    @SerializedName("dateAdded")
    val dateAdded: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("hasCatalog")
    val hasCatalog: Boolean?,
    @SerializedName("playParams")
    val playParams: PlayParamsModel,
    @SerializedName("canEdit")
    val canEdit: Boolean
)