package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DescriptionModel(
    @SerializedName("standard")
    val standard: String,
    @SerializedName("short")
    val short: String
)