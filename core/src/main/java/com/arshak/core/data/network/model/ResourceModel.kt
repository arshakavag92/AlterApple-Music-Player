package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */
@Keep
open class ResourceModel<T>(
    @SerializedName("href")
    val href: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("attributes")
    val attributes: T? = null,
    @SerializedName("type")
    val type: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResourceModel<*>

        if (href != other.href) return false
        if (id != other.id) return false
        if (attributes != other.attributes) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = href.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + (attributes?.hashCode() ?: 0)
        result = 31 * result + type.hashCode()
        return result
    }
}