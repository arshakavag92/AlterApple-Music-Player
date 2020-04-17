package com.arshak.core.data.network.model

import com.google.gson.annotations.SerializedName

data class ArtistAttributesModel(
    @SerializedName("genreNames")
    val genreNames: MutableList<String> = mutableListOf(),
    @SerializedName("name")
    val name: String, val url: String,
    @SerializedName("editorialNotes")
    val editorialNotes: EditorialNotesModel
)