package com.arshak.core.utils

import android.app.Application
import android.app.KeyguardManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService
import com.arshak.core.extensions.clearValues
import com.arshak.core.extensions.put
import com.securepreferences.SecurePreferences


/**
 * Created by Arshak Avagyan on 2020-02-14.
 * Project Name: FreeIPTV
 */

class SecurePreferenceHelper {

    lateinit var mSecurePreferences: SecurePreferences
    lateinit var mStandartPreferences: SharedPreferences

    private val isDeviceSecured: Boolean
        get() {
            val keyguardManager =
                applicationContext.getSystemService(
                    Context.KEYGUARD_SERVICE
                ) as KeyguardManager? //api 16+
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                keyguardManager!!.isDeviceSecure
            } else keyguardManager!!.isKeyguardSecure
        }

    fun put(pair: Pair<String, Any>) = when (isDeviceSecured) {
        true -> mSecurePreferences.put(pair)
        false -> mStandartPreferences.put(pair)
    }

    fun checkForChangedSecurityCredentials() {
        when (isDeviceSecured) {
            true -> clearStandartPreferences()
            false -> clearSecurePreferences()
        }
    }

    fun clearSecurePreferences() = mSecurePreferences.clearValues()

    fun clearStandartPreferences() = mStandartPreferences.clearValues()

    companion object {

        lateinit var applicationContext: Application

        fun init(context: Application) {
            applicationContext = context
        }
    }
}