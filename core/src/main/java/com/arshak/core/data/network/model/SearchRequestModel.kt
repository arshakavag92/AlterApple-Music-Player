package com.arshak.core.data.network.model

import androidx.annotation.Keep

/**
 * Created by Arshak Avagyan on 2/25/20.
 * Project Name: FreeIPTV
 */
@Keep
data class SearchRequestModel(
    val term: String,
    val localisation: String = "",
    val limit: Int? = null,
    val offset: String? = "",
    val types: List<String>? = emptyList()
)