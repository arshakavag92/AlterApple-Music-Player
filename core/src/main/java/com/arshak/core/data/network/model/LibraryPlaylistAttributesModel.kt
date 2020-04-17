package com.arshak.core.data.network.model

import androidx.annotation.Keep

@Keep
data class LibraryPlaylistAttributesModel(
    val artwork: ArtworkModel?,
    val description: DescriptionModel?,
    val dateAdded: String,
    val name: String,
    val hasCatalog: Boolean?,
    val playParams: PlayParamsModel,
    val canEdit: Boolean
)