package com.arshak.core.data.viewmodel

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel

/**
 * Created by Arshak Avagyan on 2020-02-17.
 * Project Name: FreeIPTV
 */
open class BaseAndroidViewModel(val context: Application) : AndroidViewModel(context),
    OnActivityResult {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) = Unit
}