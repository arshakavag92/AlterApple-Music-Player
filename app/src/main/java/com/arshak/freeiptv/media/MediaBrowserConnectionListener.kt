package com.arshak.freeiptv.media

import android.support.v4.media.MediaBrowserCompat

interface MediaBrowserConnectionListener {
    fun onMediaBrowserConnected(mediaBrowser: MediaBrowserCompat)
}