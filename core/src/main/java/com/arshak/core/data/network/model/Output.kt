package com.arshak.core.data.network.model

import androidx.annotation.Keep

@Keep
sealed class Output<out T : Any> {
    @Keep
    data class Success<out T : Any>(val output: T) : Output<T>()

    @Keep
    data class Error(val exception: Exception) : Output<Nothing>()
}