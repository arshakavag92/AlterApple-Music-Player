package com.arshak.core.data.network.model

import androidx.annotation.Keep

/**
 * Created by Arshak Avagyan on 3/9/20.
 * Project Name: FreeIPTV
 */
@Keep
data class SearchResponseItemUiModel<T>(
    val type: String,
    val name: String,
    val href: String,
    val id: String,
    val data: List<ResourceModel<T>>
)