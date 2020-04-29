package com.arshak.core.data.local.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.arshak.core.data.network.model.ArtistAttributesModel
import com.arshak.core.data.network.model.ResourceModel
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class ArtistUIModel(
    override val id: String?,
    val attributes: Attributes
) : BaseUIModel(id) {
    @Keep
    @Parcelize
    data class Attributes(
        val genreNames: MutableList<String>? = mutableListOf(),
        val name: String,
        val url: String?,
        val editorialNotes: EditorialNotesUIModel?
    ) : Parcelable {
        companion object {
            fun from(attributes: ArtistAttributesModel) =
                Attributes(
                    genreNames = attributes.genreNames,
                    name = attributes.name,
                    url = attributes.url,
                    editorialNotes = EditorialNotesUIModel.from(attributes.editorialNotes)
                )
        }
    }

    companion object {
        fun from(artistModel: ResourceModel<ArtistAttributesModel>) =
            ArtistUIModel(
                id = artistModel.id,
                attributes = Attributes.from(artistModel.attributes!!)
            )
    }
}