package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Created by Arshak Avagyan on 2/28/20.
 * Project Name: FreeIPTV
 */
@Keep
data class ArtistModel<T>(
    @SerializedName("type")
    val type: String,
    @SerializedName("attributes")
    val attributes: Attributes,
    @SerializedName("relationships")
    val relationships: RelationshipModel<T>

) {
    data class Attributes(
        @SerializedName("genreNames")
        val genreNames: List<String>,
        @SerializedName("name")
        val name: String, val url: String
    )
}