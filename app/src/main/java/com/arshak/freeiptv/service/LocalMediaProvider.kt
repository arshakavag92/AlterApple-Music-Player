package com.arshak.freeiptv.service

import android.content.Context
import android.support.v4.media.MediaBrowserCompat
import androidx.media.MediaBrowserServiceCompat

/**
 * Copyright (C) 2017 Apple, Inc. All rights reserved.
 */
class LocalMediaProvider(context: Context) {

    private val applicationContext: Context = context.applicationContext

    fun loadMediaItems(
        parentId: String,
        result: MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>>
    ) {
        result.detach()
        DataLoader(
            applicationContext,
            parentId,
            result
        ).execute()
    }

    companion object {
        const val MEDIA_ROOT_ID = "MEDIA_ROOT"
    }

}