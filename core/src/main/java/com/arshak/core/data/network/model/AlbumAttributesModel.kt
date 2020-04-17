package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Created by Arshak Avagyan on 3/9/20.
 * Project Name: FreeIPTV
 */
@Keep
data class AlbumAttributesModel(
    @SerializedName("albumName")
    val albumName: String,
    @SerializedName("artistName")
    val artistName: String,
    @SerializedName("artwork")
    val artwork: ArtworkModel,
    @SerializedName("contentRating")
    val contentRating: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("editorialNotes")
    val editorialNotes: EditorialNotesModel,
    @SerializedName("genreNames")
    val genreNames: List<String>,
    @SerializedName("isComplete")
    val isComplete: Boolean,
    @SerializedName("isSingle")
    val isSingle: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("playParams")
    val playParams: PlayParamsModel,
    @SerializedName("recordLabel")
    val recordLabel: String,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("trackCount")
    val trackCount: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("isMasteredForItunes")
    val isMasteredForItunes: Boolean
)