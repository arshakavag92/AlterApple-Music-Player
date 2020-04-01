package com.arshak.core.data.network.model

import androidx.annotation.Keep

@Keep
data class LibrarySearchResponseModel(val results: LibrarySearchResultsModel) :
    ResponseRootModel<Nothing>()