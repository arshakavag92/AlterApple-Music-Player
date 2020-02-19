package com.arshak.core.data.viewmodel

import android.content.Intent

/**
 * Created by Arshak Avagyan on 2020-02-18.
 * Project Name: FreeIPTV
 */
interface OnActivityResult {
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}