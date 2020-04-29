package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
enum class AlbumRelationshipEnum(val value: String) {

    @SerializedName("songs")
    TRACKS("tracks"),

    @SerializedName("artists")
    ARTISTS("artists")
}