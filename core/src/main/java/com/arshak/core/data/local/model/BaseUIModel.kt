package com.arshak.core.data.local.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.databinding.BaseObservable
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
open class BaseUIModel(open val id: String?) : BaseObservable(), Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BaseUIModel

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}