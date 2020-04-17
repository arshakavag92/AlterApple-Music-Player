package com.arshak.core.data.local.model

import androidx.annotation.Keep
import com.arshak.core.data.network.model.ArtistModel
import com.arshak.core.data.network.model.EditorialNotesModel
import com.arshak.core.data.network.model.ResourceModel
import com.google.gson.annotations.SerializedName

@Keep
data class ArtistUIModel(
    override val id: String?,
    val attributes: Attributes
) : BaseUIModel(id) {
    @Keep
    data class Attributes(
        val genreNames: MutableList<String>? = mutableListOf(),
        val name: String,
        val url: String?,
        val editorialNotes: EditorialNotesUIModel?
    ) {
        companion object {
            fun from(attributes: ArtistModel.Attributes) =
                Attributes(
                    genreNames = attributes.genreNames,
                    name = attributes.name,
                    url = attributes.url,
                    editorialNotes = EditorialNotesUIModel.from(attributes.editorialNotes)
                )
        }
    }

    companion object {
        fun from(artistModel: ResourceModel<ArtistModel.Attributes>) =
            ArtistUIModel(
                id = artistModel.id,
                attributes = Attributes.from(artistModel.attributes!!)
            )
    }
}