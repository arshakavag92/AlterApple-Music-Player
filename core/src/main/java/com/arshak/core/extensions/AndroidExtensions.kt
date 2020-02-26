package com.arshak.core.extensions

import android.app.Activity
import android.content.SharedPreferences
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

/**
 * Created by Arshak Avagyan on 2020-02-14.
 * Project Name: FreeIPTV
 */

// SharedPreference etxensions
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

// LifecycleOwner extensions
fun Activity.toast(message: String, length: Int) {
    Toast.makeText(this, message, length).show()
}

fun Activity.toast(messageResourceId: Int, length: Int) {
    Toast.makeText(this, messageResourceId, length).show()
}

fun Fragment.showToast(message: String, length: Int) {
    activity!!.toast(message, length)
}

fun Fragment.showToast(messageResourceId: Int, length: Int) {
    activity!!.toast(messageResourceId, length)
}

fun Fragment.longToast(message: String) {
    activity!!.longToast(message)
}

fun Fragment.shortToast(messageResourceId: Int) {
    activity!!.shortToast(messageResourceId)
}

fun Fragment.shortToast(message: String) {
    activity!!.shortToast(message)
}

fun Activity.shortToast(message: String) {
    toast(message, Toast.LENGTH_SHORT)
}

fun Activity.longToast(message: String) {
    toast(message, Toast.LENGTH_LONG)
}

fun Activity.shortToast(message: Int) {
    toast(message, Toast.LENGTH_SHORT)
}

fun Activity.longToast(messageResourceId: Int) {
    toast(messageResourceId, Toast.LENGTH_LONG)
}


