package com.arshak.core.data.network.model

import androidx.annotation.Keep

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */
@Keep
data class ErrorModel(
    val code: String,
    val details: String,
    val id: String,
    val source: Source,
    val status: String,
    val title: String
)