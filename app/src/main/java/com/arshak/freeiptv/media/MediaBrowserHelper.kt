package com.arshak.freeiptv.media

import android.content.ComponentName
import android.content.Context
import android.support.v4.media.MediaBrowserCompat
import com.arshak.freeiptv.service.MediaPlaybackService

class MediaBrowserHelper(
    context: Context,
    private val listener: MediaBrowserConnectionListener
) : MediaBrowserCompat.ConnectionCallback() {

    private val mediaBrowser: MediaBrowserCompat = MediaBrowserCompat(
        context,
        ComponentName(context, MediaPlaybackService::class.java),
        this,
        null
    )

    fun connect() = mediaBrowser.connect()

    fun disconnect() = mediaBrowser.disconnect()

    override fun onConnected() = listener.onMediaBrowserConnected(mediaBrowser)

    override fun onConnectionSuspended() = Unit
    override fun onConnectionFailed() = Unit

}