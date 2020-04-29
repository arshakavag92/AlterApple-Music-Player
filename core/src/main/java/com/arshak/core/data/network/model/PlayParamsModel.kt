package com.arshak.core.data.network.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Arshak Avagyan on 3/9/20.
 * Project Name: FreeIPTV
 */
@Keep
@Parcelize
data class PlayParamsModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("kind")
    val kind: String
) : Parcelable