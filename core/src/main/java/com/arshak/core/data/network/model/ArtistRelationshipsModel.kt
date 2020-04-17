package com.arshak.core.data.network.model

import androidx.annotation.Keep

@Keep
data class ArtistRelationshipsModel(
    val albums: RelationshipModel<AlbumResponseModel>,
    val genres: RelationshipModel<GenreAttributesModel>,
    val playlists: RelationshipModel<PlaylistAttributeModel>,
    val station: RelationshipModel<StationAttributesModel>
)