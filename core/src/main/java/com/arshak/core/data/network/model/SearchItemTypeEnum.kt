package com.arshak.core.data.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Arshak Avagyan on 3/9/20.
 * Project Name: FreeIPTV
 */
enum class SearchItemTypeEnum(val type: String) {
    @SerializedName("songs")
    SONGS("songs"),

    @SerializedName("artists")
    ARTISTS("artists"),

    @SerializedName("albums")
    ALBUMS("albums")
}