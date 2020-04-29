package com.arshak.core.data.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
open class ResponseWithRelationshipModel<T, R> : ResponseRootModel<T>()