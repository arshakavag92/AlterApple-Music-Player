package com.arshak.core.data.network.model

import com.google.gson.annotations.SerializedName

enum class LibraryTimeTypes(val type: String) {

    @SerializedName("library-albums")
    ALBUMS("library-albums"),
}