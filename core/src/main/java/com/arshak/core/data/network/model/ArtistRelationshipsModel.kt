package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ArtistRelationshipsModel(
    @SerializedName("albums")
    val albums: RelationshipModel<AlbumResponseModel>,
    @SerializedName("genres")
    val genres: RelationshipModel<GenreAttributesModel>,
    @SerializedName("playlists")
    val playlists: RelationshipModel<PlaylistAttributeModel>,
    @SerializedName("station")
    val station: RelationshipModel<StationAttributesModel>
)