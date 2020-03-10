package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SearchHintResultsModel(
    @SerializedName("terms")
    val terms: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SearchHintResultsModel

        if (!terms.contentEquals(other.terms)) return false

        return true
    }

    override fun hashCode(): Int {
        return terms.contentHashCode()
    }
}