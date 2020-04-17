package com.arshak.core.data.network.model

import com.google.gson.annotations.SerializedName

enum class LibraryTimeTypesEnum(val type: String) {

    @SerializedName("library-albums")
    ALBUMS("library-albums"),

    @SerializedName("library-playlists")
    PLAYLISTS("library-playlists"),

    @SerializedName("library-artists")
    ARTISTS("library-artists"),

    @SerializedName("library-songs")
    SONGS("library-songs")
}