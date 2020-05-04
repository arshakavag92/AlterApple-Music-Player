package com.arshak.core.data.network.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class DescriptionModel(
    @SerializedName("standard")
    val standard: String,
    @SerializedName("short")
    val short: String
) : Parcelable