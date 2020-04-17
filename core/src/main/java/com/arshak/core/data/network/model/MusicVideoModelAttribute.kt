package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Created by Arshak Avagyan on 3/9/20.
 * Project Name: FreeIPTV
 */

@Keep
data class MusicVideoModelAttribute(
    @SerializedName("albumName")
    val albumName: String,
    @SerializedName("artistName")
    val artistName: String,
    @SerializedName("artwork")
    val artwork: ArtworkModel,
    @SerializedName("contentRating")
    val contentRating: String,
    @SerializedName("durationInMillis")
    val durationInMillis: Long,
    @SerializedName("editorialNotes")
    val editorialNotes: EditorialNotesModel,
    @SerializedName("genreNames")
    val genreNames: List<String>,
    @SerializedName("isrc")
    val isrc: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("playParams")
    val playParams: PlayParamsModel,
    @SerializedName("previews")
    val previews: List<PreviewModel>,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("trackNumber")
    val trackNumber: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("videoSubType")
    val videoSubType: String,
    @SerializedName("hasHDR")
    val hasHDR: Boolean,
    @SerializedName("has4K")
    val has4K: Boolean
)