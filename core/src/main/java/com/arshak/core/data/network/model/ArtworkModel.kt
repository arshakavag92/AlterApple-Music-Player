package com.arshak.core.data.network.model

import androidx.annotation.Keep

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */
@Keep
data class ArtworkModel(
    val bgColor: String,
    val height: Int,
    val width: Int,
    val textColor1: String,
    val textColor2: String,
    val textColor3: String,
    val textColor4: String,
    val url: String
)