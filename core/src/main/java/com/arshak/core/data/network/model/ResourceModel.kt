package com.arshak.core.data.network.model

import androidx.annotation.Keep

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */
@Keep
data class ResourceModel<T>(
    val href: String,
    val id: String,
    val attributes: T? = null,
    val type: String
)