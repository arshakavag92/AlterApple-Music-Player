package com.arshak.core.data.network.model

import androidx.annotation.Keep

/**
 * Created by Arshak Avagyan on 3/9/20.
 * Project Name: FreeIPTV
 */
@Keep
data class AlbumAttributesModel(
    val albumName: String,
    val artistName: String,
    val artwork: ArtworkModel,
    val contentRating: String,
    val copyright: String,
    val editorialNotes: EditorialNotesModel,
    val genreNames: List<String>,
    val isComplete: Boolean,
    val isSingle: Boolean,
    val name: String,
    val playParams: PlayParamsModel,
    val recordLabel: String,
    val releaseDate: String,
    val trackCount: Int,
    val url: String,
    val isMasteredForItunes: Boolean
)