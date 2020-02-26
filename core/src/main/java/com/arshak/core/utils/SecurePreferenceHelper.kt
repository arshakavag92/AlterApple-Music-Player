package com.arshak.core.utils

import android.app.Application
import android.app.KeyguardManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.arshak.core.extensions.clearValues
import com.arshak.core.extensions.put
import com.securepreferences.SecurePreferences


/**
 * Created by Arshak Avagyan on 2020-02-14.
 * Project Name: FreeIPTV
 */

class SecurePreferenceHelper private constructor(context: Application) {

    var applicationContext: Application = context

    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    // Step 2: Initialize/open an instance of EncryptedSharedPreferences
    val sharedPreferences = EncryptedSharedPreferences.create(
        "app_preferences",
        masterKeyAlias,
        applicationContext,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    inline fun <reified T> put(key: String, value: T) {
        val edit = sharedPreferences.edit()
        when (T::class) {
            String::class -> edit.putString(key, value as String)
            Int::class -> edit.putInt(key, value as Int)
            Float::class -> edit.putFloat(key, value as Float)
            Boolean::class -> edit.putBoolean(key, value as Boolean)
            Long::class -> edit.putLong(key, value as Long)
        }
        edit.commit()
    }

    inline fun <reified T> get(key: String, defaultValue: T) = when (T::class) {
        Boolean::class -> sharedPreferences.getBoolean(key, defaultValue as Boolean) as T
        Float::class -> sharedPreferences.getFloat(key, defaultValue as Float) as T
        Int::class -> sharedPreferences.getInt(key, defaultValue as Int) as T
        Long::class -> sharedPreferences.getLong(key, defaultValue as Long) as T
        String::class -> sharedPreferences.getString(key, defaultValue as String) as T
        else -> {
            if (defaultValue is Set<*>) {
                sharedPreferences.getStringSet(key, defaultValue as Set<String>) as T
            } else {
                defaultValue
            }
        }
    }

    companion object {
        fun init(context: Application) = SecurePreferenceHelper(context)
    }

    object Keys {
        const val KEY_APPLE_MUSIC_TOKEN = "apple_music_token"
        const val KEY_DEVELOPER_TOKEN = "apple_developer_token"
    }
}