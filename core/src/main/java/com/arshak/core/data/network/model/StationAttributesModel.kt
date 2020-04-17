package com.arshak.core.data.network.model

import androidx.annotation.Keep

@Keep
data class StationAttributesModel(
    val artwork: ArtworkModel,
    val durationInMillis: Long,
    val editorialNotes: EditorialNotesModel,
    val episodeNumber: Int,
    val isLive: Boolean,
    val name: String,
    val url: String
)