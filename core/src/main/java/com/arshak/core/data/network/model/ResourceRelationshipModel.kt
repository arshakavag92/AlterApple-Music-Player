package com.arshak.core.data.network.model

import com.google.gson.annotations.SerializedName

data class ResourceRelationshipModel<T, R>(
    @SerializedName("relationships")
    val relationships: R? = null
) : ResourceModel<T>()