package com.arshak.core.data.network.model

import androidx.annotation.Keep

@Keep
data class PlaylistAttributeModel(
    val artwork: ArtworkModel,
    val curatorName: String,
    val description: EditorialNotesModel,
    val lastModifiedDate: String,
    val name: String,
    val playParams: PlayParamsModel,
    val playlistType: String,
    val url: String
)