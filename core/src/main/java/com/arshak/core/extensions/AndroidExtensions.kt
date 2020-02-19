package com.arshak.core.extensions

import android.content.SharedPreferences

/**
 * Created by Arshak Avagyan on 2020-02-14.
 * Project Name: FreeIPTV
 */
fun SharedPreferences.Editor.put(pair: Pair<String, Any>) {
    val key = pair.first
    val value = pair.second
    when (value) {
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Boolean -> putBoolean(key, value)
        is Long -> putLong(key, value)
        is Float -> putFloat(key, value)
        else -> error("Only primitive types can be stored in SharedPreferences")
    }
}

fun SharedPreferences.delete(key: String) = edit().remove(key).apply()

fun SharedPreferences.put(pair: Pair<String, Any>) = edit().put(pair)

fun SharedPreferences.clearValues() {
    if (fileExist) {
        edit().clear().apply()
    }
}

val SharedPreferences.fileExist: Boolean
    get() = this.all.isNotEmpty()


