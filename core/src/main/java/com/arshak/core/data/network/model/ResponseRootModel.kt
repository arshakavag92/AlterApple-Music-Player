package com.arshak.core.data.network.model

import androidx.annotation.Keep

/**
 * Created by Arshak Avagyan on 2020-02-24.
 * Project Name: FreeIPTV
 */

@Keep
open class ResponseRootModel<T>(
    val data: List<ResourceModel<T>> = emptyList(),
    val href: String = "",
    val meta: RelationshipModel<*>? = null,
    val error: List<ErrorModel>? = emptyList(),
    val next: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResponseRootModel<*>

        if (data != other.data) return false
        if (href != other.href) return false
        if (meta != other.meta) return false
        if (error != other.error) return false
        if (next != other.next) return false

        return true
    }

    override fun hashCode(): Int {
        var result = data.hashCode()
        result = 31 * result + href.hashCode()
        result = 31 * result + meta.hashCode()
        result = 31 * result + error.hashCode()
        result = 31 * result + next.hashCode()
        return result
    }
}