package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */
@Keep
data class ErrorModel(
    @SerializedName("code")
    val code: String,
    @SerializedName("details")
    val details: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("source")
    val source: Source,
    @SerializedName("status")
    val status: String,
    @SerializedName("title")
    val title: String
)