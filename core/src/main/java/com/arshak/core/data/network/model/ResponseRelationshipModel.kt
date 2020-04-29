package com.arshak.core.data.network.model

import com.google.gson.annotations.SerializedName

open class ResponseRelationshipModel<T, R>(
    @SerializedName("data")
    open val data: List<ResourceRelationshipModel<T, R>> = emptyList(),
    @SerializedName("href")
    val href: String = "",
    @SerializedName("meta")
    val meta: RelationshipModel<*>? = null,
    @SerializedName("error")
    val error: List<ErrorModel>? = emptyList(),
    @SerializedName("next")
    val next: String = ""
)