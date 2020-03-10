package com.arshak.core.data.network.model

import androidx.annotation.Keep

/**
 * Created by Arshak Avagyan on 3/9/20.
 * Project Name: FreeIPTV
 */

@Keep
data class MusicVideoModelAttribute(
    val albumName: String,
    val artistName: String,
    val artwork: ArtworkModel,
    val contentRating: String,
    val durationInMillis: Long,
    val editorialNotes: EditorialNotesModel,
    val genreNames: List<String>,
    val isrc: String,
    val name: String,
    val playParams: PlayParamsModel,
    val previews: List<PreviewModel>,
    val releaseDate: String,
    val trackNumber: Int,
    val url: String,
    val videoSubType: String,
    val hasHDR: Boolean,
    val has4K: Boolean
)