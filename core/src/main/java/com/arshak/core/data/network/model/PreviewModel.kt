package com.arshak.core.data.network.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */

@Keep
@Parcelize
data class PreviewModel(
    @SerializedName("artwork")
    val artwork: ArtworkModel,
    @SerializedName("url")
    val url: String
) : Parcelable