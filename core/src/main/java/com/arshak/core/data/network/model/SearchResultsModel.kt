package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Created by Arshak Avagyan on 3/9/20.
 * Project Name: FreeIPTV
 */
@Keep
data class SearchResultsModel(
    val songs: SongsResponseModel,
    val artists: ArtistResponseModel,
    val albums: AlbumResponseModel,
    @SerializedName("music-videos")
    val music_videos: MusicVideoResponseModel
)