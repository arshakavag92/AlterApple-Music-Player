package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LibrarySearchResultsModel(
    @SerializedName("library-songs")
    val songs: SongsResponseModel?,
    @SerializedName("library-artists")
    val artists: ArtistResponseModel?,
    @SerializedName("library-albums")
    val albums: AlbumResponseModel?
)