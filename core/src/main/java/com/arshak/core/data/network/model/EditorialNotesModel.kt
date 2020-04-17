package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */
@Keep
data class EditorialNotesModel(

    @SerializedName("short")
    val short: String,
    @SerializedName("standard")
    val standard: String
)