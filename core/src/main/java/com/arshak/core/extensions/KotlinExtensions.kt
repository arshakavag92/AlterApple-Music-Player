package com.arshak.core.extensions

object KotlinExtensions {

    fun Long.toMinutes() = ((this / 1000) / 60).toInt()
}