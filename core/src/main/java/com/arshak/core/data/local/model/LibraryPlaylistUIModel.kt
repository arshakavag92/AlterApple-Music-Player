package com.arshak.core.data.local.model

import com.arshak.core.data.network.model.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class LibraryPlaylistUIModel(
    override val id: String?,
    val artwork: ArtworkModel?,
    val description: DescriptionModel?,
    val dateAdded: String,
    val name: String,
    val hasCatalog: Boolean?,
    val playParams: PlayParamsModel,
    val canEdit: Boolean
) : BaseUIModel(id) {

    companion object {
        fun from(resource: ResourceModel<LibraryPlaylistAttributesModel>) =
            LibraryPlaylistUIModel(
                id = resource.id,
                artwork = resource.attributes!!.artwork,
                description = resource.attributes.description,
                dateAdded = resource.attributes.dateAdded,
                name = resource.attributes.name,
                hasCatalog = resource.attributes.hasCatalog,
                playParams = resource.attributes.playParams,
                canEdit = resource.attributes.canEdit
            )
    }
}