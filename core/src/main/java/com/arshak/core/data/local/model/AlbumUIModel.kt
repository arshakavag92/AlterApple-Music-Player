package com.arshak.core.data.local.model

import androidx.annotation.Keep
import androidx.databinding.ObservableField
import com.arshak.core.data.network.model.*
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class AlbumUIModel(
    override val id: String,
    val albumName: String?,
    val artistName: String?,
    val artwork: ArtworkModel?,
    val contentRating: String?,
    val copyright: String?,
    val editorialNotes: EditorialNotesModel?,
    val genreNames: List<String>?,
    val isComplete: Boolean,
    val isSingle: Boolean,
    val name: String,
    val playParams: PlayParamsModel,
    val recordLabel: String?,
    val releaseDate: String?,
    val trackCount: Int,
    val url: String?,
    val isMasteredForItunes: Boolean

) : BaseUIModel(id) {

    @IgnoredOnParcel
    var trackCountAndLength = ObservableField<String>()

    companion object {

        fun from(resourceModel: ResourceModel<AlbumAttributesModel>) = AlbumUIModel(
            id = resourceModel.id,
            albumName = resourceModel.attributes!!.albumName,
            artistName = resourceModel.attributes.artistName,
            artwork = resourceModel.attributes.artwork,
            contentRating = resourceModel.attributes.contentRating,
            copyright = resourceModel.attributes.copyright,
            editorialNotes = resourceModel.attributes.editorialNotes,
            genreNames = resourceModel.attributes.genreNames,
            isComplete = resourceModel.attributes.isComplete,
            isSingle = resourceModel.attributes.isSingle,
            name = resourceModel.attributes.name,
            playParams = resourceModel.attributes.playParams,
            recordLabel = resourceModel.attributes.recordLabel,
            releaseDate = resourceModel.attributes.releaseDate,
            trackCount = resourceModel.attributes.trackCount,
            url = resourceModel.attributes.url,
            isMasteredForItunes = resourceModel.attributes.isMasteredForItunes
        )
    }
}