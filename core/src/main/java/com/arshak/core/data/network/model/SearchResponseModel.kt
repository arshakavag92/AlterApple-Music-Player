package com.arshak.core.data.network.model

import androidx.annotation.Keep

/**
 * Created by Arshak Avagyan on 2/25/20.
 * Project Name: FreeIPTV
 */
@Keep
data class SearchResponseModel(val results: SearchResultsModel) : ResponseRootModel<Nothing>()