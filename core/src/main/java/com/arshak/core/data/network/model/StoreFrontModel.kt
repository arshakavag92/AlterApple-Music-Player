package com.arshak.core.data.network.model

import androidx.annotation.Keep

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */

@Keep
data class StoreFrontModel(val type: String, val attributes: List<AttriButeModel>) {

    @Keep
    data class AttriButeModel(
        val defaultLanguageTag: String,
        val name: String,
        val supportedLanguageTags: List<String>
    )
}