package com.arshak.core.data.local.model

import androidx.annotation.Keep
import androidx.databinding.BaseObservable
import com.arshak.core.data.network.model.*
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
class SongsUIModel(
    override val id: String,
    val previews: List<PreviewModel>?,
    val artwork: ArtworkModel,
    val artistName: String,
    val url: String?,
    val discNumber: Int,
    val genreNames: List<String>,
    val durationInMillis: Long,
    val releaseDate: String?,
    val name: String,
    val isrc: String?,
    val hasLyrics: Boolean,
    val albumName: String,
    val playParams: PlayParamsModel,
    val trackNumber: String,
    val composerName: String?
) : BaseUIModel(id) {
    companion object {
        fun from(resourceModel: ResourceModel<SongAttributesModel>) =
            SongsUIModel(
                id = resourceModel.id,
                previews = resourceModel.attributes!!.previews,
                artwork = resourceModel.attributes.artwork,
                artistName = resourceModel.attributes.artistName,
                url = resourceModel.attributes.url,
                discNumber = resourceModel.attributes.discNumber,
                genreNames = resourceModel.attributes.genreNames,
                durationInMillis = resourceModel.attributes.durationInMillis,
                releaseDate = resourceModel.attributes.releaseDate,
                name = resourceModel.attributes.name,
                isrc = resourceModel.attributes.isrc,
                hasLyrics = resourceModel.attributes.hasLyrics,
                albumName = resourceModel.attributes.albumName,
                playParams = resourceModel.attributes.playParams,
                trackNumber = resourceModel.attributes.trackNumber.toString(),
                composerName = resourceModel.attributes.composerName
            )
    }
}
