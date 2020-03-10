package com.arshak.core.data.network.model

import androidx.annotation.Keep

@Keep
data class SongAttributesModel(
    val previews: List<PreviewModel>,
    val artwork: ArtworkModel,
    val artistName: String,
    val url: String,
    val discNumber: Int,
    val genreNames: List<String>,
    val durationInMillis: Long,
    val releaseDate: String,
    val name: String,
    val isrc: String,
    val hasLyrics: Boolean,
    val albumName: String,
    val playParams: PlayParamsModel,
    val trackNumber: Int,
    val composerName: String
)