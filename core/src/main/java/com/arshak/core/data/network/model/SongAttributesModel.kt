package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SongAttributesModel(
    @SerializedName("previews")
    val previews: List<PreviewModel>?,
    @SerializedName("artwork")
    val artwork: ArtworkModel,
    @SerializedName("artistName")
    val artistName: String,
    @SerializedName("url")
    val url: String?,
    @SerializedName("discNumber")
    val discNumber: Int,
    @SerializedName("genreNames")
    val genreNames: List<String>,
    @SerializedName("durationInMillis")
    val durationInMillis: Long,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("isrc")
    val isrc: String?,
    @SerializedName("hasLyrics")
    val hasLyrics: Boolean,
    @SerializedName("albumName")
    val albumName: String,
    @SerializedName("playParams")
    val playParams: PlayParamsModel,
    @SerializedName("trackNumber")
    val trackNumber: Int,
    @SerializedName("composerName")
    val composerName: String?
)