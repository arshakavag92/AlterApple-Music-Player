package com.arshak.core.data.local.model

import androidx.annotation.Keep
import com.arshak.core.data.network.model.*

@Keep
data class PlaylistUIModel(
    override val id: String?,
    val artwork: ArtworkModel,
    val curatorName: String,
    val description: EditorialNotesModel,
    val lastModifiedDate: String?,
    val name: String,
    val playParams: PlayParamsModel,
    val playlistType: String,
    val url: String?
) : BaseUIModel(id) {
    companion object {
        fun from(resource: ResourceModel<PlaylistAttributeModel>) = PlaylistUIModel(
            id = resource.id,
            artwork = resource.attributes!!.artwork,
            curatorName = resource.attributes.curatorName,
            description = resource.attributes.description,
            lastModifiedDate = resource.attributes.lastModifiedDate,
            name = resource.attributes.name,
            playParams = resource.attributes.playParams,
            playlistType = resource.attributes.playlistType,
            url = resource.attributes.url
        )
    }
}