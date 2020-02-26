package com.arshak.core.data.network.model

import androidx.annotation.Keep

@Keep
data class StoreFrontAttributes(
    val defaultLanguageTag: String = "",
    val explicitContentPolicy: String = "",
    val name: String = "",
    val supportedLanguageTags: List<String> = emptyList()
)