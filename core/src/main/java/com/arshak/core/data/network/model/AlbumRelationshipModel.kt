package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AlbumRelationshipModel(
    @SerializedName("tracks")
    val tracks: SongsResponseModel,
    @SerializedName("artists")
    val artists: ArtistResponseModel
)