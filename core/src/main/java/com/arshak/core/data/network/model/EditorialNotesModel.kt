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
data class EditorialNotesModel(

    @SerializedName("short")
    val short: String,
    @SerializedName("standard")
    val standard: String
) : Parcelable