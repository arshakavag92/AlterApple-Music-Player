package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */
@Keep
data class ArtworkModel(
    @SerializedName("bgColor")
    val bgColor: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("width")
    val width: Int,
    @SerializedName("textColor1")
    val textColor1: String,
    @SerializedName("textColor2")
    val textColor2: String,
    @SerializedName("textColor3")
    val textColor3: String,
    @SerializedName("textColor4")
    val textColor4: String,
    @SerializedName("url")
    val url: String
)